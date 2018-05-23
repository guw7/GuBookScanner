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

//     REGISTER
//    @FormUrlEncoded
//    @POST("register.php")
//    Call<ResponseBody> registerRequest(@Field("nama") String nama,
//                                       @Field("email") String email,
//                                       @Field("password") String password);


//    CONTOH GET BASIC
//    @GET("guw")
//    Call<ResponseMDVK> getSemuaGUW();

//    CONTOH HET WITH PARAM
//    @GET("guw/{userpro}")
//    Call<ResponseUserDetail> getDetailUser(@Path("userpro") String namauser);


}
