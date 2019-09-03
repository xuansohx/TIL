package com.example.p478;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    CountHadler countHadler;
    Button button;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        countHadler = new CountHadler();
        handler = new Handler();
    }

   Runnable r = new Runnable() {
       @Override
       public void run() {
           for (int i = 0; i <= 10; i++) {
               try {
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               Log.d("[T]", "*****" + i);
               Message message = countHadler.obtainMessage();
               // Handler에서 Message 꺼냄
               Bundle bundle = new Bundle();
               bundle.putInt("cnt", i);
               message.setData(bundle);
               countHadler.sendMessage(message); // 변화
               // Thread에서 Message를 Activity로 보냄
               // Sub에서 Main으로 전달
           }

           handler.post(new Runnable() {
               @Override
               public void run() {
                   button.setEnabled(true);
               }
           });
       }
   } ;

    class CountHadler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            int value = bundle.getInt("cnt");
            textView.setText(value+"");
        }
    }

    public void clickBt(View view){
        Thread t = new Thread(r);
        t.start();
        button.setEnabled(false); // 버튼을 클릭하면 비활성화
    }

    public void clickBt2(View view){
       final ProgressDialog progressDialog = new ProgressDialog(this);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("dialog");
        dialog.setMessage("5 seconds...");
        dialog.setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                progressDialog.show();
                // 누르자마자 동작이 되면 안 될 때 사용
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        textView.setText("NEXT OK");
                    }
                },5000);
            }
        });
        dialog.show();
    }
}
