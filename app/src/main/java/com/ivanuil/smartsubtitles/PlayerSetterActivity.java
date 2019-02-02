package com.ivanuil.smartsubtitles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class PlayerSetterActivity extends AppCompatActivity {

    public static String APP_PREFERENCES;
    public static String APP_PREFERENCES_FILMS_FOLDER;
    SharedPreferences mSettings;
    public static String INTENT_FILM_NAME;
    public static String INTENT_FILM_PATH;
    public static String INTENT_SUB_PATH;

    public static final String APP_PREFERENCE_SUBTITLES_PATH = "subtitlesPath";
    public static final String APP_PREFERENCE = "appPrefences";
    public String pathname = Environment.getExternalStorageDirectory().getPath() + "/Movies/Subtitles/";
    public String[] subArray;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_setter);

        //Manage Prefences anf Intents
        APP_PREFERENCES = getString(R.string.APP_PREFERENCES);
        APP_PREFERENCES_FILMS_FOLDER = getString(R.string.APP_PREFERENCES_FILMS_FOLDER);
        INTENT_FILM_NAME = getString(R.string.INTENT_FILM_NAME);
        INTENT_FILM_PATH = getString(R.string.INTENT_FILM_PATH);
        INTENT_SUB_PATH = getString(R.string.INTENT_SUB_PATH);
        TextView nameTextView = (TextView) findViewById(R.id.film_name);
        nameTextView.setText(getIntent().getStringExtra(INTENT_FILM_NAME));
        TextView pathTextView = (TextView) findViewById(R.id.film_path);
        pathTextView.setText(getIntent().getStringExtra(INTENT_FILM_NAME));
        Button playButton = (Button) findViewById(R.id.play_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayerSetterActivity.this, PlayerActivity.class);
                intent.putExtra(INTENT_FILM_NAME, getIntent().getStringExtra(INTENT_FILM_NAME));
                intent.putExtra(INTENT_FILM_PATH, getIntent().getStringExtra(INTENT_FILM_PATH));
                //intent.putExtra(INTENT_SUB_PATH, );
                startActivity(intent);
            }
        });

        File file = new File(pathname);
        if (file.exists()) {
            file.mkdirs();
            Toast.makeText(this, pathname + " created", Toast.LENGTH_SHORT).show();
        }
        subArray = getSubtitles();

        spinner = (Spinner) findViewById(R.id.sub_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, subArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        mSettings = getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        //spinner.setSelection(getPosition(mSettings.getString(APP_PREFERENCE_SUBTITLES_PATH + " "+ getIntent().
        //        getStringExtra("Intent_filmName"), "")));
    }

    @Override
    protected void onStop() {
        super.onStop();
        //SharedPreferences.Editor editor = mSettings.edit();
        //editor.putInt(APP_PREFERENCE_SUBTITLES_PATH + " "+ getIntent().
        //                getStringExtra("Intent_filmName"), (int)spinner.getSelectedItem());
        //editor.apply();
    }

    private int getPosition(String string) {
        int k = 0;
        for (int i = 0; i < subArray.length; i++) {
            if (subArray[i].equals(string)) {
                k = i;
            }
        }
        return k;
    }

    private String[] getSubtitles() {
        File file = new File(pathname);
        File[] filesList = file.listFiles();
        String[] array = new String[filesList.length];
        for (int i = 0; i < filesList.length; i++) {
            array[i] = filesList[i].getPath();
        }
        return array;
    }

}
