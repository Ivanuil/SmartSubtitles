package com.ivanuil.smartsubtitles.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivanuil.smartsubtitles.R;
import com.ivanuil.smartsubtitles.classes.Film;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private List<Film> filmList = new ArrayList<>();

    public class FilmViewHolder extends RecyclerView.ViewHolder{

        private ImageView coverImageView;
        private TextView nameTextView;
        private TextView fileTextView;
        private TextView sizeTextView;
        private String Link = "https://cdn1.vectorstock.com/i/1000x1000/04/95/" +
                "film-movie-camera-icon-vector-10070495.jpg";

        public FilmViewHolder (View itemView) {
            super(itemView);
            coverImageView = itemView.findViewById(R.id.coverImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            fileTextView = itemView.findViewById(R.id.fileTextView);
            sizeTextView = itemView.findViewById(R.id.sizeTextView);
        }

        public void Bind(Film film) {
            nameTextView.setText(film.getFilmName());
            fileTextView.setText(film.getFileName());
            if (film.getImageURL() == null) {
                Picasso.with(itemView.getContext()).load(Link).into(coverImageView);
            } else {
                Picasso.with(itemView.getContext()).load(film.getImageURL()).into(coverImageView);
            }
            sizeTextView.setText(film.getFileSize());
        }

    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.film_item_view,viewGroup, false);

        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder filmViewHolder, int i) {
        filmViewHolder.Bind(filmList.get(i));
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public void setItems(Collection<Film> films) {
        filmList.addAll(films);
        notifyDataSetChanged();
    }



}
