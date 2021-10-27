package com.richer.wa.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.richer.richers.richer_wa.R;
import com.richer.wa.RApplication;
/**
 * create by yangrunqiu on 2021/10/27
 * 全局Toast
 */
public class RToast {

    private static Toast sToast;

    public static void show(String text) {
        Toast toast = getRToast();
        ((TextView) toast.getView().findViewById(R.id.tv_r_toast)).setText(text);
        toast.show();
    }

    private static Toast getRToast() {
        if (sToast == null) {
            sToast = new Toast(RApplication.getInstance());
            View view = LayoutInflater.from(RApplication.getInstance()).inflate(R.layout.layout_r_toast, null);
            sToast.setDuration(Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.CENTER, 0, 0);
            sToast.setView(view);
        }
        return sToast;
    }

}
