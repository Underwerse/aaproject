package fi.aa.aaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {
    private final DataProcessor dataProcessor = new DataProcessor(this);
    private TextView tvSelectedDate;
    private TextView tvCalSteps;
    private TextView tvCalDreams;
    private TextView tvCalWater;
    private int stepsTarget;
    ProgressBar pbCalSteps;
    ProgressBar pbCalWater;
    ProgressBar pbCalSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent intent = getIntent();
        stepsTarget = intent.getIntExtra("stepsTarget", 10000);

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
            String selectedDate;
            if (String.valueOf(mDay).length() == 1) {
                selectedDate = "0" +
                        mDay +
                        "." +
                        mMonth +
                        "." +
                        mYear;
            } else {
                selectedDate = "" +
                        mDay +
                        "." +
                        mMonth +
                        "." +
                        mYear;
            }

            updateUI(selectedDate);
        });
    }

    public void updateUI(String date) {
        tvSelectedDate.setText("Tilasto valittuna pvm:nä: " + date);

        //******************** STEPS *********************
        // TextView field:
        tvCalSteps.setText("Askelta: ");
        String updatedSteps = tvCalSteps.getText() +
                String.valueOf(dataProcessor.getInt(date +
                        ",steps"));
        tvCalSteps.setText(updatedSteps);

        // ProgressBar:
        int stepsCurrentProgress = Math.round(100 * dataProcessor.getInt(date + ",steps") / (float) stepsTarget);
        pbCalSteps.setProgress(stepsCurrentProgress);

        // ProgressBar animation:
        ProgressBarAnimation pbStepsAnim = new ProgressBarAnimation(pbCalSteps, 0, stepsCurrentProgress);
        pbStepsAnim.setDuration(1000);
        pbCalSteps.startAnimation(pbStepsAnim);

        //******************** WATER *********************
        // TextView field:
        tvCalWater.setText("Vettä juotu: ");
        String updatedWater = tvCalWater.getText() +
                String.valueOf(dataProcessor.getInt(date +
                        ",water")) +
                " ml";
        tvCalWater.setText(updatedWater);

        // ProgressBar:
        int waterCurrentProgress = (int) Math.round(dataProcessor.getInt(date + ",water") / 22.5);
        pbCalWater.setProgress(waterCurrentProgress);

        // ProgressBar animation:
        ProgressBarAnimation pbWaterAnim = new ProgressBarAnimation(pbCalWater, 0, waterCurrentProgress);
        pbWaterAnim.setDuration(1000);
        pbCalWater.startAnimation(pbWaterAnim);

        //******************** SLEEP *********************
        // TextView field:
        tvCalDreams.setText("Uniaika: ");
        String updatedDreams = tvCalDreams.getText() +
                String.valueOf(dataProcessor.getInt(date +
                        ",sleep")) +
                " t.";

        // ProgressBar:
        int sleepCurrentProgress = (int) Math.round(100 * dataProcessor.getInt(date + ",sleep") / 8.0);
        pbCalSleep.setProgress(sleepCurrentProgress);
        tvCalDreams.setText(updatedDreams);

        // ProgressBar animation:
        ProgressBarAnimation pbSleepAnim = new ProgressBarAnimation(pbCalSleep, 0, sleepCurrentProgress);
        pbSleepAnim.setDuration(1000);
        pbCalSleep.startAnimation(pbSleepAnim);
    }
}