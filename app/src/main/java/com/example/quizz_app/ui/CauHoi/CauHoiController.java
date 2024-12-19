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
    public ArrayList<CauHoi> getCauHoi(String MonHoc){
        ArrayList<CauHoi> listCauHoi= new ArrayList<CauHoi>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM CauHoi Where MaBaiHoc ='"+MonHoc+"'",null);
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
