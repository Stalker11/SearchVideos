package com.example.professor.searchvideos.fragments;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.professor.searchvideos.MainActivity;
import com.example.professor.searchvideos.R;
import com.example.professor.searchvideos.adapters.MoviesListAdapter;
import com.example.professor.searchvideos.models.DescriptionFilm;
import com.example.professor.searchvideos.util.Constants;
import com.example.professor.searchvideos.util.RecycleViewOnItemClickListener;
import com.example.professor.searchvideos.util.Shared;

import java.util.ArrayList;

public class SelectFilmsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<DescriptionFilm> films;
    private static final String TAG = SelectFilmsFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_films_fragment,null);
        films =  getArguments().getParcelableArrayList(Constants.SAVE_BUNDLE_SELECT_FILMS);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        Log.d(TAG, "onCreateView: "+ films);
        if(films != null) {
            MoviesListAdapter adapter = new MoviesListAdapter(films, null);
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition(Shared.getPosition());
            adapter.setOnItemClickListener(new RecycleViewOnItemClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    Log.d(TAG, "setText: " + position);
                    Shared.setPosition(position);
                    MainActivity act = (MainActivity) getActivity();
                    act.createFragment(FragmentsGenerator.detailsFragment(films.get(position)));
                }
            });
        }
        else{
            String error = getArguments().getString(Constants.NOT_FOUND);
            Log.d(TAG, "onCreateView: "+error);

            MoviesListAdapter adapter = new MoviesListAdapter(null, error);
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition(Shared.getPosition());
         }

        return view;

    }

    @Override
    public void onDestroyView() {
        recyclerView = null;
        films = null;
        super.onDestroyView();
    }
}
