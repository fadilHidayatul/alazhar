package com.example.aplikasitu.UtilsApi;

public class UtilsApi {
//    public static final String baseURL = "http://192.168.100.35/alazhar/"; //localhost
    public static final String baseURL = "http://tualazhar.mediatamaweb.com/alazhar/"; //hosting
    public static final String imgPegawai = "http://tualazhar.mediatamaweb.com/backend/img/pegawai/"; //untuk gambar pegawai
    public static final String imgSPP = "http://tualazhar.mediatamaweb.com/backend/img/bukti_spp/"; //untuk gambar SPP
    public static final String pdf = "http://tualazhar.mediatamaweb.com/backend/file/lapor/"; //untuk pdf

    public static ApiInterface getApiService(){
        return RetrofitClient.getRetrofit(baseURL).create(ApiInterface.class);
    }

}
