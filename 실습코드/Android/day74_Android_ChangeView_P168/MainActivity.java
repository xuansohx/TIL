package com.example.p168;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView topimg;
    ImageView bottomimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
    }

    private void setUI(){
       topimg = findViewById(R.id.top);
       bottomimg = findViewById(R.id.bottom);
       topimg.setImageResource(R.drawable.img01);
    }

    public void clickBt(View view){
        if(view.getId() == R.id.btup){
            topimg.setVisibility(View.VISIBLE);
            bottomimg.setVisibility(View.INVISIBLE);
        }else if(view.getId() == R.id.btdown){
            bottomimg.setImageResource(R.drawable.img01);
            bottomimg.setVisibility(View.VISIBLE);
            topimg.setVisibility(View.INVISIBLE);
        }
    }
}
