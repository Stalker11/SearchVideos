package com.example.professor.searchvideos.callback;

import com.example.professor.searchvideos.models.DescriptionFilm;

import java.util.List;

public class SimpleRequestCallBack implements RequestCallBack {
    @Override
    public DescriptionFilm onSuccess(List<DescriptionFilm> films) {
        return null;
    }

    @Override
    public String onError(String error) {
        return null;
    }
}
