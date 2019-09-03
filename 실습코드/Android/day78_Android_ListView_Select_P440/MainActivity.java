package com.example.p440;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    // main
    ArrayList<Item> list;

    ListView listView;
    LinearLayout container;
    ItemAdapter itemAdapter;
    TextView textView, textView2;
    EditText editText, editText3;
    RatingBar ratingBar;

    Spinner spinner;
    ArrayList<Integer> slist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText); // 이름
        editText3 = findViewById(R.id.editText3); // 전화번호

        // 처음부터 일부 데이터 list 떠야되므로 onCreate에서 동작
        getData();
        itemAdapter = new ItemAdapter(list); // 가져온 data를 adapter에 넣음
        container = findViewById(R.id.container);
        listView = findViewById(R.id.listView);
        listView.setAdapter(itemAdapter); // data를 넣은 itemAdapter를 listView에 붙여 화면에 표시
        listView.setOnItemClickListener(this);

        // Spinner
        spinner = findViewById(R.id.spinner);
        getSpinnerData();

        ArrayAdapter<Integer> adapter =  new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,slist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // 떨어지는 아이템
        spinner.setAdapter(adapter);
       // spinner.setOnItemSelectedListener(this);

        // 전화 권한 확인
        /*
        String[] Permissions = {
                Manifest.permission.CALL_PHONE,
        };
        ActivityCompat.requestPermissions(this, Permissions, 101);

         */
    }

    private void getSpinnerData(){
        slist = new ArrayList<>();
        slist.add(R.drawable.man);
        slist.add(R.drawable.women);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Item item = list.get(list.size() -i -1);
        // 거꾸로 데이터를 출력했으므로 이렇게 써줌
        // 거꾸로 돌려서 최신 데이터 가져옴
        Toast.makeText(this,""+item.getPhone(), Toast.LENGTH_SHORT).show();

        // 전화권한 확인 및 발신

        int permission = PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        Intent intent = new Intent();
        if(permission == PackageManager.PERMISSION_GRANTED){
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01022221111"));
            startActivity(intent);
        }else {
            Toast.makeText(this,"권한부여가 안되어있습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        float temp = ratingBar.getRating()+1;
        ratingBar.setRating(temp);
    }

    class ItemAdapter extends BaseAdapter {

        ArrayList<Item> alist;

        public ItemAdapter(){

        }

        public ItemAdapter(ArrayList<Item> alist){
            this.alist = alist;
        }


        public void addItem(Item item){
            alist.add(item);
            list = alist;
        }

        @Override
        public int getCount() {
            return alist.size();
        }

        @Override
        public Object getItem(int i) {
            return alist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        // data의 개수만큼 getView를 실행 → 실행될 때마다 listView에 붙음
        // 우리가 만든 list를 Adapter에 저장 후, Adapter를 listView에 붙임
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            // User List
            View myview = null;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = inflater.inflate(R.layout.layout,container,true);
            // 'layout'이라는 xml에서 'container'를 가져와 'myview'에 넣어 return

            ImageView iv = myview.findViewById(R.id.imageView); // 이미지
            TextView tx1 = myview.findViewById(R.id.textView3); // 이름
            TextView tx2 = myview.findViewById(R.id.textView5); // 전화번호

            iv.setImageResource(alist.get(alist.size() -i -1).getImgId()); // 이미지
            tx1.setText(alist.get(alist.size() -i -1).getName()); // 이름
            tx2.setText(alist.get(alist.size() -i -1).getPhone()); // 전화번호

            // Rating Bar

         ratingBar = myview.findViewById(R.id.ratingBar);

            ratingBar.setNumStars(8);
            ratingBar.setStepSize(1);
            ratingBar.setMax(8);
            ratingBar.setRating(0);

            final int a = i;
            ratingBar.setRating(alist.get(a).num);
            myview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alist.get(a).plus();
                    itemAdapter.notifyDataSetChanged();
                }
            });

            return myview;

        }
    }

    // User Add Button
    public void clickBt(View view){
        String name = editText.getText().toString();
        String phone = editText3.getText().toString();
        int imgId = parseInt(spinner.getSelectedItem().toString());
        int num= 0;

        itemAdapter.addItem(new Item(name, phone, imgId, num));
        itemAdapter.notifyDataSetChanged(); // refresh
        Toast.makeText(this,"User List가 추가되었습니다.",Toast.LENGTH_SHORT).show();
    }

    private void getData() { // data를 가져오는 함수
        list = new ArrayList<>();
        list.add(new Item("안","010-1234-5678",R.drawable.doctor,0));
        list.add(new Item("안소","010-2345-6789",R.drawable.nurse,0));
        list.add(new Item("안쏘","010-3456-7890",R.drawable.teacher,0));
    }
}

