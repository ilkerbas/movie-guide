package com.appmovieguide.movieguide;

import java.util.ArrayList;

public class CinemaPerson extends Person implements ICinemaPerson {

    ArrayList<String> movieList;

    public CinemaPerson(String name) {
        super(name);
        this.movieList = new ArrayList<String>();
    }

    public ArrayList<String> getMovieList() {
        return movieList;
    }


    public void setMovieList(ArrayList<String> movieList) {
        this.movieList = movieList;
    }

    @Override
    public void addMovie(String movie) {
        movieList.add(movie);
    }


    public String toString() {
        return name + "," + movieList;
    }
}
