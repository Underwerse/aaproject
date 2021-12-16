package fi.aa.aaproject;

import android.content.Context;
import android.content.SharedPreferences;

public class DataProcessor {

    private final static String PREFS_NAME = "fi.aa.aaproject";
    private final SharedPreferences sharedPref;

    private static DataProcessor instance;

    public static synchronized DataProcessor getInstance(Context applicationContext){
        if(instance == null)
            instance = new DataProcessor(applicationContext);
        return instance;
    }

    private DataProcessor(Context applicationContext) {
        sharedPref = applicationContext.getSharedPreferences(
                PREFS_NAME, Context.MODE_PRIVATE );
    }

    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key) {
        return sharedPref.getInt(key, 0);
    }
}
