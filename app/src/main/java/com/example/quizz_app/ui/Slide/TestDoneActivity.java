package com.example.quizz_app.ui.Slide;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quizz_app.R;
import com.example.quizz_app.ui.CauHoi.CauHoi;
import com.example.quizz_app.ui.score.ScoreController;

import java.util.ArrayList;

public class TestDoneActivity extends AppCompatActivity {

    ArrayList<CauHoi> listCauHoiBanDau = new ArrayList<CauHoi>();
    int NumNoAns = 0;
    int NumTrue = 0;
    int NumFalse= 0;
    int TotalScore = 0;

    TextView tvTrue, tvFalse, tvNotAns, tvTotalScore;
    Button btnSaveScore, btnAgain, btnExit;

    ScoreController scoreController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_done);
        scoreController = new ScoreController(TestDoneActivity.this);

        Intent intent = getIntent();
        listCauHoiBanDau = (ArrayList<CauHoi>) intent.getExtras().getSerializable("list CauHoi");
        begin();
        checkResult();
        TotalScore = NumTrue * 10;
        tvNotAns.setText(""+NumNoAns);
        tvFalse.setText(""+NumFalse);
        tvTrue.setText(""+NumTrue);
        tvTotalScore.setText(""+TotalScore);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát hay không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        btnSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
                LayoutInflater inflater = TestDoneActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.alert_dialog_save_score,null);
                builder.setView(view);

                EditText edtName = (EditText) view.findViewById(R.id.edtName);
                EditText edtRoom = (EditText) view.findViewById(R.id.edtRoom);
                TextView tvScore = (TextView) view.findViewById(R.id.tvScore);
                int numTotal = NumTrue * 10;
                tvScore.setText(numTotal+" điểm");


                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edtName.getText().toString();
                        String room = edtRoom.getText().toString();
                        scoreController.insertScore(name,numTotal,room);
                        Toast.makeText(TestDoneActivity.this, "Lưu điểm thành công",Toast.LENGTH_LONG).show();
                        finish();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog b = builder.create();
                b.show();
            }
        });
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