package com.ivanuil.smartsubtitles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaFormat;
import android.media.audiofx.Equalizer;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Locale;

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
        setContentView(R.layout.activity_player);

        //Manage Prefences and Intents
        INTENT_FILM_NAME = getString(R.string.INTENT_FILM_NAME);
        INTENT_FILM_PATH = getString(R.string.INTENT_FILM_PATH);
        INTENT_SUB_PATH = getString(R.string.INTENT_SUB_PATH);
        APP_PREFERENCES_FILMS_FOLDER = getString(R.string.APP_PREFERENCES_FILMS_FOLDER);
        APP_PREFERENCE = getString(R.string.APP_PREFERENCES);
        mSettings = getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        videoSource = mSettings.getString(APP_PREFERENCES_FILMS_FOLDER, "")
                + getIntent().getStringExtra(INTENT_FILM_PATH);
        Toast.makeText(this, "Video: " + videoSource, Toast.LENGTH_SHORT).show();

        //Manage VideoView and Media Controller
        VideoView videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setVideoPath(videoSource);
        startActivity(new Intent(Settings.ACTION_CAPTIONING_SETTINGS));
        videoView.addSubtitleSource(getResources().openRawResource(R.raw.subs),
                MediaFormat.createSubtitleFormat("text/vtt", Locale.ENGLISH.getLanguage()));
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
    }
}
