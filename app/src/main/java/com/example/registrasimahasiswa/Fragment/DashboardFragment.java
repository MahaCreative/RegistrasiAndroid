package com.example.registrasimahasiswa.Fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.registrasimahasiswa.R;


public class DashboardFragment extends Fragment {

    private CardView operator, pengelola, mahasiswa, registrasi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup group = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);
        inisialisasiId(group);
        return group;

    }

    private void inisialisasiId(ViewGroup group){
        operator = group.findViewById(R.id.men_operator);
        pengelola = group.findViewById(R.id.men_pengelola);
        mahasiswa = group.findViewById(R.id.men_mahasiswa);
        setAnimation();
        clickHandler(group);
    }

    private void clickHandler(ViewGroup group) {
        operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gantiFragment(new OperatorFragment());
            }
        });
        mahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gantiFragment(new MahasiswaFragment());
            }
        });
    }

    private void setAnimation(){
        operator.setTranslationY(400);
        pengelola.setTranslationY(400);

        operator.setAlpha(0);
        pengelola.setAlpha(0);

        operator.animate().translationY(0).alpha(1).setDuration(1500).setStartDelay(200).start();
        pengelola.animate().translationY(0).alpha(1).setDuration(1500).setStartDelay(400).start();
    }
    private void gantiFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_layout, fragment);
        ft.commit();


    }
}