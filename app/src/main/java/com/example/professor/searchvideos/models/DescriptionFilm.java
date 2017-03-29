package com.example.professor.searchvideos.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Favorite films")
public class DescriptionFilm implements Parcelable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    @SerializedName("release_year")
    private String release;
    @DatabaseField
    @SerializedName("rating")
    private String raiting;
    @DatabaseField(unique = true)
    @SerializedName("summary")
    private String summary;
    @DatabaseField
    @SerializedName("show_title")
    private String title;
    @DatabaseField
    @SerializedName("category")
    private String category;
    @DatabaseField
    @SerializedName("director")
    private String director;
    @DatabaseField
    @SerializedName("poster")
    private String picture;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(release);
        parcel.writeString(raiting);
        parcel.writeString(title);
        parcel.writeString(category);
        parcel.writeString(director);
        parcel.writeString(picture);
    }
public DescriptionFilm(){

}
    private DescriptionFilm(Parcel parcel) {  // Создание объекта через Parcel
        release = parcel.readString();
        raiting = parcel.readString();
        title = parcel.readString();
        category = parcel.readString();
        director = parcel.readString();
        picture = parcel.readString();
    }

    public static final Parcelable.Creator<DescriptionFilm> CREATOR =
            new Parcelable.Creator<DescriptionFilm>(){

        public DescriptionFilm createFromParcel(Parcel in) {
            return new DescriptionFilm(in);
        }

        public DescriptionFilm[] newArray(int size) {
            return new DescriptionFilm[size];
        }
    };


    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getRaiting() {
        return raiting;
    }

    public void setRaiting(String raiting) {
        this.raiting = raiting;
    }

    public String getSummary() {
        return summary;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

