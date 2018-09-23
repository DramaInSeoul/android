package gukbab1216.com.chalkak;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import gukbab1216.com.chalkak.Adapter.HorizontalSceneAdapter;
import gukbab1216.com.chalkak.Model.Drama;
import gukbab1216.com.chalkak.Model.Scene;
import gukbab1216.com.chalkak.databinding.ActivitySceneMapBinding;

public class SceneMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivitySceneMapBinding mBinding;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_scene_map);

        //이전 Activity 에서 가져온 Intent
        Intent intent = getIntent();
        String dramaTitle = (String)intent.getExtras().get("dramaName");
        String dramaDescription = (String) intent.getExtras().get("dramaContext");

        mBinding.setDrama(new Drama(dramaTitle, "", R.drawable.thumbnail_mylovefromthestar, dramaDescription));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initHorizontalScene();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void initHorizontalScene() {
        mBinding.scenePanel.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
        mBinding.scenePanel.setHasFixedSize(true);

        ArrayList<Scene> items = new ArrayList<>();
        items.add(new Scene("aaa", "", 0, ""));
        items.add(new Scene("aaa", "", 0, ""));
        items.add(new Scene("aaa", "", 0, ""));
        items.add(new Scene("aaa", "", 0, ""));
        items.add(new Scene("aaa", "", 0, ""));

        //set data and list adapter
        HorizontalSceneAdapter mAdapter = new HorizontalSceneAdapter(this, items);
        mBinding.scenePanel.setAdapter(mAdapter);


    }
}