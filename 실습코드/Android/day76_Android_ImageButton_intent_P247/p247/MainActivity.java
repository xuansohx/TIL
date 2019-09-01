package com.example.p247;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }

    public void clickBt(View view){
        // 액티비티 간 화면 이동
        //Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);

        // 버튼에 따라 다른 이미지와 이름 전송
        // 버튼의 아이디 값과 비교하여, 해당하는 사진의 아이디 값을 intent에 저장 + 이름
        if(view.getId() == R.id.button){
            intent.putExtra("str","Grape");
            intent.putExtra("img",R.drawable.img01);
        }else if(view.getId() == R.id.button2){
            intent.putExtra("str","Orange");
            intent.putExtra("img",R.drawable.img02);
        }else if(view.getId() == R.id.button3){
            intent.putExtra("str","Strawberry");
            intent.putExtra("img",R.drawable.img03);
        }

        startActivityForResult(intent,100);
        // 이거 맨 마지막에 써야 전송 됨
        // 중간에 쓰면 아래 코드가 실행되지 않음
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){
            String st = data.getStringExtra("imagename");
            int nn = data.getIntExtra("file",0);
            textView.setText(nn);
        }


    }
}
