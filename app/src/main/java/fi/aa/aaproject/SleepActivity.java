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
    private int progress;
    private TextView teksti1;
    private int unenmaara;
    private DataProcessor dataProsessori;
    private String currentDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        dataProsessori = new DataProcessor(this  );
        currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());

        nappi = (Button) findViewById(R.id.nappi);
        nappi2 = (Button) findViewById(R.id.nappi2);
        teksti1 = (TextView) findViewById(R.id.unenMaara);

        updateProgress();

        nappi.setOnClickListener(v -> {
            if (progress <=90) {
                progress += 10;
            }
            unenmaara += 1;
            teksti1.setText(unenmaara +" H");
            dataProsessori.setInt(currentDate +",sleep",unenmaara);
            updateProgress();
        });

        nappi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress >= 10) {
                    progress -= 10;
                }
                unenmaara -= 1;
                teksti1.setText(unenmaara +" H");
                dataProsessori.setInt(currentDate +",sleep",unenmaara);
                updateProgress();
            }
        });

    }
    private void updateProgress(){
        ProgressBar uni = (ProgressBar) findViewById(R.id.pb_sleep);
        unenmaara = dataProsessori.getInt(currentDate + ",sleep");
        progress = 100 * unenmaara / 8;
        teksti1.setText(String.valueOf(unenmaara) +" H");
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