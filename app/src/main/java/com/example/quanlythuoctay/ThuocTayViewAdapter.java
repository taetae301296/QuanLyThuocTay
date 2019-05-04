package com.example.quanlythuoctay;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ThuocTayViewAdapter extends BaseAdapter{
    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final ArrayList<ThuocTay> listMedicine;

    ThuocTayViewAdapter(ArrayList<ThuocTay> listMedicine) {
        this.listMedicine = listMedicine;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return listMedicine.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return listMedicine.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return Integer.parseInt(listMedicine.get(position).mathuoc);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.activity_thuoc_tay_view, null);
        } else viewProduct = convertView;

        //Bind sữ liệu phần tử vào View
        ThuocTay medicine = (ThuocTay) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.etmathuoc)).setText(String.format("%s", medicine.mathuoc));
        ((TextView) viewProduct.findViewById(R.id.ettenthuoc)).setText(String.format("%s", medicine.tenthuoc));
        ((TextView) viewProduct.findViewById(R.id.etgiathuoc)).setText(String.format("Giá %d", medicine.gia));
        ((TextView) viewProduct.findViewById(R.id.etdonvi)).setText(String.format("%s", medicine.donvitinh));


        return viewProduct;
    }
}
