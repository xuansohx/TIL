package com.example.p474;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2;
    Button button, button2;
  //  boolean flag1=true, flag2=true;

    // main Thread
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
    }

    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            for(int i=0; i<=10; i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("[T]","---"+i);
                final int temp =i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(temp+"");
                    }
                });
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    button.setEnabled(true);
                }
            });

        }
    };

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            for(int i=11; i<=20; i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("[T]","******"+i);
                final int temp =i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText(temp+"");
                    }
                });

            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    button2.setEnabled(true);
                }
            });
        }
    };

    // 별도의 Thread 두 개
    public void clickB1(View view){
       Thread t1 = new Thread(r1);
       t1.start();
       button.setEnabled(false);
    }

    public void clickB2(View view){
        Thread t2 = new Thread(r2);
        t2.start();
        button2.setEnabled(false);
    }
}
