package com.ivanuil.smartsubtitles.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ivanuil.smartsubtitles.PlayerSetterActivity;
import com.ivanuil.smartsubtitles.R;
import com.ivanuil.smartsubtitles.adapter.FilmAdapter;
import com.ivanuil.smartsubtitles.classes.Film;
import com.ivanuil.smartsubtitles.listeners.RecyclerFilmClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilmsFragment extends Fragment {

    public static String INTENT_FILM_NAME;
    public static String INTENT_FILM_PATH;
    private RecyclerView recyclerView;
    private FilmAdapter filmAdapter;
    public String pathname = Environment.getExternalStorageDirectory().getPath() + "/Movies/";
    public Collection<Film> films;
    File[] filesList;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        INTENT_FILM_NAME = getString(R.string.INTENT_FILM_NAME);
        INTENT_FILM_PATH = getString(R.string.INTENT_FILM_PATH);

        File file = new File(pathname);
        if (!file.exists()) {
            file.mkdirs();
            Toast.makeText(getContext(), pathname + " created", Toast.LENGTH_LONG).show();
        }

        View view = inflater.inflate(R.layout.fragment_films, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        filmAdapter = new FilmAdapter();
        loadFilms();
        recyclerView.setAdapter(filmAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerFilmClickListener(getContext(), recyclerView, new RecyclerFilmClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), PlayerSetterActivity.class);
                        intent.putExtra(INTENT_FILM_NAME, getFilmsArray()[position].getFilmName());
                        intent.putExtra(INTENT_FILM_PATH, getFilmsArray()[position].getPath());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getContext(), "Item" + position + " long clicked",
                                Toast.LENGTH_SHORT).show();
                    }
                })
        );

        return view;
    }

    private void loadFilms() {
        films = getFilms();
        filmAdapter.setItems(films);
    }

    private Collection<Film> getFilms() {
        File files = new File(pathname);
        filesList = files.listFiles();
        List<Film> list = new ArrayList<>();
        for (int i = 0; i < filesList.length; i++) {
            Film filmTemp = new Film(filesList[i]);
            list.add(i, filmTemp);
        }
        return list;
    }

    private Film[] getFilmsArray() {
        File files = new File(pathname);
        filesList = files.listFiles();
        Film[] array = new Film[filesList.length];
        for (int i = 0; i < filesList.length; i++) {
            array[i] = new Film(filesList[i]);
        }
        return array;
    }

}