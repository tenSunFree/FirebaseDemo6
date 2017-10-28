package com.tensun.firebasedemo6;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/** 實現 透過Picasso 來處理圖片的加載顯示 */
public class PicassoClient {

    public static void downloadimg(Context c, String url, ImageView img) {

        if (url != null && url.length() > 0) {                                                      // 如果url不為空值, 而且是有內容的
            Picasso.with(c).load(url).placeholder(R.drawable.placeholder).into(img);                   // 未讀取完成之前, 暫時顯示placeholder, 讀取完成之後 顯示該圖片
        } else {
            Picasso.with(c).load(R.drawable.placeholder).into(img);                                    // 直接讀取placeholder, 因為沒有url
        }
    }
}
