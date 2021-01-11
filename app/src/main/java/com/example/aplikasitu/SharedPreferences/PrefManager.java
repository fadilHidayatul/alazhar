package com.example.aplikasitu.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.logging.Level;

public class PrefManager {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "ALAZHAR";
    public static final String SESSION_KEY = "SESSION_USER";
    public static final String ID_SISWA = "ID_SISWA";
    public static final String USERNAME = "USERNAME";
    public static final String LEVEL = "LEVEL";


    public PrefManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = preferences.edit();

    }

    //login session
    public void saveSession(){
        editor.putBoolean(SESSION_KEY,true);
        editor.commit();
    }
    public boolean getSession(){
        return preferences.getBoolean(SESSION_KEY,false);
    }
    public void removeSession(){
        editor.putBoolean(SESSION_KEY,false);
        editor.commit();
    }

    //set user
    public void spIdSiswa(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }
    public void spUsername(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }
    public void spLevel(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    //get user
    public String getIdSiswa(){
        return preferences.getString(ID_SISWA,"");
    }
    public String getUsername(){
        return preferences.getString(USERNAME,"");
    }
    public String getLevel(){
        return preferences.getString(LEVEL,"");
    }
}
