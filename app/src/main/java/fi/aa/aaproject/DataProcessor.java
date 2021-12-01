package fi.aa.aaproject;

import android.content.Context;
import android.content.SharedPreferences;

public class DataProcessor {

    private final Context context;

    public DataProcessor(Context context){
        this.context = context;
    }

    public final static String PREFS_NAME = "fi.aa.aaproject";

    public boolean prefExists(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.contains(key);
    }

    public void setInt(String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(key, 1);
    }

    public boolean getBool(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(key,false);
    }
}
