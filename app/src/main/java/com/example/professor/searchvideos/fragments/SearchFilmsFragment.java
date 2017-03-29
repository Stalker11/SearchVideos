package com.example.professor.searchvideos.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.professor.searchvideos.MainActivity;
import com.example.professor.searchvideos.R;
import com.example.professor.searchvideos.callback.RequestCallBack;
import com.example.professor.searchvideos.callback.SimpleRequestCallBack;
import com.example.professor.searchvideos.http.RequestForServer;
import com.example.professor.searchvideos.models.DescriptionFilm;
import com.example.professor.searchvideos.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchFilmsFragment extends Fragment {
    private View view;
    @BindView(R.id.search_button) Button search;
    @BindView(R.id.edit_text_search) EditText searchName;
    private RequestCallBack request;
     private RequestForServer query;
    private MainActivity act;
    private Unbinder un;
    private int flag;

    private static final String TAG = SearchFilmsFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_films_fragment,null);
        String hint = getArguments().getString(Constants.SEARCH_PARAMETER);
        un = ButterKnife.bind(this,view);
        flag = getArguments().getInt(Constants.SEARCH_FLAG);
        searchName.setHint(hint);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestInspection(searchName.getText().toString().trim());
            }
        });
        act = (MainActivity)getActivity();
        return view;
    }
    private void serchFilms(String searchString){
        request = new CallBack();
        query = new RequestForServer();
        if(flag == 2) {
            query.requestFilmForDirector(searchString, request);
            Log.d(TAG, "serchFilms: "+searchString);
        }
        if(flag == 1){
            query.requestFilmForTitle(searchString,request);
            Log.d(TAG, "serchFilms: "+searchString);
        }
    }
    private class CallBack extends SimpleRequestCallBack {
        @Override
        public DescriptionFilm onSuccess(List<DescriptionFilm> films) {
            Log.d(TAG, "onStart: " + films.get(0).getCategory());

            act.createFragment(FragmentsGenerator.filmsFragment((ArrayList<DescriptionFilm>) films));
            return super.onSuccess(films);
        }

        @Override
        public String onError(String error) {
            Log.d(TAG, "onError: " + error);
            act.createFragment(FragmentsGenerator.notFoundFilms(error));
            return super.onError(error);
        }
    }
    private String requestInspection(String request) {
        String[] numbers = request.split(request);
        if (numbers.length < 10) {
            serchFilms(request);
        } else {
            return null;
        }
return null;
    }

    @Override
    public void onDestroyView() {
        un.unbind();
        super.onDestroyView();
    }
}
