package com.example.quizz_app.ui.CauHoi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;

//Class Lấy ra các cơ sở dữ liệu
public class CauHoiController {
        private DBHelper dbHelper;

    public CauHoiController(Context context) {
    dbHelper = new DBHelper(context);
    }
    public ArrayList<CauHoi> getCauHoi(String MaBaiHoc){
        ArrayList<CauHoi> listCauHoi= new ArrayList<CauHoi>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM CauHoi Where MaBaiHoc ='"+MaBaiHoc+"'",null);
        cursor.moveToFirst();
        do {
            CauHoi item;
            item = new CauHoi(cursor.getString(0),cursor.getString(1),
                    cursor.getString(2),cursor.getString(3),
                    cursor.getString(4), cursor.getString(5),cursor.getString(6));
            listCauHoi.add(item);
        }while(cursor.moveToNext());
        return listCauHoi;
    }
    //Hàm Lấy câu hỏi ngẫu nhiên
    public ArrayList<CauHoi> getCauHoiNgauNhien(String MaMonHoc){
        ArrayList<CauHoi> listCauHoi= new ArrayList<CauHoi>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT CauHoi.* " +
                "FROM CauHoi " +
                "JOIN BaiHoc ON CauHoi.MaBaiHoc = BaiHoc.MaBaiHoc " +
                "JOIN MonHoc ON BaiHoc.MaMonHoc = MonHoc.MaMonHoc " +
                "Where MonHoc.MaMonHoc='"+MaMonHoc+"'" +
                "ORDER BY RANDOM() "+ "LIMIT 10",
                null);
        cursor.moveToFirst();
        do {
            CauHoi item;
            item = new CauHoi(cursor.getString(0),cursor.getString(1),
                    cursor.getString(2),cursor.getString(3),
                    cursor.getString(4), cursor.getString(5),cursor.getString(6));
            listCauHoi.add(item);
        }while(cursor.moveToNext());
        return listCauHoi;
    }
}
