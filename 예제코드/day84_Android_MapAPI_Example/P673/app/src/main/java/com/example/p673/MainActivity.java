package com.example.p673;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        String[] Permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION, // 필요한 permission 여기에
        };

        ActivityCompat.requestPermissions(this, Permissions, 101);

        // permission 물어보기기

       int check = PermissionChecker.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (check == PackageManager.PERMISSION_GRANTED) {
           Toast.makeText(this,"ACCEPT",Toast.LENGTH_SHORT).show();
           startLocationService();
           return;
        } else {
            Toast.makeText(this, "DENY", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void startLocationService() {
        // LogationManager 만듦
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Manager가 현재 위치 찾아 → 경도, 위도 가져옴
        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "최근 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;

                textView.setText(message);
            }

            // Listener에 의해 실시간으로 가져옴
            GPSListener gpsListener = new GPSListener();
            long minTime = 10000; // 10초에 한 번씩 바뀐 정보를 수신
            float minDistance = 0;

            /// Manager가 현재 위치 바뀌는 것 감지하여 반영
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime, minDistance, gpsListener);
            // 네 개의 정보가 들어감
            // gpsListener의 역할? 일정시간마다 호출되어 바뀐 값을 찾아아
            Toast.makeText(getApplicationContext(), "내 위치확인 요청함",
                    Toast.LENGTH_SHORT).show();

        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String message = "내 위치 -> Latitude : "+ latitude + "\nLongitude:"+ longitude;
            textView.setText(message);
        }

        public void onProviderDisabled(String provider) { }

        public void onProviderEnabled(String provider) { }

        public void onStatusChanged(String provider, int status, Bundle extras) { }
    }
}
