package com.tensun.firebasedemo6;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by Administrator on 2017/10/28.
 */

/** 自定義Dialog, 設定對應的布局 以及Style */
public class MyDialog extends Dialog {

    public MyDialog(Context context, int layout, int style) {
        super(context, style);
        setContentView(layout);
    }
}
