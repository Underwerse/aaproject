package fi.aa.aaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SleepActivity extends AppCompatActivity {
    private ProgressBar pbSleep;
    private Button nappi;
    private Button nappi2;
    private int progress = 0;
    private TextView teksti1;
    private int unenmaara = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        DataProcessor dataProsessori = new DataProcessor(this  );
        String currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        updateProgress();

        Animation animation = (Animation) AnimationUtils.loadAnimation(this, R.anim.pb_animation);

        pbSleep = findViewById(R.id.pb_sleep);
        pbSleep.setProgress(0);
        pbSleep.startAnimation(animation);

        nappi = (Button) findViewById(R.id.nappi);
        nappi2 = (Button) findViewById(R.id.nappi2);
        teksti1 = (TextView) findViewById(R.id.unenMaara);

        nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress <=90){
                    progress += 10;
                    unenmaara += 1;
                    teksti1.setText(unenmaara +" H");
                    dataProsessori.setInt(currentDate +",sleep",unenmaara);
                    updateProgress();
                }
            }
        });

        nappi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress >= 10){
                    progress -= 10;
                    unenmaara -= 1;
                    teksti1.setText(unenmaara +" H");
                    dataProsessori.setInt(currentDate +",sleep",unenmaara);
                    updateProgress();
                }
            }
        });

    }
    private void updateProgress(){
        ProgressBar uni = (ProgressBar) findViewById(R.id.pb_sleep);

        uni.setProgress(progress);

    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String stateSaved = savedInstanceState.getString("saved_state:");


    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("saved_state:", "aaa");

    }

}