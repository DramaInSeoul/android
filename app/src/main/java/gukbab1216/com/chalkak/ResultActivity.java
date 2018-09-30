package gukbab1216.com.chalkak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

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
        Button button = findViewById(R.id.back_button);
        ImageButton imgButton = findViewById(R.id.image_back_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


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
