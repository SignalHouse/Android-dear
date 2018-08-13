package nexters.com.dear.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonToken;
import android.view.View;
import android.widget.RelativeLayout;

import nexters.com.dear.R;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences savedToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.splashRelative);
        //layout.setBackgroundDrawable(new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.splash)));

        Handler handler = new Handler();
        handler.postDelayed(new SplashHandler(), 2000);
    }

    private class SplashHandler implements Runnable {
        public void run() {
            Intent i = new Intent(SplashActivity.this, LoginActivity.class); // 수정 예정
            startActivity(i);
            SplashActivity.this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recycleView(findViewById(R.id.splashRelative));
        System.gc();
    }

    // 메모리 관리를 위한 리사이클 함수
    private void recycleView(View view) {
        if (view != null) {
            Drawable bg = view.getBackground();
            if (bg != null) {
                bg.setCallback(null);
                ((BitmapDrawable) bg).getBitmap().recycle();
                view.setBackgroundDrawable(null);
            }
        }
    }
}
