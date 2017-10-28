package com.tensun.firebasedemo6;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/** 將CustomAdapter 會使用到的控件, 在這裡初始化 */
public class MyHolder {

    TextView name;
    TextView message;
    ImageView profilePicture;

    public MyHolder(View itemView) {
        name = (TextView) itemView.findViewById(R.id.name);
        message = (TextView) itemView.findViewById(R.id.message);
        profilePicture = (ImageView) itemView.findViewById(R.id.profilePicture);
    }
}
