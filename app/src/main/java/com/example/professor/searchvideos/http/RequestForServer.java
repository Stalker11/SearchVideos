package com.example.professor.searchvideos.http;


import android.util.Log;

import com.example.professor.searchvideos.callback.RequestCallBack;
import com.example.professor.searchvideos.models.DescriptionFilm;
import com.example.professor.searchvideos.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestForServer {

    private static final String TAG = RequestForServer.class.getSimpleName();
    private  String query;
    private DescriptionFilm film;

    private  List<DescriptionFilm> films = new ArrayList<DescriptionFilm>();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.HOSTING_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private  VideoRequest request = retrofit.create(VideoRequest.class);

    public  Call<JsonElement> requestFilmForTitle(String title, final RequestCallBack callBack) {
        if (title.length() > 1) {
            System.out.println(title.length());
            StringBuilder builder = new StringBuilder();
            String q[] = title.split(" ");
            for (int i = 0; i < q.length; i++) {
                builder.append(q[i])
                        .append("%20");
            }
            int start = builder.length();
            query = builder.delete(start - 3, builder.length()).toString();
            Log.d(TAG, "requestFilmForTitle: " + query);
        } else {
            query = title;
        }
        Call<JsonElement> answer = request.getFilmInformationForTitle(query);
        answer.enqueue(new Callback<JsonElement>() {
            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

                callBack.onError(t.getMessage());

            }

            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d(TAG, "onResponse: " + response.code());
                Log.d(TAG, "onResponse: " + response.message());
                Log.d(TAG, "onResponse: " + response.body());
                try {
                    Gson gson = new GsonBuilder().create();
                    film = gson.fromJson(response.body().toString(), DescriptionFilm.class);
                    films.add(film);
                    callBack.onSuccess(films);
                    Log.d(TAG, "onResponse: " + film.getCategory());
                } catch (NullPointerException e) {
                    callBack.onError(response.message());
                }
            }
        });

        return answer;
    }

    public  void requestFilmForDirector(String title, final RequestCallBack callBack) {
        if (title.length() > 1) {
            System.out.println(title.length());
            StringBuilder builder = new StringBuilder();
            String q[] = title.split(" ");
            for (int i = 0; i < q.length; i++) {
                builder.append(q[i])
                        .append("%20");
            }
            int start = builder.length();
            query = builder.delete(start - 3, builder.length()).toString();
            Log.d(TAG, "requestFilmForTitle: " + query);
        } else {
            query = title;
        }
        Call<JsonElement> answer = request.getFilmInformationForDirector(query);
        answer.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.d(TAG, "onResponse: 110" + response.body());
                try {
                    Gson gson = new GsonBuilder().create();
                    films = gson.fromJson(response.body().toString()
                            , new TypeToken<ArrayList<DescriptionFilm>>() {
                            }.getType());
                    Log.d(TAG, "onResponse: " + films.get(1).getCategory());
                    callBack.onSuccess(films);
                } catch (NullPointerException e) {
                     callBack.onError(response.message());
                }
            }
            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


}