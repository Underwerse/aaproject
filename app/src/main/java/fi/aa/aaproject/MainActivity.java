package fi.aa.aaproject;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private SensorManager sensorManager;
    private TextView tv_Steps;
    private SharedPreferences sharedPreferences;
    private int ACTIVITY_RECOGNITION_CODE = 1;
    private ProgressBar stepsProgressBar;
    private int stepsTarget = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        tv_Steps = (TextView) findViewById(R.id.textView_Steps);
        stepsProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        sharedPreferences = this.getSharedPreferences("fi.aa.aaproject", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed;

        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }

        if (!sharedPreferences.contains(currentDate)) {
            sharedPreferences.edit().putInt(currentDate, 0).apply();
        }

        if (sharedPreferences.contains(currentDate)) {
            int v = sharedPreferences.getInt(currentDate, 1);
            tv_Steps.setText(String.valueOf(v));
            stepsProgressBar.setProgress( 100 * v / this.stepsTarget );
        }

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(MainActivity.this, "You have already granted this permission", Toast.LENGTH_SHORT).show();
        } else {
            requestActivityPermission();
        }
    }

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

    @Override
    public void onSensorChanged(SensorEvent event) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Log.i("date", currentDate);
        if (sharedPreferences.contains(currentDate)) {
            int value = sharedPreferences.getInt(currentDate, 1);
            value = value + 1;
            sharedPreferences.edit().putInt(currentDate, value).apply();
            tv_Steps.setText(String.valueOf(value));
            stepsProgressBar.setProgress( 100 * value / this.stepsTarget);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
