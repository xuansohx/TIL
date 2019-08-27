package com.example.p115;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt2 = findViewById(R.id.button26);
        // 화면에 있는 두 번째 버튼 정보 받아옴
        // R. 뒤에 이미지인지 위젯인지 정보 입력하면 가져올 수 있음
    }

    public void btclick(View view) {
        bt2.setBackgroundColor(Color.RED);
        bt2.setText("Clicked");
    }

    }



