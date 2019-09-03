package com.example.p488;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2, textView3, textView4, textView5, textView6;
    Button button;
    Handler handler;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        button = findViewById(R.id.button);

        //TextView Connection
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            // Thread 실행 시간 임의로 100으로 제한
            for(int i=0; i<=100; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                random = new Random();

                // Speed
                int num;
                num = random.nextInt(8);
                int speed = Integer.parseInt(textView4.getText().toString());

                if(speed>220) {
                    speed -= num;
                } else if (speed<=0){
                    speed += num;
                } else if(num<4){
                    speed -= num;
                }else
                    speed += num;

                final int speedData = speed;

                // RPM
                int num2;
                num2 = random.nextInt((int) speedData + 80);
                int rpm = Integer.parseInt(textView6.getText().toString());

                if(rpm>3000){
                    rpm -= num2;
                }else rpm += num2;

                final int rpmData = rpm;

                // Average
                int average = rpmData/speedData;
                final int averageData = average;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText(speedData+"");
                        textView4.setText(rpmData+"");
                        textView6.setText(averageData+"");
                    }
                });
            }
            button.setEnabled(true);
        }
    };



    // START Button
    public void clickBt(View view){
        button.setEnabled(false);
        Thread t = new Thread(r);
        t.start();

    }




}
