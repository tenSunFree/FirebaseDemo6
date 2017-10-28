package com.tensun.firebasedemo6;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * Q: com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "xxx"
 * A: 檢查Firebase 對應的資料庫裡面, 是否有其它不必要的名稱
 */

public class FirebaseClient {

    Context context;
    String DB_URL;
    ListView listView;
    Firebase firebase;
    ArrayList<User> users = new ArrayList<>();
    CustomAdapter customAdapter;

    public FirebaseClient(Context context, String DB_URL, ListView listView) {

        this.context = context;
        this.DB_URL = DB_URL;
        this.listView = listView;

        Firebase.setAndroidContext(context);
        firebase = new Firebase(DB_URL);                                                            // 創建一個 包含著資料庫網址的Firebase實體
    }

    /** 將資料弄成一個物件, 透過firebase 相關方法上傳到指定的資料庫中 */
    public void savedata(String name, String message, String profilePictureUrl) {

        User user = new User();
        user.setName(name);
        user.setMessage(message);
        user.setProfilePictureUrl(profilePictureUrl);

        firebase.child("user").push().setValue(user);
    }

    public void refreshdata() {

        firebase.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {                         // 將資料庫的資料 加載到Listview
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {                       // 如果資料庫有欄位異動, 就會同步刷新Listview
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void getupdates(DataSnapshot dataSnapshot) {

        users.clear();                                                                              // 防止資料更新的過程, 會導致資料重複顯示的情況

        for (DataSnapshot ds : dataSnapshot.getChildren()) {                                        // 取出資料庫的所有資料, 放入users中
            User user = new User();
            user.setName(ds.getValue(User.class).getName());
            user.setMessage(ds.getValue(User.class).getMessage());
            user.setProfilePictureUrl(ds.getValue(User.class).getProfilePictureUrl());
            users.add(user);
        }

        if (users.size() > 0) {
            customAdapter = new CustomAdapter(context, users);
            listView.setAdapter((ListAdapter) customAdapter);

            listView.setDividerHeight(1);                                                           // 取消ListView 預設分隔線
            listView.setDivider(new ColorDrawable(Color.GRAY));                                    // 取消ListView 預設分隔線
        } else {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}
