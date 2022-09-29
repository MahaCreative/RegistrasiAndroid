package com.example.registrasimahasiswa.Model.Response;

import com.example.registrasimahasiswa.Model.Data.LoginModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginReponse {
    private String message;
    private String token;
    @SerializedName("data")
    private List<LoginModel> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<LoginModel> getResult() {
        return result;
    }

    public void setResult(List<LoginModel> result) {
        this.result = result;
    }
}
