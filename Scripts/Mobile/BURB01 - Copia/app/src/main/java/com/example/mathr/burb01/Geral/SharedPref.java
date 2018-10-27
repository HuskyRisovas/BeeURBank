package com.example.mathr.burb01.Geral;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mathr on 03/03/2018.
 */

public class SharedPref {
    SharedPreferences mySharedPref;

    int periodo;

    public SharedPref(Context context)
    {
        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }

    public void setNightModeState(Boolean state)
    {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }

    public Boolean loadNightModeState()
    {
        Boolean state = mySharedPref.getBoolean("NightMode",false);
        return state;
    }

}
