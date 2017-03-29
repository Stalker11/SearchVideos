package com.example.professor.searchvideos.http;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VideoRequest {
    @POST("/api/api.php?")
    Call<JsonElement> getFilmInformationForTitle(@Query(value = "title", encoded = true) String filmName);
    @POST("/api/api.php?")
    Call<JsonElement> getFilmInformationForDirector(@Query(value = "director", encoded = true)
                                                    String director);
}
