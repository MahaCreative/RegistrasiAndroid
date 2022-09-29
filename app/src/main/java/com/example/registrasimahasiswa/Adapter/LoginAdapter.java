package com.example.registrasimahasiswa.Adapter;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.registrasimahasiswa.Fragment.LoginTabFragment;
import com.example.registrasimahasiswa.Fragment.SignUpTabFragment;

public class LoginAdapter extends FragmentPagerAdapter {
    private Context ctx;
    int totalTabs;
    public LoginAdapter(FragmentManager fm, Context ctx, int totalTabs){
        super(fm);
        this.ctx = ctx;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position){
        Log.e("yyy" , String.valueOf(position));
        switch(position){
            case 0:
                LoginTabFragment loginTabFragment = new LoginTabFragment();
                Log.e("hy", "hy");
                return loginTabFragment;
            case 1:
                SignUpTabFragment signUpTabFragment = new SignUpTabFragment();
                Log.e("hy", "yyy");
                return signUpTabFragment;
            default:
                return null;
        }
    }
}
