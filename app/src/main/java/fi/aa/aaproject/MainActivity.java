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

public class MainActivity extends AppCompatActivity implements SensorEventListener, StepsTargetDialog.StepsTargetDialogListener
{
    private TextView tv_Steps;
    private TextView tvStepsTarget;
    private ProgressBar stepsProgressBar;
    private int stepsTarget;
    private StepsCounter stepsCounter;

    // Saving day-steps preferences into default Android DB initiation
    // private SharedPreferences sharedPreferences;
    DataProcessor dataProcessor = new DataProcessor(this);
    private final int ACTIVITY_RECOGNITION_CODE = 1;
    private final String currentDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Current Main activity's variables
        // Defining all the variables
        // Android sensor (for steps activity following) initiation
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        tv_Steps = findViewById(R.id.tv_main_steps_qty);
        stepsProgressBar = findViewById(R.id.pb_steps);
        tvStepsTarget = findViewById(R.id.tv_info_main_2);

        tvStepsTarget.setOnClickListener(v -> {
            openDialog();
        });

        stepsCounter = new StepsCounter(dataProcessor.getInt(currentDate + ",steps"),
                dataProcessor.getInt("steps target"));
        stepsTarget = stepsCounter.getStepsTarget();

        // More activities buttons initiation
        Button btnCalendar = findViewById(R.id.btn_calendar);
        Button btnSleep = findViewById(R.id.btn_sleep);
        Button btnWater = findViewById(R.id.btn_water);

        // Check if device has steps counter sensor or not
        checkIfDeviceHasStepsSensor(sensorManager, countSensor);

        setCurrentStepsQtyFromDb();

        // Check if user granted device activity permission or not
        checkIfActivityPermissionGranted();

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

        // Testing data saving
//        dataProcessor.setInt("28.11.2021" + ",steps", 555);
//        dataProcessor.setInt("28.11.2021" + ",sleep", 8);
//        dataProcessor.setInt("28.11.2021" + ",water", 1200);
//        dataProcessor.setInt("27.11.2021" + ",steps", 1300);
//        dataProcessor.setInt("27.11.2021" + ",sleep", 5);
//        dataProcessor.setInt("27.11.2021" + ",water", 900);
    }

    @Override
    public void applyStepsTargetQty(int stepsTargetQty) {
        setStepsTarget(stepsTargetQty);
//        tvStepsTarget.setText("Tavoiteltu askelmäärä: " + stepsCounter.getStepsTarget());
        dataProcessor.setInt("steps target", stepsTargetQty);
        setCurrentStepsQtyFromDb();
    }

    public void openDialog() {
        StepsTargetDialog stepsTargetDialog = new StepsTargetDialog();
        stepsTargetDialog.show(getSupportFragmentManager(), "steps target dialog");
    }

    public void setCurrentStepsQtyFromDb() {
        int v = stepsCounter.getCounter();
        tv_Steps.setText(String.valueOf(v));

        stepsTarget = stepsCounter.getStepsTarget();
        tvStepsTarget.setText("Tavoiteltu askelmäärä: " + stepsTarget);

        stepsProgressBar.setProgress( 100 * v / stepsTarget );

        // Main steps ProgressBar animation
        ProgressBarAnimation pbMainStepsAnim = new ProgressBarAnimation(stepsProgressBar, 0, 100 * v / stepsTarget);
        pbMainStepsAnim.setDuration(1000);
        stepsProgressBar.startAnimation(pbMainStepsAnim);
    }

    public void checkIfActivityPermissionGranted() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(MainActivity.this, "You have already granted this permission", Toast.LENGTH_SHORT).show();
        } else {
            // Request needed permission method
            requestActivityPermission();
        }
    }

    public void checkIfDeviceHasStepsSensor(SensorManager sensorManager, Sensor countSensor) {
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
//            Toast.makeText(this, "Steps sensor has been found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void setStepsTarget(int stepsTargetQty) {
        this.stepsCounter.setStepsTarget(stepsTargetQty);
    }

    // Defining methods for opening the activities
    public void openCalendarActivity() {

        Intent intent = new Intent(this, CalendarActivity.class);
        // Max day steps qty
        intent.putExtra("stepsTarget", stepsTarget);
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
        int v = stepsCounter.getCounter();
        Log.i("steps before", String.valueOf(stepsCounter.getCounter()));
        stepsCounter.plusValue();

        // Record updated steps data into local Android DB
        dataProcessor.setInt(currentDate + ",steps", v);

        // Updating text field and progress bar with the last fixed steps
        Log.i("steps", String.valueOf(v));
        tv_Steps.setText(String.valueOf(v));
        stepsProgressBar.setProgress( 100 * v / stepsTarget);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Due to we implement SensorEventListener, we have to define this function too (even without a code)
    }
}
