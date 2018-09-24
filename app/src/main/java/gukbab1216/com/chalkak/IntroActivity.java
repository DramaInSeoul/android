package gukbab1216.com.chalkak;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class IntroActivity extends AppCompatActivity {
    /**
     * 로딩 화면이 떠있는 시간(밀리초단위)
     **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        /* SPLASH_DISPLAY_LENGTH 뒤에 메뉴 액티비티를 실행시키고 종료.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* 메뉴액티비티를 실행하고 로딩화면을 죽인다.*/
                Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    public void callMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
