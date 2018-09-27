package gukbab1216.com.chalkak;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import gukbab1216.com.chalkak.Adapter.CustomAdapter;
import gukbab1216.com.chalkak.Model.DramaDto;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    public static View.OnClickListener myOnClickListener;

    //firebase
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;

    ArrayList<DramaDto> list;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //permission
        String[] neededPermissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(this, neededPermissions, 0);

        TextView mainContext = findViewById(R.id.mainContext);

        mainContext.setText("당신의 드라마를 선택하세요");

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("DramaData");
        list = new ArrayList<>();

        //get Drama Data
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    DramaDto dramaDto = dataSnapshot1.getValue(DramaDto.class);
                    list.add(dramaDto);
                }
                customAdapter = new CustomAdapter(MainActivity.this, list);
                recyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static class MyOnClickListener extends Activity implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            int selectedItemPosition = recyclerView.getChildPosition(view);
            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(selectedItemPosition);

            TextView textView = viewHolder.itemView.findViewById(R.id.textViewName);
            TextView textView1 = viewHolder.itemView.findViewById(R.id.textDramaContext);
            String dramaName = (String) textView.getText();
            String dramaContext = (String) textView1.getText();

            //우리가 사용하는 드라마가 도깨비,쌈마이웨이 두가지 인데
            //Firebase 자동 정렬로 인해 데이터를 가져와서 새로 정렬하는데 시간이 오래걸려서 하드코딩 했음.
            switch (dramaName) {
                case "도깨비":
                    dramaName = "A Goblin";
                    break;
                case "쌈마이웨이":
                    dramaName = "BFight for My Way";
                    break;
                case "미녀의 탄생":
                    dramaName = "Cirth of a Beauty";
                    break;
                case "태양의 후예":
                    dramaName = "Descendants of The Sun";
                    break;
                case "닥터 이방인":
                    dramaName = "Doctor of Medicine";
                    break;
                case "별에서 온 그대":
                    dramaName = "My Love From the Star";
                    break;
                case "상속자":
                    dramaName = "The Inheritors";
                    break;
                case "함부로 애틋하게":
                    dramaName = "Uncontrollably Fond";
                    break;
                case "드라마 준비중":
                    dramaName = "zPreparing Image";
                    break;
            }


            //다음 Activity로 넘길 드라마 제목과 드라마 설명
            Intent intent = new Intent(context, MomentActivity.class);
            intent.putExtra("dramaName", dramaName);
            intent.putExtra("dramaContext", dramaContext);

            context.startActivity(intent);
        }
    }
}
