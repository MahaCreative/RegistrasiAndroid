package com.example.registrasimahasiswa.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.registrasimahasiswa.Adapter.MahasiswaAdapter;
import com.example.registrasimahasiswa.Api.ApiInterface;
import com.example.registrasimahasiswa.Api.ApiRetroServer;
import com.example.registrasimahasiswa.Model.Data.MahasiswaModel;
import com.example.registrasimahasiswa.Model.Response.MahasiswaResponse;
import com.example.registrasimahasiswa.R;
import com.example.registrasimahasiswa.databinding.ActivityMahasiswaBinding;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MahasiswaActivity extends AppCompatActivity  {

    

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<MahasiswaModel> listDataMahasiswa = new ArrayList<>();
    private SearchView tv_search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        rvData = findViewById(R.id.rv_mahasiswa);
//        Setting Layout Managernya
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        refreshData("");
        tv_search = findViewById(R.id.tv_search);
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

                Toast.makeText(MahasiswaActivity.this, "Kode:" +kode+" |Pesan:" + pesan  , Toast.LENGTH_SHORT).show();

                adData = new MahasiswaAdapter(MahasiswaActivity.this, listDataMahasiswa);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
                Log.e("output", response.body().getData().toString());

            }

            @Override
            public void onFailure(Call<MahasiswaResponse> call, Throwable t) {
                Toast.makeText(MahasiswaActivity.this, "Gagal Menghubungi Server error:" +t.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                Log.e("hasil " + );
            }
        });

    }


}