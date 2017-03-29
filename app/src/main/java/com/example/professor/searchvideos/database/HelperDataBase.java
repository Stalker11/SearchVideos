package com.example.professor.searchvideos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.professor.searchvideos.models.DescriptionFilm;
import com.example.professor.searchvideos.util.Constants;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class HelperDataBase extends OrmLiteSqliteOpenHelper {
    private Dao<DescriptionFilm,Long> filmDao;
    private static final String TAG = HelperDataBase.class.getSimpleName();
    public HelperDataBase(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, DescriptionFilm.class);
        } catch (SQLException e) {
            Log.d(TAG, "onCreate: "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
    public Dao<DescriptionFilm, Long> getDao() throws SQLException {
        if(filmDao == null) {
            filmDao = getDao(DescriptionFilm.class);
        }
        return filmDao;
    }
    @Override
    public void close(){
        super.close();

    }
}
