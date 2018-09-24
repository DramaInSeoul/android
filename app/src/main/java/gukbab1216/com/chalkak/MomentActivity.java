package gukbab1216.com.chalkak;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import gukbab1216.com.chalkak.Model.DramaDto;
import gukbab1216.com.chalkak.Model.Picture;

public class MomentActivity extends AppCompatActivity implements ValueEventListener {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    static ArrayList<Picture> picturesList;
    String dramaTitle;
    String dramaDescription;
    String korDramaName;
    String subImageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        picturesList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //이전 Activity 에서 가져온 Intent
        Intent intent = getIntent();
        dramaTitle = (String) intent.getExtras().get("dramaName");
        dramaDescription = (String) intent.getExtras().get("dramaContext");
        FirebaseDatabase.getInstance().getReference().addValueEventListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseDatabase.getInstance().getReference().removeEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.child("DramaData").child(dramaTitle).child("pictures").getChildren()) {
            Picture pictures = dataSnapshot1.getValue(Picture.class);
            picturesList.add(pictures);
        }
        for (DataSnapshot dataSnapshot1 : dataSnapshot.child("DramaData").child(dramaTitle).getChildren()) {
            if (dataSnapshot1.getKey().equals("title")) {
                korDramaName = (String) dataSnapshot1.getValue();
            }
            if (dataSnapshot1.getKey().equals("subImage")) {
                subImageUrl = (String) dataSnapshot1.getValue();
            }
        }
        nextPage(dramaDescription);
    }

    private void nextPage(String dramaDescription) {

        Intent it = new Intent(this, SceneMapActivity.class);
        it.putExtra("pictureList", picturesList);
        it.putExtra("dramaName", korDramaName);
        it.putExtra("dramaContext", dramaDescription);
        it.putExtra("subImageUrl", subImageUrl);
        startActivity(it);
        this.finish();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
