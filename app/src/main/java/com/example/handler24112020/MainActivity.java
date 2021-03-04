package com.example.handler24112020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                switch (message.what){
                    case 1 :
                        Toast.makeText(MainActivity.this, "Xử lý xong cho chuỗi " + message.getData().getString("string"), Toast.LENGTH_SHORT).show();
                        break;
                    case 2 :
                        Toast.makeText(MainActivity.this, "Xử lý xong cho số " +message.getData().getInt("number") + "" , Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("string","hello");
                message.setData(bundle);
                handler.sendMessage(message);
                try {
                    Thread.sleep(3000);
                    Message message2 = new Message();
                    message2.what = 2;
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("number",123);
                    message2.setData(bundle1);
                    handler.sendMessage(message2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();



    }
}