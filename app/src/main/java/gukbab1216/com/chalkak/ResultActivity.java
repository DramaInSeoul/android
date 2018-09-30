package gukbab1216.com.chalkak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ImageView filterImageView = findViewById(R.id.filterImageView);
        ImageView afterImageView = findViewById(R.id.afterImageView);
        ProgressBar back_progressBar = findViewById(R.id.back_ProgressBar);
        ProgressBar pos_progressBar = findViewById(R.id.pos_progressBar);
        TextView backTextView = findViewById(R.id.back_textView);
        TextView posTextView = findViewById(R.id.pos_textView);


        try {
            JSONObject obj = new JSONObject(getIntent().getStringExtra("jsonObject"));
            String filterImageUrl = (String) getIntent().getExtras().get("filterImageUrl");
            String afterImageUrl = (String) getIntent().getExtras().get("afterImageUrl");


            Glide.with(this).load(filterImageUrl).into(filterImageView);
            Glide.with(this).load(afterImageUrl).into(afterImageView);


            float back = Float.parseFloat(String.valueOf(obj.get("back")));
            float pose = Float.parseFloat(String.valueOf(obj.get("pose")));

            int intBack = (int) (back * 100);
            int intPose = (int) (pose * 100);

            back_progressBar.setProgress(intBack);
            pos_progressBar.setProgress(intPose);

            backTextView.setText(String.valueOf(back));
            posTextView.setText(String.valueOf(pose));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
