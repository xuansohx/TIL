package com.example.p234;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ProgressBar progressBar;
    EditText editText;
    RadioButton radioButton1, radioButton2, radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
    }

    public void mainbtn(View view){

        // main의 button을 누르면 선호도 조사 페이지로 이동(choosepage)
        if(view.getId() == R.id.button) {
            // xml 파일에 만든 layout 호출
            LayoutInflater inflater = getLayoutInflater();
            View cview = inflater.inflate(R.layout.choosepage,(ViewGroup) findViewById(R.id.fruits));

            // 동작위치 및 표시위치
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("선호도 조사");
            builder.setMessage("좋아하는 과일을 선택해주세요.");
            builder.setIcon(R.drawable.icon1);
            builder.setView(cview); // xml 파일 띄우기

            // 라디오버튼 값 받음 - 설문조사 선택한 값
            radioButton1 = cview.findViewById(R.id.radioButton1);
            radioButton2 = cview.findViewById(R.id.radioButton2);
            radioButton3 = cview.findViewById(R.id.radioButton3);

            editText = cview.findViewById(R.id.editText);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    // 선택한 과일의 사진 ImageView에 띄움
                    if(radioButton1.isChecked() == true){
                        imageView.setImageResource(R.drawable.img01);
                    }else if(radioButton2.isChecked() == true){
                        imageView.setImageResource(R.drawable.img02);
                    }else if(radioButton3.isChecked() == true){
                        imageView.setImageResource(R.drawable.img03);
                    }

                    // 입력한 숫자 받아서 progressBar로 나타내기
                    String str = editText.getText().toString(); // editText에 입력한 값을 받음
                    if(str.equals("")){
                        progressBar.setProgress(0);
                    }
                    else{
                        progressBar.setProgress(Integer.parseInt(str));
                    }
                    //int number = Integer.parseInt(str); // String을 int로 변환
                    // progressBar.setProgress(number); // int로 변한 값을 progressBar에 입력

                }
            });

            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();

        }

    }

}
