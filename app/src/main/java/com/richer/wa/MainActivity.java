package com.richer.wa;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivityMainBinding;
import com.richer.wa.home.model.ArticleListResponseModel;
import com.richer.wa.network.NetUtil;
import com.richer.wa.utils.StatusBarUtil;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

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

        mBinding.btnChangeMain.setOnClickListener(v -> {
            NetUtil.getHomeApi().getArticleList(0)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObservable<ArticleListResponseModel>() {
                        @Override
                        protected void onSuccess(ArticleListResponseModel articleListResponseModel) {
                            Log.d("MainActivity", "onSuccess: " + articleListResponseModel.getData().getTotal());
                        }

                        @Override
                        protected void onFail(int errorCode, String errorMsg) {
                            Log.d("MainActivity", "onFail: "+errorCode + ": "+errorMsg);
                        }
                    });
        });

    }
}