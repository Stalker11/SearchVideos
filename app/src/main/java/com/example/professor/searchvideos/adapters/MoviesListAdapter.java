package com.example.professor.searchvideos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.professor.searchvideos.models.DescriptionFilm;

import java.util.List;
import com.example.professor.searchvideos.R;
import com.example.professor.searchvideos.util.RecycleViewOnItemClickListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

 public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder> implements
RecycleViewOnItemClickListener{
    private List<DescriptionFilm> movies;
    private String errors;
    private ViewHolder viewHolder;
    private Context context;
    private View view;
    private RecycleViewOnItemClickListener clickListener;
    public static final String TAG = MoviesListAdapter.class.getSimpleName();

    public MoviesListAdapter(List<DescriptionFilm> list, String errors) {
        this.movies = list;
        this.errors = errors;
        Log.d(TAG, "MoviesListAdapter: ");
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_list, parent, false);
        viewHolder = new ViewHolder(view);
        context = parent.getContext();
        Log.d(TAG, "onCreateViewHolder: ");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(movies != null) {
            holder.textView.setText(movies.get(position).getTitle());
            Log.d(TAG, "onBindViewHolder: "+position+"  "+movies.size());
            Log.d(TAG, "onBindViewHolder: "+movies.get(position).getPicture());
                        Picasso.with(context).load(movies.get(position).getPicture())
                        .error(R.drawable.no_images)
                       // .resizeDimen(R.dimen.recycler_picture_size, R.dimen.recycler_picture_size)
                       // .centerInside()
                        .into(holder.imageView);
                       //   .resizeDimen(R.dimen.recycler_picture_size, R.dimen.recycler_picture_size).centerInside()
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(holder.getAdapterPosition(),view);
                }
            });
            Log.d(TAG, "onBindViewHolder: image  "+holder.imageView.getTag());
        }
        if(errors != null){
            holder.textView.setText(errors);
            Log.d(TAG, "onBindViewHolder: "+errors);
        }
        if(errors == null && movies == null){
            Log.d(TAG, "onBindViewHolder: "+55555555);
            holder.textView.setText(context.getResources().getString(R.string.nothing_saved_films));
        }
    }

    @Override
    public int getItemCount() {
        if(movies != null) {
            Log.d(TAG, "getItemCount: "+movies.size());
           return movies.size();
        }
        if(errors != null){
            return 1;
        }
        return 0;
    }

     class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            ImageView imageView;
             View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = ButterKnife.findById(itemView,R.id.movies_list_text);
            imageView = ButterKnife.findById(itemView, R.id.movies_list_image);
            this.itemView = itemView;

        }
    }
    public void setOnItemClickListener(RecycleViewOnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onItemClick(int position, View v) {

    }
    }

