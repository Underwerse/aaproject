package fi.aa.aaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {
    private final DataProcessor dataProcessor = new DataProcessor(this);
    private TextView tvSelectedDate;
    private TextView tvCalSteps;
    private TextView tvCalDreams;
    private TextView tvCalWater;
    ProgressBar pbCalSteps;
    ProgressBar pbCalWater;
    ProgressBar pbCalSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        tvSelectedDate = findViewById(R.id.tv_date);
        tvCalSteps = findViewById(R.id.tv_cal_steps);
        tvCalDreams = findViewById(R.id.tv_cal_sleep);
        tvCalWater = findViewById(R.id.tv_cal_water);

        pbCalSteps = findViewById(R.id.pb_cal_steps);
        pbCalWater = findViewById(R.id.pb_cal_water);
        pbCalSleep = findViewById(R.id.pb_cal_sleep);

        String currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        updateUI(currentDate);

        CalendarView calendarView = findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            int mYear = year;
            int mMonth = month + 1;
            int mDay = dayOfMonth;
            String selectedDate = "" +
                    mDay +
                    "." +
                    mMonth +
                    "." +
                    mYear;

            updateUI(selectedDate);
        });
    }

    public void updateUI(String date) {
        tvSelectedDate.setText("Tilasto valittuna pvm:nä: " + date);

        tvCalSteps.setText("Äskeltä: ");
        String updatedSteps = tvCalSteps.getText() +
                String.valueOf(dataProcessor.getInt(date +
                        ",steps"));
        tvCalSteps.setText(updatedSteps);
        pbCalSteps.setProgress(dataProcessor.getInt(date + ",steps") / 6);
        tvCalWater.setText("Vettä juotu: ");
        String updatedWater = tvCalWater.getText() +
                String.valueOf(dataProcessor.getInt(date +
                        ",water"));
        tvCalWater.setText(updatedWater);
        DecimalFormat decimalFormat = new DecimalFormat( "#" );
        String result = decimalFormat.format(value);
        pbCalWater.setProgress(dataProcessor.getInt(date + ",water") / 22.5);
        tvCalDreams.setText("Uniaika: ");
        String updatedDreams = tvCalDreams.getText() +
                String.valueOf(dataProcessor.getInt(date +
                        ",sleep"));
        tvCalDreams.setText(updatedDreams);


    }
}