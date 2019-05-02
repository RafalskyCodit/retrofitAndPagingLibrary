package com.example.pagingretrofit.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.pagingretrofit.R;

public class FragmentUtils {
    public static void replaceFragment(FragmentManager fm, Fragment fragment){
        fm.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
