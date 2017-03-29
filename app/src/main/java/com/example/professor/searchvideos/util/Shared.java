package com.example.professor.searchvideos.util;

import android.app.Activity;
import android.content.SharedPreferences;

public class Shared {
    private static Activity act;
    private static SharedPreferences sPref;
    private static SharedPreferences.Editor edit;
    public static final String TAG = Shared.class.getSimpleName();
    public static void setActivity(Activity activity){
        act = activity;
        sPref = act.getPreferences(act.MODE_PRIVATE);
        edit = sPref.edit();
    }
    public static void setPosition(int position){
        edit.putInt(ProjectConstants.POSITION_RECYCLER, position);
        edit.commit();
    }
    public static int getPosition(){
        return  sPref.getInt(ProjectConstants.POSITION_RECYCLER, 0);
    }

}
