package com.example.registrasimahasiswa.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.registrasimahasiswa.Activity.MainActivity;
import com.example.registrasimahasiswa.Api.ApiInterface;
import com.example.registrasimahasiswa.Api.ApiRetroServer;
import com.example.registrasimahasiswa.Model.Data.LoginModel;
import com.example.registrasimahasiswa.Model.Response.LoginReponse;
import com.example.registrasimahasiswa.Model.Response.MahasiswaResponse;
import com.example.registrasimahasiswa.R;
import com.example.registrasimahasiswa.Request.LoginRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginTabFragment extends Fragment {
    private EditText nimoremail, pass, passConfirmation;
    private Button btnLogin;
    private TextView foregetPassword;
    private float v=0;
    private ProgressDialog progressDialog;
    private List<LoginModel> loginModelList = new ArrayList<>();
//    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup group = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);


//        Progress
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.setCancelable(false);


        nimoremail = group.findViewById(R.id.nimoremail);
        pass = group.findViewById(R.id.pass);
        passConfirmation = group.findViewById(R.id.confirm_pass);
        btnLogin = group.findViewById(R.id.btnLogin);
        foregetPassword = group.findViewById(R.id.forgetPassword);
        styleAnimation();

//        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nimoremail.getText().length() > 0 && pass.getText().length()>0) {
                    if (pass.getText().toString().equals(passConfirmation.getText().toString())) {
                        login(nimoremail.getText().toString(), passConfirmation.getText().toString());

                    } else {
                        passConfirmation.setError("Password harus sama");
                    }
                }else{
                    Toast.makeText(getActivity(), "Input tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return group;
    }
    private void login(String nimemail, String pass){
//        Koding Login
        loginToApi();
//        mAuth.signInWithEmailAndPassword(nimemail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful() && task.getResult() != null){
//                    if (task.getResult().getUser() != null){
//
////                        reload();
//                    }else{
//                        Toast.makeText(getActivity(),"Login gagal", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(getActivity(),"Login gagal", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private void loginToApi(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setNimoremail(nimoremail.getText().toString());
        loginRequest.setPass(pass.getText().toString());
        loginRequest.setPassConfirm(passConfirmation.getText().toString());
        ApiInterface apiData = ApiRetroServer.konekRetro().create(ApiInterface.class);
        Call<LoginReponse> loginReponseCall = apiData.userLogin(loginRequest);
        loginReponseCall.enqueue(new Callback<LoginReponse>() {
            @Override
            public void onResponse(Call<LoginReponse> call, Response<LoginReponse> response) {
                loginModelList = response.body().getResult();
                Log.e("get Login", String.valueOf(loginModelList));
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(), "Login ke api berhasil ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Login ke api Gagal ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginReponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Login ke api Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void reload() {
        startActivity(new Intent(getContext(), MainActivity.class));
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            reload();
//        }
//    }

    private void styleAnimation(){
        nimoremail.setTranslationX(400);
        pass.setTranslationX(400);
        passConfirmation.setTranslationX(400);
        foregetPassword.setTranslationX(400);
        btnLogin.setTranslationY(400);

        nimoremail.setAlpha(v);
        pass.setAlpha(v);
        passConfirmation.setAlpha(v);
        foregetPassword.setAlpha(v);
        btnLogin.setAlpha(v);

        nimoremail.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(400).start();
        pass.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(600).start();
        passConfirmation.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(800).start();
        foregetPassword.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(1000).start();
        btnLogin.animate().translationY(0).alpha(1).setDuration(2000).setStartDelay(1100).start();

    }
}

