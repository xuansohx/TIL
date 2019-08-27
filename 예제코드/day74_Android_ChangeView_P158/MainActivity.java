package com.example.p158;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

   ImageView img;
   ConstraintLayout loginLayer, registerLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUi();
    }

    private void setUi() {
        img = findViewById(R.id.img);
        loginLayer = findViewById(R.id.loginLayer);
        registerLayer = findViewById(R.id.registerLayer);
        //loginLayer.setVisibility(View.INVISIBLE); // 화면에 보여지지 않게 설정
        //registerLayer.setVisibility(View.INVISIBLE);
        disable(); // 위의 두 줄 함수로 만들어 간단히 처리 → frame 생성?
    }

    public void disable(){
        loginLayer.setVisibility(View.INVISIBLE);
        registerLayer.setVisibility(View.INVISIBLE);
    }

    public void clickBt(View view){
        // onclick 설정해두면, 버튼 클릭 시 모든 event를 이 곳에서 처리
        if(view.getId() == R.id.bt1){
            img.setImageResource(R.drawable.d1);
        }else if(view.getId() == R.id.bt2){
            img.setImageResource(R.drawable.d2);
        }else if(view.getId() == R.id.bt3){ // 어떤 버튼에 눌렀는 지에 따라 교차되어 화면 출력
            loginLayer.setVisibility(View.VISIBLE);
            registerLayer.setVisibility(View.INVISIBLE);
        }else if(view.getId() == R.id.bt4){
            loginLayer.setVisibility(View.INVISIBLE);
            registerLayer.setVisibility(View.VISIBLE);
        }
    }

}
