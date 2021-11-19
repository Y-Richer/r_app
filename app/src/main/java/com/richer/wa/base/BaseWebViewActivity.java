package com.richer.wa.base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivityBaseWebViewBinding;
import com.richer.wa.CommonHeaderView;

/**
 * create by richer on 2021/10/15
 */
public class BaseWebViewActivity extends BaseActivity<BaseViewModel> implements CommonHeaderView.OnHeaderClickListener, View.OnClickListener {

    @Override
    public void setClazz() {
        clazz = BaseViewModel.class;
    }

    ActivityBaseWebViewBinding mBinding;

    private String url = "https://wanandroid.com";
    private String title;

    public static void start(Context context, String url, String title) {
        Intent intent = new Intent(context, BaseWebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void initActivity() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_web_view);
        initView();
    }

    private void initView() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");

        initWebView();

        mBinding.headerWeb.init(title, this);
        mBinding.headerWeb.setMenuImage(ContextCompat.getDrawable(this, R.drawable.ic_safari_787878));

        mBinding.ivLeftButtonWeb.setOnClickListener(this);
        mBinding.ivRightButtonWeb.setOnClickListener(this);

    }

    private void initWebView() {
        mBinding.webview.loadUrl(url);
        WebSettings settings = mBinding.webview.getSettings();
        mBinding.webview.setWebViewClient(new BaseWebViewClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        mBinding.webview.setWebChromeClient(new BaseWebChromeClient());

        //支持缩放
        settings.setSupportZoom(true);

        //让h5能够识别出本APP
        String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + "; ****");
    }

    @Override
    public void onClickBack(View view) {
        finish();
    }

    @Override
    public void onClickMenu(View view) {
        Uri contentUrl = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, contentUrl);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left_button_web:
                mBinding.webview.goBack();
                break;
            case R.id.iv_right_button_web:
                mBinding.webview.goForward();
                break;
            default:
                break;
        }
    }

    static class BaseWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!TextUtils.isEmpty(url)) {
                if (url.startsWith("http") || url.startsWith("https")) {
                    view.loadUrl(url);
                }
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

    }

    static class BaseWebChromeClient extends WebChromeClient {

    }

}