package fi.aa.aaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {
    private final DataProcessor dataProcessor = new DataProcessor(this);
    private TextView tvSelectedDate;
    private TextView tvCalSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        tvSelectedDate = findViewById(R.id.tv_date);
        tvCalSteps = findViewById(R.id.tv_cal_steps);
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
        tvSelectedDate.setText(date);
        Log.i("date", date + ", data: " + String.valueOf(dataProcessor.getInt(date)));

        tvCalSteps.setText("Äskeltä: ");
        String updatedSteps = tvCalSteps.getText() + String.valueOf(dataProcessor.getInt(date));
        tvCalSteps.setText(updatedSteps);
    }
}