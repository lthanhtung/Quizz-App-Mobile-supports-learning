package com.example.quizz_app.ui.Slide;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quizz_app.R;
import com.example.quizz_app.ui.CauHoi.CauHoi;

import java.util.ArrayList;

public class TestDoneActivity extends AppCompatActivity {

    ArrayList<CauHoi> listCauHoiBanDau = new ArrayList<CauHoi>();
    int NumNoAns = 0;
    int NumTrue = 0;
    int NumFalse= 0;
    int TotalScore = 0;

    TextView tvTrue, tvFalse, tvNotAns, tvTotalScore;
    Button btnSaveScore, btnAgain, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_done);

        Intent intent = getIntent();
        listCauHoiBanDau = (ArrayList<CauHoi>) intent.getExtras().getSerializable("list CauHoi");
        begin();
        checkResult();
        TotalScore = NumTrue * 10;
        tvNotAns.setText(""+NumNoAns);
        tvFalse.setText(""+NumFalse);
        tvTrue.setText(""+NumTrue);
        tvTotalScore.setText(""+TotalScore);
    }

    public void begin(){
        tvFalse = (TextView) findViewById(R.id.tvFalse);
        tvTrue = (TextView) findViewById(R.id.tvTrue);
        tvNotAns = (TextView) findViewById(R.id.tvNotAns);
        tvTotalScore = (TextView) findViewById(R.id.tvTotalPoint);
        btnAgain = (Button) findViewById(R.id.btnAgain);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnSaveScore = (Button) findViewById(R.id.btnSaveScore);
    }

    //PT check kết quả
    public void checkResult(){
        for (int i=0; i<listCauHoiBanDau.size(); i++){
            if (listCauHoiBanDau.get(i).getTraLoi().equals("")==true){
                NumNoAns++;
            }else if (listCauHoiBanDau.get(i).getKetQua().equals(listCauHoiBanDau.get(i).getTraLoi())==true){
                NumTrue++;
            }else NumFalse++;
        }
    }
}