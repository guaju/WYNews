package com.guaju.wynews.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by root on 17-6-2.
 */

public class ToastUtils {
    private static Toast toast;
    public static void show(Context context,String str){
        if (toast==null){
            toast=Toast.makeText(context,str,Toast.LENGTH_SHORT);
        }else{
        toast.setText(str);
        }
        toast.show();
    }
}
