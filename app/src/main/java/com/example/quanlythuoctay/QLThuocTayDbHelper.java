package com.example.quanlythuoctay;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QLThuocTayDbHelper extends SQLiteOpenHelper{
    private static final String TAG = "QLThuocTayDbHelper";
    private static final String DATABASE_NAME = "qlthuoctay.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_THUOCTAY = "thuoctay";

    public QLThuocTayDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Phương thức này tự động gọi nếu storage chưa có DATABASE_NAME
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG, "Create table");
        String queryCreateTable_ThuocTay = "CREATE TABLE " + TABLE_THUOCTAY + " ( " +
                "mathuoc VARCHAR (20) PRIMARY KEY UNIQUE, " +
                "tenthuoc VARCHAR (255) NOT NULL, " +
                "dongia DECIMAL DEFAULT (0) NOT NULL, " +
                "donvitinh VARCHAR (20) NOT NULL " +
                ")";

        db.execSQL(queryCreateTable_ThuocTay);
    }

    //Phương thức này tự động gọi khi đã có DB trên Storage, nhưng phiên bản khác
    //với DATABASE_VERSION
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Xoá bảng cũ
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THUOCTAY);
        //Tiến hành tạo bảng mới
        onCreate(db);
    }

    public List<ThuocTay> getAllThuocTay() {

        List<ThuocTay> medicine = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT mathuoc, tenthuoc, gia, donvitinh from thuoctay", null);

        //Đến dòng đầu của tập dữ liệu
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String medicineID = cursor.getString(0);
            String medicineName = cursor.getString(1);
            int medicinePrice = cursor.getInt(2);
            String medicineUnit = cursor.getString(3);

            medicine.add(new ThuocTay(medicineID, medicineName, medicinePrice, medicineUnit));
            cursor.moveToNext();
        }

        cursor.close();

        return medicine;
    }

    public ThuocTay getThuocTayByID(String ID) {
        ThuocTay medicine = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT mathuoc, tenthuoc, gia, donvitinh from thuoctay where mathuoc = ?",
                new String[]{ID});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String medicineID = cursor.getString(0);
            String medicineName = cursor.getString(1);
            int medicinePrice = cursor.getInt(2);
            String medicineUnit = cursor.getString(3);

            medicine = new ThuocTay(medicineID, medicineName, medicinePrice, medicineUnit);
        }
        cursor.close();
        return medicine;
    }

    void updateThuocTay(ThuocTay medicine) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE thuoctay SET mathuoc = ?, tenthuoc = ?, gia = ?, donvitinh = ? where mathuoc = ?",
                new String[]{medicine.mathuoc, medicine.tenthuoc, medicine.gia + "", medicine.donvitinh});
    }

    void insertThuocTay(ThuocTay medicine) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO product (tenthuoc, gia, donvitinh) VALUES (?,?,?)",
                new String[]{medicine.tenthuoc, medicine.gia + "", medicine.donvitinh});
    }

    void deleteThuocTayByID(String medicineID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM thuoctay where mathuoc = ?", new String[]{String.valueOf(medicineID)});
    }
}
