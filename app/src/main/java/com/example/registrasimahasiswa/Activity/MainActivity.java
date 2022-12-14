package com.example.registrasimahasiswa.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registrasimahasiswa.Fragment.DashboardFragment;
import com.example.registrasimahasiswa.Fragment.MahasiswaFragment;
import com.example.registrasimahasiswa.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
//    private FirebaseUser firebaseUser;
    private TextView tvUser;
    TextView tvHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gantiFragment(new DashboardFragment());
        //        Set Sidebar
        //        inisialisasi user Login


        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);


        //        Cara mendapatkan menu item
        Menu menu = navigationView.getMenu();
        MenuItem navMenu = menu.findItem(R.id.nav_mahasiswa);


        tvHeader = drawer.findViewById(R.id.title_header);



        pb = (ProgressBar) findViewById(R.id.progres);
        gantiFragment(new DashboardFragment());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawer.closeDrawer(GravityCompat.START);
                switch (id){
                    case R.id.nav_dashboard:
                        Toast.makeText(MainActivity.this, "Dashboard telah di klick", Toast.LENGTH_SHORT).show();
                        gantiFragment(new DashboardFragment());
                        navMenu.setVisible(false);
                        break;
                    case R.id.nav_mahasiswa:
                        gantiFragment(new MahasiswaFragment());
                        tvHeader.setText("Mahasiswa");
                        break;
                    case R.id.nav_logout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    private void gantiFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_layout, fragment);
        ft.commit();
    }
}