package com.example.pagingretrofit.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pagingretrofit.R;
import com.example.pagingretrofit.utils.FragmentUtils;
import com.example.pagingretrofit.view.fragment.ResultFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentUtils.replaceFragment(getSupportFragmentManager(), new ResultFragment());
    }
}
