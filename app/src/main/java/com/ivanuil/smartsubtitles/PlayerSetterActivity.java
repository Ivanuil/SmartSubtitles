package com.ivanuil.smartsubtitles;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ivanuil.smartsubtitles.classes.Subtitle;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerSetterActivity extends AppCompatActivity {

    public static final String APP_PREFERENCE_SUBTITLES_PATH = "subtitlesPath";
    public static final String APP_PREFERENCE = "appPrefences";
    SharedPreferences mSettings;
    public String pathname = Environment.getExternalStorageDirectory().getPath() + "/Movies/"
            + "Subtitles/";
    public String[] subArray;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_setter);
        TextView textView = (TextView) findViewById(R.id.film_name);
        textView.setText(getIntent().getStringExtra("Intent_filmName"));

        File file = new File(pathname);
        if (file.exists()) {
            file.mkdirs();
            Toast.makeText(this, pathname + " created", Toast.LENGTH_SHORT).show();
        }
        //subArray = getSubtitles();

        spinner = (Spinner) findViewById(R.id.sub_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getSubtitles());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        mSettings = getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        spinner.setSelection(getPosition(mSettings.getString(APP_PREFERENCE_SUBTITLES_PATH + " "+ getIntent().
                getStringExtra("Intent_filmName"), "")));
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCE_SUBTITLES_PATH + " "+ getIntent().
                        getStringExtra("Intent_filmName"), (int)spinner.getSelectedItem());
        editor.apply();
    }

    private int getPosition(String string) {
        int k = 0;
        for (int i = 0; i < subArray.length; i++) {
            if (subArray[i] == string) {
                k = i;
            }
        }
        return k;
    }

    private String[] getSubtitles() {
        File file = new File(pathname);
        File[] filesList = file.listFiles();
        String[] array;
        if (filesList.length == 0) {
            array = new String[filesList.length];
            for (int i = 0; i < filesList.length; i++) {
                String string = filesList[i].getPath().replace("_", " ").
                        replace(".srt", "");
                if (string.startsWith(" ")) {
                    string = string.replaceFirst(" ", "");
                }
                array[i] = string;
            }
        } else {
            array = new String[1];
        }
        return array;
    }

}
