package com.ivanuil.smartsubtitles;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class PlayerActivity extends AppCompatActivity {

    private String videoSource;
    SharedPreferences mSettings;
    public static String APP_PREFERENCE;
    public static String INTENT_FILM_NAME;
    public static String INTENT_FILM_PATH;
    public static String INTENT_SUB_PATH;
    public static String APP_PREFERENCES_FILMS_FOLDER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player);
        INTENT_FILM_NAME = getString(R.string.INTENT_FILM_NAME);
        INTENT_FILM_PATH = getString(R.string.INTENT_FILM_PATH);
        INTENT_SUB_PATH = getString(R.string.INTENT_SUB_PATH);
        APP_PREFERENCES_FILMS_FOLDER = getString(R.string.APP_PREFERENCES_FILMS_FOLDER);
        APP_PREFERENCE = getString(R.string.APP_PREFERENCES);
        mSettings = getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        videoSource = mSettings.getString(APP_PREFERENCES_FILMS_FOLDER, "")
                + getIntent().getStringExtra(INTENT_FILM_PATH);
        Toast.makeText(this, "Video: " + videoSource, Toast.LENGTH_SHORT).show();

        VideoView videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setVideoPath(videoSource);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
    }
}
