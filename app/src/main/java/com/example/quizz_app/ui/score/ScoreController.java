package com.example.quizz_app.ui.score;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizz_app.ui.CauHoi.CauHoi;
import com.example.quizz_app.ui.CauHoi.DBHelper;

import java.util.ArrayList;

public class ScoreController {
    private DBHelper dbHelper;

    public ScoreController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertScore(String name, int score, String room){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("score",score);
        values.put("room",room);
        db.insert("tbscore",null,values);
        db.close();
    }

    //Trả về cursor. Lấy ra ds điểm
    public Cursor getScore(){
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query("tbscore", //tenbang
                null, //Danh sach cot can lay
                null, //Dieu kien where
                null, //Doi so dieu kiện where
                null, //Bieu thuc GroupBy
                null, //Bieu thuc Having
                "_id DESC", //Bieu thuc order by
                null
                );
        if (cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

}
