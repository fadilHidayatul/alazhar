package com.example.aplikasitu.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    Call<ResponseBody>getSppSiswa(@Query("bulan") String bulan,
                                  @Query("tahun") String tahun
    );//change

    @FormUrlEncoded
    @POST("spp/get_spp.php")
    Call<ResponseBody>getSpp(@Field("id") String id,
                             @Field("stat") String status,
                             @Field("bulan") String bulan,
                             @Field("tahun") String tahun
    );

    @FormUrlEncoded
    @POST("spp/update_spp.php")
    Call<ResponseBody>updateSpp(@Field("id") String id,
                                @Field("bulan") String bulan,
                                @Field("tahun") String tahun
    );

    @FormUrlEncoded
    @POST("siswa/get_siswa_by_kelas.php")
    Call<ResponseBody>getSiswaByKelas(@Field("kelas") String kelas,
                                      @Field("grup") String grup
    );

    @FormUrlEncoded
    @POST("rapor/get_rapor_siswa.php") //change done
    Call<ResponseBody>getRapor(@Field("id_siswa") String idSiswa,
                               @Field("kelas") String kelas,
                               @Field("grup") String grup,
                               @Field("semester") String semester
    );

    @FormUrlEncoded
    @POST("tatausaha/profil.php")
    Call<ResponseBody>getProfilPegawai(@Field("id_user") String idUser
    );
}
