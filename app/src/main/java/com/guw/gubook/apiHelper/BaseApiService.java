package com.guw.gubook.apiHelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface BaseApiService {
    // LOGIN
    @FormUrlEncoded
    @POST("loginLuar.php")
    Call<ResponseBody> loginRequestLuar(
            @Field("email") String email,
            @Field("u_password") String password);

    @FormUrlEncoded
    @POST("loginLokal.php")
    Call<ResponseBody> loginRequestLokal(
            @Field("nim_mhs") String nim_mhs,
            @Field("passwd") String passwd);

    // REGISTER
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("u_password") String u_password,
                                       @Field("jk") String jk,
                                       @Field("asal") String asal,
                                       @Field("alamat") String alamat,
                                       @Field("no_hp") String no_hp);

}
