package com.example.aplikasitu.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("siswa/get_siswa.php")
    Call<ResponseBody>allSiswa();

    @FormUrlEncoded
    @POST("tatausaha/login.php")
    Call<ResponseBody>login(@Field("username") String username,
                            @Field("password") String password
                            );

    @GET("pegawai/get_pegawai.php")
    Call<ResponseBody>allPegawai();
}
