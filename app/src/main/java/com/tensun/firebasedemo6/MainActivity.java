package com.tensun.firebasedemo6;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    /** 設定資料庫的網址, 記得添加對應的 google-services.json檔案 */
    final static String DB_URL = "";

    EditText nameeditText, messageEditText, urleditText;
    Button btnsave;
    ListView listView;
    FirebaseClient firebaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        firebaseClient = new FirebaseClient(this, DB_URL, listView);
        firebaseClient.refreshdata();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog(MainActivity.this);
            }
        });
    }

    /** 創建一個自定義的Dialog */
    private void displayDialog(final Activity activity) {

        final MyDialog dialog = new MyDialog(
                this, R.layout.customdialog_layout, R.style.dialog);
        dialog.setCancelable(true);                                                                 // 设置对话框是否可以被取消, 如果是true, 则点击非对话框区域 对话框消失, false则刚好相反

        nameeditText = (EditText) dialog.findViewById(R.id.nameEditText);
        messageEditText = (EditText) dialog.findViewById(R.id.messageEditText);
        urleditText = (EditText) dialog.findViewById(R.id.urlEditText);

        btnsave = (Button) dialog.findViewById(R.id.saveBtn);
        btnsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {                                                        // 將EditText 輸入的資料, 上傳到資料庫中

                firebaseClient.savedata(
                        nameeditText.getText().toString(),
                        messageEditText.getText().toString(),
                        urleditText.getText().toString()
                );
            }
        });

        dialog.show();
    }
}
