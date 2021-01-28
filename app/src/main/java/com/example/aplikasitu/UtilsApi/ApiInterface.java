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

    @GET("kelas/get_kelas.php")
    Call<ResponseBody>getAllKelas();

    @FormUrlEncoded
    @POST("kelas/get_grup.php")
    Call<ResponseBody>getGrupKelas(@Field("kelas") String kelas
    );

    @FormUrlEncoded
    @POST("jadwal/get_jadwal.php")
    Call<ResponseBody>getJadwal(@Field("kelas") String kelas,
                                @Field("grup") String grup,
                                @Field("hari") String hari
    );

    @FormUrlEncoded
    @POST("absen/get_absen_pegawai.php")
    Call<ResponseBody>getAbsen(@Field("pegawai") String pegawai
    );

    @GET("spp/get_spp_siswa.php")
    Call<ResponseBody>getSppSiswa();
}
