package com.example.professor.searchvideos.fragments;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.professor.searchvideos.MainActivity;
import com.example.professor.searchvideos.R;
import com.example.professor.searchvideos.models.DescriptionFilm;
import com.example.professor.searchvideos.util.Constants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieDetailsFragment extends Fragment {
     @BindView(R.id.movie_details_image) ImageView image;
     @BindView(R.id.movie_details_release) TextView release;
     @BindView(R.id.movie_details_rating) TextView rating;
     @BindView(R.id.movie_details_director) TextView director;
     @BindView(R.id.movie_details_summary) TextView summary;
    private DescriptionFilm videoInfo;
    private Unbinder un;
    private MainActivity act;
    public static final String TAG = MovieDetailsFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        act = (MainActivity) getActivity();
        act.tag = true;
        act.fragmentName = MovieDetailsFragment.class.getSimpleName();

        videoInfo = getArguments().getParcelable(Constants.SAVE_BUNDLE_DETAILS_FRAGMENT);

        View v = inflater.inflate(R.layout.movie_details_fragment, null);
        Log.d(TAG, "onCreateView: " + 12250);
        un = ButterKnife.bind(this,v);
        setParameters();
        if(videoInfo != null){
            act.setToolbar(videoInfo,image);
        }
        return v;
    }

    private void setParameters() {
        if (videoInfo != null) {
            Picasso.with(getActivity().getBaseContext()).load(videoInfo.getPicture())
                    .error(R.drawable.no_images)
                    .into(image, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "onSuccess: "+1000);
                        }

                        @Override
                        public void onError() {
                            Log.d(TAG, "onError: "+4500);
                        }
                    });
            release.setText(videoInfo.getRelease());
            rating.setText(videoInfo.getRaiting());
            director.setText(videoInfo.getDirector());
            summary.setText(videoInfo.getSummary());
        }
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: " + 114);
        act.tag = false;
        act.setToolbar(null,null);
        un.unbind();
        super.onDestroyView();
    }

}
