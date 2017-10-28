package com.tensun.firebasedemo6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * 自定義的Adapter
 */
public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<User> users;
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    /**
     * 告诉listview要显示多少个条目
     */
    @Override
    public int getCount() {
        return users.size();
    }

    /**
     * 訪問列表數據的一種更簡潔的方法 , 例如透過CustomAdapter.getItem()
     */
    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    /**
     * 訪問列表數據的一種更簡潔的方法 , 例如透過CustomAdapter.getItemId()
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 告诉Listview条目上显示的内容, 返回一个View对象作为条目上的内容展示, 该方法返回什么样的view, Listview的条目上就显示什么样的view
     * 屏幕上每显示一个条目 getview()就会被调用一次
     *
     * @param convertview 曾经使用过的view对象, 可以被重复使用, 使用前要判断
     * @return 返回一个View对象作为条目上的内容展示
     */
    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertview == null) {                                                                  // 預載convertview, 之後都不太會再被調用, 除非convertview 發生改變, 比如說動態新增一筆資料..等
            convertview = inflater.inflate(                                                         // 將convertview 添上listview_layout的布局架構
                    R.layout.listview_layout, viewGroup, false);
        }

        MyHolder holder = new MyHolder(convertview);
        holder.name.setText(users.get(i).getName());                                                // 加載姓名
        holder.message.setText(users.get(i).getMessage());                                          // 加載訊息內容
        PicassoClient.downloadimg(                                                                  // 加載大頭貼圖片
                context, users.get(i).getProfilePictureUrl(), holder.profilePicture);

        return convertview;
    }
}
