package com.example.aplikasitu.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "ALAZHAR";
    public static final String SESSION_KEY = "SESSION_USER";
    public static final String ID_USER = "ID_USER";
    public static final String ID_PEGAWAI = "ID_PEGAWAI";
    public static final String USERNAME = "USERNAME";
    public static final String LEVEL = "LEVEL";
    public static final String KELAS = "KELAS";
    public static final String SISWA_SPP = "SISWA_SPP";
    public static final String NAMASISWA_SPP = "NAMA_SISWA_SPP";
    public static final String TAHUN_SPP = "TAHUN_SPP";
    public static final String BULAN_SPP = "BULAN_SPP";
    public static final String GRUP_RAPOR = "GRUP_RAPOR";
    public static final String ID_SISWA_RAPOR = "ID_SISWA_RAPOR";


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
    public void spIdUser(String key, String value){
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
    public void spIdPegawai(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    //get user
    public String getIdUser(){
        return preferences.getString(ID_USER,"");
    }
    public String getUsername(){
        return preferences.getString(USERNAME,"");
    }
    public String getLevel(){
        return preferences.getString(LEVEL,"");
    }
    public String getIdPegawai(){
        return preferences.getString(ID_PEGAWAI,"");
    }

    //kelas
    public void spKelas(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }
    public String getKelas(){
        return preferences.getString(KELAS,"");
    }

    //siswaspp
    public void spSppSiswa(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }
    public String getSppSiswa(){
        return preferences.getString(SISWA_SPP,"");
    }

    public void spSppNama(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }
    public String getSppNama(){
        return preferences.getString(NAMASISWA_SPP,"");
    }
    public void bulanSPP(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }
    public String getBulanSPP(){
        return preferences.getString(BULAN_SPP,"");
    }

    public void tahunSPP(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }
    public String getTahunSPP(){
        return preferences.getString(TAHUN_SPP,"");
    }

    //rapor
    public void spGrupKelasRapor(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }
    public String getGrupRapor(){
        return preferences.getString(GRUP_RAPOR,"");
    }

    public void spIdSiswaRapor(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }
    public String getIdSiswaRapor(){
        return preferences.getString(ID_SISWA_RAPOR,"");
    }
}
