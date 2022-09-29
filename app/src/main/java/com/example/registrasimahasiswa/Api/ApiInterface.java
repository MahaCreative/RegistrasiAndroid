package com.example.registrasimahasiswa.Api;

import com.example.registrasimahasiswa.Model.Login.LoginResponse;

import com.example.registrasimahasiswa.Model.Response.MahasiswaResponse;


import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(@Field("nim") String nim,
                                  @Field("password") String pass,
                                  @Field("password_confirmation") String confirm);

    @GET("mahasiswa")
    Call<MahasiswaResponse> ardFetchData(@Query("search") String search);
}
