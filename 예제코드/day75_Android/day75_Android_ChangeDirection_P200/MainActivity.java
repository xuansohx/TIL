package com.example.p200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    View view,view2;
    TextView textView;
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
    }

    // private로 설정하면 외부에서 사용하지 못 함
    private void setUI() {
        view = findViewById(R.id.view);
        view2 = findViewById(R.id.view2);
        textView = findViewById(R.id.textView);
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                printMsg("onDown() 호출됨.");
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                printMsg("onShowPress() 호출됨.");

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                printMsg("onSingleTapup() 호출됨.");
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                printMsg("onScroll() 호출됨 : "+ v + ", " + v1);
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                printMsg("onLongPress() 호출됨.");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                printMsg("onFling() 호출됨 : " + v + ", " + v1);
                return false;
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    printMsg("DOWN: " + curX + " " + curY);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    printMsg("MOVE: " + curX + " " + curY);
                } else if (action == MotionEvent.ACTION_UP) {
                    printMsg("UP: " + curX + " " + curY);
                }
                return true;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

    }

    private void printMsg(String str){
        textView.append(str+"\n");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this,"Back...",Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showToast("방향 : ORIENTATION_LANDSCAPE");
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            showToast("방향 : ORIENTATION_PORTRAIT");
        }

    }

    public void showToast(String daga){
        Toast.makeText(this,daga,Toast.LENGTH_LONG).show();
    }
}
