package com.ivanuil.smartsubtitles;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {
    public static String APP_PREFERENCES;
    public static String APP_PREFERENCES_FILMS_FOLDER;
    SharedPreferences mSettings;
    EditText editFilmsPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Manage Preferences
        APP_PREFERENCES = getString(R.string.APP_PREFERENCES);
        APP_PREFERENCES_FILMS_FOLDER = getString(R.string.APP_PREFERENCES_FILMS_FOLDER);

        editFilmsPath = (EditText)findViewById(R.id.path_films);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_FILMS_FOLDER)) {
            editFilmsPath.setText(mSettings.getString(APP_PREFERENCES_FILMS_FOLDER, ""));
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_FILMS_FOLDER, editFilmsPath.getText().toString());
        editor.apply();

    }
}
