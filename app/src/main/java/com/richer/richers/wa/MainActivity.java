package com.richer.richers.wa;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivityMainBinding;
import com.richer.richers.wa.utils.StatusBarUtil;

/**
 * create by richer on 2021/10/12
 * MainActivity
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarTransparent(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}