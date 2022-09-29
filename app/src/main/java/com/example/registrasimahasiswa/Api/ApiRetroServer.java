package com.example.registrasimahasiswa.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetroServer {
    private static final String BASE_URL = "http://10.0.2.2:8000/api/";
//    private static final String BASE_URL = "https://registrasi.mcdeveloper.click/api/";
    private static Retrofit retrofit;

    public static Retrofit konekRetro(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
