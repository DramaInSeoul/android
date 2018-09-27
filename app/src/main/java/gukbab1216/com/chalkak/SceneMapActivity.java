package gukbab1216.com.chalkak;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import gukbab1216.com.chalkak.Adapter.HorizontalSceneAdapter;
import gukbab1216.com.chalkak.Model.Drama;
import gukbab1216.com.chalkak.Model.Picture;
import gukbab1216.com.chalkak.databinding.ActivitySceneMapBinding;

public class SceneMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivitySceneMapBinding mBinding;

    private GoogleMap mMap;
    static ArrayList<Picture> pictureArrayList;
    String dramaTitle;
    HorizontalSceneAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_scene_map);

        //이전 Activity 에서 가져온 Intent
        Intent intent = getIntent();
        dramaTitle = (String) intent.getExtras().get("dramaName");
        pictureArrayList = (ArrayList<Picture>) intent.getSerializableExtra("pictureList");
        String dramaDescription = (String) intent.getExtras().get("dramaContext");
        String subDramaImage = (String) intent.getExtras().get("subImageUrl");


        mBinding.setDrama(new Drama(dramaTitle, "", 0, dramaDescription));

        ImageView imageView = findViewById(R.id.thumbnail_drama);
        Glide.with(this).load(subDramaImage).into(imageView);

        FloatingActionButton nextFabBtn = findViewById(R.id.nextCameraFloatingButton);

        nextFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SceneMapActivity.this, CameraActivity.class));

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initHorizontalScene();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng position = new LatLng(37.576034, 126.98705199999995);
        mMap.addMarker(new MarkerOptions().position(position).title(dramaTitle + "촬영지")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);
        mMap.moveCamera(zoom);


    }

    private void initHorizontalScene() {
        mBinding.scenePanel.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
        mBinding.scenePanel.setHasFixedSize(true);
        mAdapter = new HorizontalSceneAdapter(this, pictureArrayList);
        mBinding.scenePanel.setAdapter(mAdapter);

    }

}