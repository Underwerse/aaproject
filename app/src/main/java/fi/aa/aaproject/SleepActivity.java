package fi.aa.aaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SleepActivity extends AppCompatActivity {
    private Button nappi;
    private Button nappi2;
    private int progress;
    private TextView teksti1;
    private int unenmaara;
    private String currentDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
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
            DataProcessor.getInstance(this).setInt(currentDate +",sleep",unenmaara);
            updateProgress();
        });

        nappi2.setOnClickListener(v -> {
            if (progress >= 10) {
                progress -= 10;
            }
            if (unenmaara >= 1) {
                unenmaara -= 1;
            }
            teksti1.setText(unenmaara +" H");
            DataProcessor.getInstance(SleepActivity.this).setInt(currentDate +",sleep",unenmaara);
            updateProgress();
        });

    }
    private void updateProgress(){
        ProgressBar uni = (ProgressBar) findViewById(R.id.pb_sleep);
        unenmaara = DataProcessor.getInstance(this).getInt(currentDate + ",sleep");
        progress = 100 * unenmaara / 8;
        teksti1.setText(String.valueOf(unenmaara) +" H");
        uni.setProgress(progress);
    }
}