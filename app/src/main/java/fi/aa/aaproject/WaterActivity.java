package fi.aa.aaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WaterActivity extends AppCompatActivity {

    private Button nappi;
    private Button nappi2;
    private TextView teksti1;
    private int progress;
    private int vesimaara;
    private DataProcessor dataProsessori;
    private String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        dataProsessori = DataProcessor.getInstance(this  );
        currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());

        nappi = (Button) findViewById(R.id.nappi);
        nappi2 = (Button) findViewById(R.id.nappi2);
        teksti1 = (TextView) findViewById(R.id.counterView);

        updateProgress();

        nappi.setOnClickListener(v -> {
            if (progress <=90) {
                progress += 10;
            }
            vesimaara += 250;
            teksti1.setText(vesimaara +" ML");
            dataProsessori.setInt(currentDate +",water",vesimaara);
            updateProgress();
        });
        nappi2.setOnClickListener(v -> {
            if (progress >= 10) {
                    progress -= 10;
            }
            if (vesimaara >= 250) {
                vesimaara -= 250;
            }
            teksti1.setText(vesimaara +" ML");
            dataProsessori.setInt(currentDate +",water",vesimaara);
            updateProgress();

        });

    }

    private void updateProgress(){
        ProgressBar lasi = (ProgressBar) findViewById(R.id.pb_water);
        vesimaara = dataProsessori.getInt(currentDate + ",water");
        progress = vesimaara / 25;
        lasi.setProgress(progress);
        teksti1.setText(String.valueOf(vesimaara) +" ML");

    }
}