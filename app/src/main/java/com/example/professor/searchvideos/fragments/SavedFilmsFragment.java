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
import android.widget.LinearLayout;

import com.example.professor.searchvideos.MainActivity;
import com.example.professor.searchvideos.R;
import com.example.professor.searchvideos.adapters.MoviesListAdapter;
import com.example.professor.searchvideos.database.ReadWriteFilmsInStorage;
import com.example.professor.searchvideos.models.DescriptionFilm;
import com.example.professor.searchvideos.util.RecycleViewOnItemClickListener;
import com.example.professor.searchvideos.util.Shared;

import java.util.ArrayList;
import java.util.List;

public class SavedFilmsFragment extends Fragment {
private List<DescriptionFilm> films = new ArrayList<>();
    private RecyclerView recyclerView;
    private static final String TAG = SavedFilmsFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.select_films_fragment,null);
        films = ReadWriteFilmsInStorage.readFilm();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        MoviesListAdapter adapter = new MoviesListAdapter(films,null);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(Shared.getPosition());
        adapter.setOnItemClickListener(new RecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "setText: " + position);
                MainActivity act = (MainActivity) getActivity();
                Shared.setPosition(position);
                act.createFragment(FragmentsGenerator.detailsFragment(films.get(position)));
            }
        });
        return v;
    }
}
