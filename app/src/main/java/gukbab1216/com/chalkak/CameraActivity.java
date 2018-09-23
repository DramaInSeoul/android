package gukbab1216.com.chalkak;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gukbab1216.com.chalkak.Camera.Camera2RawFragment;
import gukbab1216.com.chalkak.databinding.ActivityCameraBinding;

public class CameraActivity extends AppCompatActivity {
    private ActivityCameraBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_camera);

        if (null == savedInstanceState) {
            initCameraPreview();
        }
    }

    private void initCameraPreview() {
        // Create an instance of Camera
        getFragmentManager().beginTransaction().replace(R.id.camera_preview, Camera2RawFragment.newInstance()).commit();
    }
}
