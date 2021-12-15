package fi.aa.aaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
        dataProsessori = new DataProcessor(this  );
        currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());

        nappi = (Button) findViewById(R.id.nappi);
        nappi2 = (Button) findViewById(R.id.nappi2);
        teksti1 = (TextView) findViewById(R.id.counterView);

        updateProgress();

        nappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress <=90){
                    progress += 10;
                    vesimaara += 250;
                    teksti1.setText(vesimaara +" ML");
                    dataProsessori.setInt(currentDate +",water",vesimaara);
                    updateProgress();
                }
            }
        });
        nappi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progress >= 10){
                    progress -= 10;
                    vesimaara -= 250;
                    teksti1.setText(vesimaara +" ML");
                    dataProsessori.setInt(currentDate +",water",vesimaara);
                    updateProgress();

                }
            }
        });

    }

    private void updateProgress(){
        ProgressBar lasi = (ProgressBar) findViewById(R.id.progressBar);
        vesimaara = dataProsessori.getInt(currentDate + ",water");
        progress = vesimaara / 25;
        lasi.setProgress(progress);
        teksti1.setText(String.valueOf(vesimaara) +" ML");

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