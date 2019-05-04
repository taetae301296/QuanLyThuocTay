package com.example.quanlythuoctay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button thuoctay, hoadon, chitietban, hieuthuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }
    private void setControl(){
        thuoctay = findViewById(R.id.btnThuocTay);
        hieuthuoc = findViewById(R.id.btnHieuThuoc);
        hoadon = findViewById(R.id.btnHoaDon);
        chitietban = findViewById(R.id.btnChiTietBan);
    }

    private void setEvent(){
        thuoctay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThuocTayMainActivity.class);
                startActivity(intent);
            }
        });

        hieuthuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HieuThuocMainActivity.class);
                startActivity(intent);
            }
        });

        hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HoaDonMainActivity.class);
                startActivity(intent);
            }
        });

        chitietban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChiTietBanMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
