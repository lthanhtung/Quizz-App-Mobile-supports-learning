package com.example.quizz_app.ui.score;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.quizz_app.R;

public class ScoreAdapter extends CursorAdapter {
    public ScoreAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_score,parent,false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvScoreStudent = (TextView) view.findViewById(R.id.tvScoreStudent);
        TextView tvNameStudent = (TextView) view.findViewById(R.id.tvNameStudent);
        TextView tvSubjectStudent = (TextView) view.findViewById(R.id.tvSubjectStudent);

        tvNameStudent.setText(cursor.getString(1));
        tvSubjectStudent.setText(cursor.getString(4));
        tvScoreStudent.setText(cursor.getInt(2)+"");
    }
}
