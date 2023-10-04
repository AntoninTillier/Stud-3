package com.pormob.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pormob.fragments.Fragments.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DetailFragment fragment = new DetailFragment();
    }
}