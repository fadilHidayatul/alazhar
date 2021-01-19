package com.example.aplikasitu.UtilsApi;

public class UtilsApi {
    public static final String baseURL = "http://192.168.100.35/alazhar/"; //localhost
    public static final String img = "http://192.168.100.35/alazhar/img/"; //untuk gambar

    public static ApiInterface getApiService(){
        return RetrofitClient.getRetrofit(baseURL).create(ApiInterface.class);
    }

}
