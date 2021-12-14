package fi.aa.aaproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private ImageView splashLogo;
    private TextView splashText;
    private static int splashTimeOut = 5000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashLogo = findViewById(R.id.splash_logo);
        splashText = findViewById(R.id.splash_text);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }, splashTimeOut);

        Animation splashAnimationFadein = AnimationUtils.loadAnimation(this, R.anim.splash_animation_fadein);
        splashLogo.startAnimation(splashAnimationFadein);
        splashText.startAnimation(splashAnimationFadein);
    }
}
