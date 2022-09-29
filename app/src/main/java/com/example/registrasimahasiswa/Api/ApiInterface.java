package com.example.registrasimahasiswa.Api;

import com.example.registrasimahasiswa.Model.Response.LoginReponse;
import com.example.registrasimahasiswa.Model.Response.MahasiswaResponse;
import com.example.registrasimahasiswa.Request.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("login")
    Call<LoginReponse> userLogin(@Body LoginRequest loginRequest);

    @GET("mahasiswa")
    Call<MahasiswaResponse> ardFetchData(@Query("search") String search);
}
