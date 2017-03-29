package com.example.professor.searchvideos.callback;


import com.example.professor.searchvideos.models.DescriptionFilm;

import java.util.List;

public interface RequestCallBack {
    public DescriptionFilm onSuccess(List<DescriptionFilm> films);
    public String onError(String error);
}
