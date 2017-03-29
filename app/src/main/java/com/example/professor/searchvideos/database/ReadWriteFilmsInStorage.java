package com.example.professor.searchvideos.database;

import android.util.Log;

import com.example.professor.searchvideos.models.DescriptionFilm;

import java.sql.SQLException;
import java.util.List;

public class ReadWriteFilmsInStorage {

    private static final String TAG = ReadWriteFilmsInStorage.class.getSimpleName();

    public static void writeFilm(DescriptionFilm filmName) {
        try {
            HelperFactory.getHelper().getDao().createIfNotExists(filmName);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "writeFilm: " + e.getMessage());
        }
    }

    public static List<DescriptionFilm> readFilm() {
        List<DescriptionFilm> films = null;
        try {
            HelperDataBase storage = HelperFactory.getHelper();
            films = storage.getDao().queryForAll();
        } catch (SQLException e) {
            Log.d(TAG, "readFilm: " + e.getMessage());
        }
        return films;
    }
}
