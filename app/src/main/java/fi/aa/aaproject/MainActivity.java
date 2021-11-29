package fi.aa.aaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    // Defining all the variables
    // Android sensor (for steps activity following) initiation
    private SensorManager sensorManager;
    private TextView tv_Steps;
    private ProgressBar stepsProgressBar;
    private Sensor countSensor;

    // Max day steps qty
    private int stepsTarget = 600;

    // Saving day-steps preferences into default Android DB initiation
//    private SharedPreferences sharedPreferences;
    DataProcessor dataProcessor = new DataProcessor(this);
    private int ACTIVITY_RECOGNITION_CODE = 1;


    // More activities buttons initiation
    private Button btnCalendar;
    private Button btnSleep;
    private Button btnWater;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Initial methods
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Current all the Main activity's variables
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        tv_Steps = (TextView) findViewById(R.id.textView_Steps);
        stepsProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        btnCalendar = (Button) findViewById(R.id.btn_calendar);
        btnSleep = (Button) findViewById(R.id.btn_sleep);
        btnWater = (Button) findViewById(R.id.btn_water);

        // Today's date variable creation
        String currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());

//        sharedPreferences = this.getSharedPreferences("fi.aa.aaproject", Context.MODE_PRIVATE);
//        SharedPreferences.Editor ed;

        // Check if device has steps counter sensor or not
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }

        // Check if there is saved steps data in the local DB or not
        if (!dataProcessor.prefExists(currentDate)) {
            dataProcessor.setInt(currentDate, 0);
        }
        if (dataProcessor.prefExists(currentDate)) {
            int v = dataProcessor.getInt(currentDate);
            tv_Steps.setText(String.valueOf(v));
            stepsProgressBar.setProgress( 100 * v / this.stepsTarget );
        }

        // Check if user granted device activity permission or not
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(MainActivity.this, "You have already granted this permission", Toast.LENGTH_SHORT).show();
        } else {
            // Request needed permission method
            requestActivityPermission();
        }

        // Setting onClickListeners to buttons for moving into another activities
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendarActivity();
            }
        });

        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSleepActivity();

                // Test case for sharedPrefs
                String yesterdayDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date(System.currentTimeMillis()-24*60*60*1000));
                dataProcessor.setInt(yesterdayDate, 555);
                Log.i("today", String.valueOf(dataProcessor.getInt(currentDate)));
                Log.i("yesterday", String.valueOf(dataProcessor.getInt(yesterdayDate)));
            }
        });

        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWaterActivity();
            }
        });

        dataProcessor.setInt("28.11.2021" + ",steps", 555);
        dataProcessor.setInt("28.11.2021" + ",dreams", 8);
        dataProcessor.setInt("28.11.2021" + ",water", 1200);
        dataProcessor.setInt("27.11.2021" + ",steps", 1300);
        dataProcessor.setInt("27.11.2021" + ",dreams", 5);
        dataProcessor.setInt("27.11.2021" + ",water", 900);
    }

    // Defining methods for opening the activities
    public void openCalendarActivity() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void openSleepActivity() {
        Intent intent = new Intent(this, SleepActivity.class);
        startActivity(intent);
    }

    public void openWaterActivity() {
        Intent intent = new Intent(this, WaterActivity.class);
        startActivity(intent);
    }

    /**
     * Requesting Activity_Recognition permissions from user
     */
    private void requestActivityPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACTIVITY_RECOGNITION)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission denied")
                    .setMessage("This permission is needed for recording your activity")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACTIVITY_RECOGNITION}, ACTIVITY_RECOGNITION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACTIVITY_RECOGNITION}, ACTIVITY_RECOGNITION_CODE);
        }
    }

    // Getting message of permission requesting result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ACTIVITY_RECOGNITION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Steps counter detection processing
    @Override
    public void onSensorChanged(SensorEvent event) {
        String currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        if (dataProcessor.prefExists(currentDate)) {
            int value = dataProcessor.getInt(currentDate);
            value += 1;

            // Record updated steps data into local Android DB
            dataProcessor.setInt(currentDate + ",steps", value);

            // Updating text field and progress bar with the last fixed steps
            tv_Steps.setText(String.valueOf(value));
            stepsProgressBar.setProgress( 100 * value / this.stepsTarget);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Due to we implement SensorEventListener, we have to define this function too (even without a code)
    }
}
