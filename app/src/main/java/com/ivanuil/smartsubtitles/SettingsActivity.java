package com.ivanuil.smartsubtitles;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "appPreferences";
    public static final String APP_PREFERENCES_FILMS_FOLDER = "filmsFolder";
    public static final String APP_PREFERENCES_SERIES_FOLDER = "seriesFolder";
    //public static final String APP_PREFERENCES_SUBTITLES_FOLDER = "seriesFolder";
    SharedPreferences mSettings;
    EditText editFilmsPath;
    EditText editSeriesPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editFilmsPath = (EditText)findViewById(R.id.path_films);
        editSeriesPath = (EditText)findViewById(R.id.path_series);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_FILMS_FOLDER)) {
            editFilmsPath.setText(mSettings.getString(APP_PREFERENCES_FILMS_FOLDER, ""));
        }
        if (mSettings.contains((APP_PREFERENCES_SERIES_FOLDER))) {
           editSeriesPath.setText(mSettings.getString(APP_PREFERENCES_SERIES_FOLDER, ""));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_FILMS_FOLDER, editFilmsPath.getText().toString());
        editor.putString(APP_PREFERENCES_SERIES_FOLDER, editSeriesPath.getText().toString());
        editor.apply();
    }
}
