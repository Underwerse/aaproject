package fi.aa.aaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

public class SleepActivity extends AppCompatActivity {
    private ProgressBar pbSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        Animation animation = (Animation) AnimationUtils.loadAnimation(this, R.anim.pb_animation);

        pbSleep = findViewById(R.id.pb_sleep);
        pbSleep.setProgress(70);
        pbSleep.startAnimation(animation);
    }
}