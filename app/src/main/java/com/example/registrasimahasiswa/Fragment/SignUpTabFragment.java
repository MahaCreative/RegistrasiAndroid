package com.example.registrasimahasiswa.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.registrasimahasiswa.Activity.MainActivity;
import com.example.registrasimahasiswa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpTabFragment extends Fragment {
    private EditText nim,email,username,pass,passConfirmation;
    private Button register;
    private String vnim ,vemail,vpass, vconfirm, vusername;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup group=(ViewGroup) inflater.inflate(R.layout.sign_up_tab_fragment, container, false);
        defineId(group);
        styleAnimation();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.setCancelable(false);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vnim = nim.getText().toString();
                vemail = email.getText().toString();
                vpass = pass.getText().toString();
                vusername = username.getText().toString();
                vconfirm = passConfirmation.getText().toString();
                register(vnim,vemail,vusername,vconfirm);

            }
        });

        return  group;
    }

    private void register(String nim, String email, String username, String passConfimation) {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, passConfimation).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() && task.getResult() != null){
                    FirebaseUser firebaseUser = task.getResult().getUser();
                   if(firebaseUser != null){
                       UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                       firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
//                               Send Email Verivication
                               firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if (task.isSuccessful()){
                                           Toast.makeText(getActivity(), "Email Verivication has Sending Your Email", Toast.LENGTH_LONG).show();
                                       }else{
                                           Toast.makeText(getActivity(), "Email Verivication has Sending Your Email", Toast.LENGTH_LONG).show();
                                       }
                                   }
                               });

                               reload();

                           }
                       });
                   }else{
                       Toast.makeText(getActivity(), "Register Gagal Dilakukan", Toast.LENGTH_LONG).show();
                   }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void defineId(ViewGroup group){
        nim = group.findViewById(R.id.nim);
        email = group.findViewById(R.id.email);
        username = group.findViewById(R.id.username);
        pass = group.findViewById(R.id.pass);
        passConfirmation = group.findViewById(R.id.confirm_pass);
        register = group.findViewById(R.id.btnSignup);

    }

    private void reload() {
        startActivity(new Intent(getContext(), MainActivity.class));
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }




    private void styleAnimation(){
        nim.setTranslationX(-800);
        email.setTranslationX(-800);
        username.setTranslationX(-800);
        pass.setTranslationX(-800);
        passConfirmation.setTranslationX(-800);
        register.setTranslationY(800);


        //    private String
        float v = 0;
        nim.setAlpha(v);
        email.setAlpha(v);
        username.setAlpha(v);
        pass.setAlpha(v);
        passConfirmation.setAlpha(v);
        register.setAlpha(v);



        nim.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(400).start();
        email.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(600).start();
        username.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(800).start();
        pass.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(1000).start();
        passConfirmation.animate().translationX(0).alpha(1).setDuration(1500).setStartDelay(1200).start();
        register.animate().translationY(0).alpha(1).setDuration(1500).setStartDelay(1400).start();


    }

}
