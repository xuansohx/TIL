package com.example.p247;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends AppCompatActivity {

    TextView textView2;
    ImageView imageView;

    String name;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView2 = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        name = intent.getStringExtra("str");
        textView2.setText(name);

        image = intent.getIntExtra("img",0);
        imageView.setImageResource(image);

    }

    public void clickBt(View view){
        Intent intent = new Intent();
        intent.putExtra("imagename",name);
        intent.putExtra("file", image);
        setResult(RESULT_OK,intent);
        finish(); // 뒤로가기
    }


}
