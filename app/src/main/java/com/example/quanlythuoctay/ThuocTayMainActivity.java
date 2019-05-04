package com.example.quanlythuoctay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ThuocTayMainActivity extends AppCompatActivity {
    final int RESULT_PRODUCT_ACTIVITY = 1;
    ArrayList<ThuocTay> listMedicine;
    ThuocTayViewAdapter medicineListViewAdapter;
    ListView listViewProduct;
    QLThuocTayDbHelper medicineDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medicineDbHelper = new QLThuocTayDbHelper(this);


        listMedicine = new ArrayList<>();
        loadDbThuoctay();


        medicineListViewAdapter = new ThuocTayViewAdapter(listMedicine);
        listViewProduct = findViewById(R.id.listthuoctay);
        listViewProduct.setAdapter(medicineListViewAdapter);


        //Thêm dữ liệu
        findViewById(R.id.addbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("isupdate", false);
                intent.setClass(ThuocTayMainActivity.this, Edit_ThuocTay.class);
                startActivityForResult(intent, RESULT_PRODUCT_ACTIVITY);


            }
        });

        //Lắng nghe bắt sự kiện một phần tử danh sách được chọn, mở Activity để soạn thảo phần tử
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThuocTay thuoctay = (ThuocTay) medicineListViewAdapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("isupdate", true);
                intent.putExtra("mathuoc", thuoctay.mathuoc);
                intent.setClass(ThuocTayMainActivity.this, Edit_ThuocTay.class);
                startActivityForResult(intent, RESULT_PRODUCT_ACTIVITY);
            }
        });
    }

    private void loadDbThuoctay() {
        listMedicine.clear();
        listMedicine.addAll(medicineDbHelper.getAllThuocTay());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_PRODUCT_ACTIVITY:
                //Khi đóng Activity EditProduct thì nạp lại dữ liệu
                loadDbThuoctay();
                medicineListViewAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }

    }
}
