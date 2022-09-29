package com.example.registrasimahasiswa.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.registrasimahasiswa.Activity.MahasiswaActivity;
import com.example.registrasimahasiswa.Adapter.MahasiswaAdapter;
import com.example.registrasimahasiswa.Api.ApiInterface;
import com.example.registrasimahasiswa.Api.ApiRetroServer;
import com.example.registrasimahasiswa.Model.Data.MahasiswaModel;
import com.example.registrasimahasiswa.Model.Response.MahasiswaResponse;
import com.example.registrasimahasiswa.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MahasiswaFragment extends Fragment {


    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<MahasiswaModel> listDataMahasiswa = new ArrayList<>();
    private SearchView tv_search;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup group = (ViewGroup) inflater.inflate(R.layout.fragment_mahasiswa, container, false);
        rvData = group.findViewById(R.id.rv_mahasiswa);
//        Setting Layout Managernya
        lmData = new GridLayoutManager(getActivity(), 1);

        progressBar = group.findViewById(R.id.progress);
        rvData.setLayoutManager(lmData);
        progressBar.setVisibility(View.VISIBLE);

        refreshData("");
        progressBar.setVisibility(View.GONE);
        tv_search = group.findViewById(R.id.tv_search);
        tv_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                refreshData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                refreshData(newText);
                return false;
            }
        });
        return group;

    }
    public void refreshData(String search){
        ApiInterface apiData = ApiRetroServer.konekRetro().create(ApiInterface.class);
        Call<MahasiswaResponse> tampilDataMahasiswa = apiData.ardFetchData(search);

        tampilDataMahasiswa.enqueue(new Callback<MahasiswaResponse>() {
            @Override
            public void onResponse(Call<MahasiswaResponse> call, Response<MahasiswaResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listDataMahasiswa = response.body().getData();

                Toast.makeText(getActivity(), "Kode:" +kode+" |Pesan:" + pesan  , Toast.LENGTH_SHORT).show();
                if(response.body().getPesan().toString().equals("Data Tidak Tersedia")){
                    Toast.makeText(getActivity(), "Error data tidak ditemukan", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility(View.GONE);
                }
                adData = new MahasiswaAdapter(getActivity(), listDataMahasiswa);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<MahasiswaResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungi Server error:" +t.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                Log.e("hasil " + );
            }
        });

    }
}