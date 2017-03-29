package com.example.professor.searchvideos.fragments;


import android.os.Bundle;

import com.example.professor.searchvideos.models.DescriptionFilm;
import com.example.professor.searchvideos.util.Constants;

import java.util.ArrayList;

public class FragmentsGenerator {
    public static SelectFilmsFragment filmsFragment(ArrayList<DescriptionFilm> film){
        SelectFilmsFragment selectFragment = new SelectFilmsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.SAVE_BUNDLE_SELECT_FILMS,film);
        selectFragment.setArguments(bundle);
        return selectFragment;
    }
    public static MovieDetailsFragment detailsFragment(DescriptionFilm film){
        MovieDetailsFragment detailsFragment = new MovieDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.SAVE_BUNDLE_DETAILS_FRAGMENT,film);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }
    public static SelectFilmsFragment notFoundFilms(String error){
        SelectFilmsFragment notfound = new SelectFilmsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.NOT_FOUND,error);
        notfound.setArguments(bundle);
        return notfound;
    }
    public static SearchFilmsFragment searcFilms(String searchParameter, int flag){
        SearchFilmsFragment search = new SearchFilmsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.SEARCH_FLAG,flag);
        bundle.putString(Constants.SEARCH_PARAMETER, searchParameter);
        search.setArguments(bundle);
        return search;
    }
    public static SavedFilmsFragment showSavedFilms(){
        SavedFilmsFragment saveFrag = new SavedFilmsFragment();
        return saveFrag;
    }

}
