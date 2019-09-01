package com.example.p246;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        Intent intent = getIntent();
        int num = intent.getIntExtra("num",0);
        String str = intent.getStringExtra("str");
        textView.setText(num+" "+str);
    }

    public void clickBt(View view){
        finish();
    }

    public void clickBt2(View view){
        Intent intent = new Intent(getApplicationContext(),Main3Activity.class); // go MAIN3
        startActivityForResult(intent,100); // 값을 보냄
    }

    @Override // 세 번째 페이지(MAIN 3)의 값을 받음
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            int nn = data.getIntExtra("nm",0);
            String st = data.getStringExtra("st");
            textView2.setText(nn+" "+st);
        }
    }
}
