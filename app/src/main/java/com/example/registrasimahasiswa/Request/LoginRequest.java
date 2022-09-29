package com.example.registrasimahasiswa.Request;

public class LoginRequest {
    private String nimoremail;
    private String pass;
    private String passConfirm;

    public String getNimoremail() {
        return nimoremail;
    }

    public void setNimoremail(String nimoremail) {
        this.nimoremail = nimoremail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassConfirm() {
        return passConfirm;
    }

    public void setPassConfirm(String passConfirm) {
        this.passConfirm = passConfirm;
    }
}
