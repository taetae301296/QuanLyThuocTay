package com.example.quanlythuoctay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit_ThuocTay extends AppCompatActivity{
    boolean isupdate;
    String editMa;
    EditText editTen;
    EditText editGia;
    EditText editDonvi;
    ThuocTay medicine;

    QLThuocTayDbHelper qlthuoctayDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__thuoc_tay);

        qlthuoctayDbHelper = new QLThuocTayDbHelper(this);

        Intent intent = getIntent();
        isupdate = intent.getBooleanExtra("isupdate", false);
        if (isupdate) {
            //Activity hoạt động biên tập dữ liệu Sản phẩm đã

            //Đọc sản phẩm

            editMa = intent.getStringExtra("ediMa");
            medicine = qlthuoctayDbHelper.getThuocTayByID(editMa);


            findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    qlthuoctayDbHelper.deleteThuocTayByID(editMa);
                    finish();
                }
            });


        } else {
            //Activity nhâp dữ liệu thêm Sản phẩm mới

            medicine = new ThuocTay("", "", 0, "");
            findViewById(R.id.deleteBtn).setVisibility(View.GONE);
            ((Button) findViewById(R.id.save)).setText("Tạo sản phẩm mới");
        }

        //Update to View
        editTen = findViewById(R.id.ettenthuoc);
        editGia = findViewById(R.id.etgiathuoc);
        editDonvi = findViewById(R.id.etdonvi);


        editTen.setText(medicine.tenthuoc);
        editGia.setText(medicine.gia + "");
        editDonvi.setText(medicine.donvitinh);

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicine.tenthuoc = editTen.getText().toString();
                medicine.gia = Integer.parseInt(editGia.getText().toString());
                medicine.donvitinh = editDonvi.getText().toString();

                if (isupdate) {
                    //Cập nhật
                    qlthuoctayDbHelper.updateThuocTay(medicine);
                } else {
                    //Tạo
                    qlthuoctayDbHelper.updateThuocTay(medicine);
                }
                finish();
            }
        });


    }
}
