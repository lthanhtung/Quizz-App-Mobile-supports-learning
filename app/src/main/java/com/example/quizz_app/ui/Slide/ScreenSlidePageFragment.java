package com.example.quizz_app.ui.Slide;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizz_app.R;
import com.example.quizz_app.ui.CauHoi.CauHoi;

import java.util.ArrayList;


public class ScreenSlidePageFragment extends Fragment {

    ArrayList<CauHoi> ListcauHoi;
    public static final String ARG_PAGE = "page";//Key cho Bundle
    public static final String ARG_CHECKANSWER = "checkAnswer";
    public int mPageNumber;  //Vị trí trang hiện tại
    public int checkAns; //Biến kiểm tra

    //Gọi các  thành phần xml
    TextView tvNum, tvCauHoi;
    RadioGroup radioGroup;
    RadioButton rad_A,rad_B,rad_C,rad_D;

    Button buttonCauSau;
    Button buttonCauTruoc;



    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

ViewGroup rootView = (ViewGroup) inflater.inflate(
        R.layout.fragment_screen_slide_page, container, false);
tvNum = (TextView)rootView.findViewById(R.id.tvNum);
tvCauHoi = (TextView) rootView.findViewById(R.id.tvQuestion);
rad_A = (RadioButton) rootView.findViewById(R.id.radA);
rad_B = (RadioButton) rootView.findViewById(R.id.radB);
rad_C = (RadioButton) rootView.findViewById(R.id.radC);
rad_D = (RadioButton) rootView.findViewById(R.id.radD);
radioGroup = (RadioGroup) rootView.findViewById(R.id.radGroup);
//Lấy vị trí hiện tại và tổng số câu hỏi từ

buttonCauTruoc = (Button) rootView.findViewById(R.id.buttonLeft);
buttonCauSau = (Button) rootView.findViewById(R.id.buttonRight);

// Ẩn button Câu Trước khi đã đến câu hỏi đầu tiên
    if (mPageNumber == 0){
        buttonCauTruoc.setVisibility(View.GONE);//Ẩn nút
    }else{
        buttonCauTruoc.setVisibility(View.VISIBLE);//Hiển thị nút
    }
// Ẩn button Câu Sau khi đã đến câu hỏi cuối cùng
    if (mPageNumber ==ListcauHoi.size() - 1){
        buttonCauSau.setVisibility(View.GONE);//Ẩn Nút
    }else {
        buttonCauSau.setVisibility(View.VISIBLE);//Hiển thị nút
    }

// Xử lý sự kiện khi ấn nút chuyển đến câu hỏi trước
    buttonCauTruoc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        //Gọi class Chuyển câu hỏi trong ScreenSlideActivity
            ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
            if (slideActivity !=null){
                slideActivity.getCauTruoc();
            }
        }
    });
// Xử lý sự kiện khi ấn nút chuyển đến câu hỏi trước
buttonCauSau.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
        if (slideActivity != null){
            slideActivity.getCauSau();
        }
    }
});

//    GridView gridViewListQuestion = rootView.findViewById(R.id.gvListQuestion);
//    CheckAnswerAdapter answerAdapter = new CheckAnswerAdapter(ListcauHoi,getContext());
//    gridViewListQuestion.setAdapter(answerAdapter);
//
//    gridViewListQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
//            if (slideActivity !=null){
//                slideActivity.setCurrentQuestion(position);
//            }
//        }
//    });

        return rootView;
    }
    

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListcauHoi = new ArrayList<CauHoi>();
        ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
        ListcauHoi = slideActivity.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE);
        checkAns = getArguments().getInt(ARG_CHECKANSWER);
    }

    public static ScreenSlidePageFragment Create(int pageNumber, int checkAnswer){
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,pageNumber);
        args.putInt(ARG_CHECKANSWER, checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNum.setText("Câu " + (mPageNumber+1));
        tvCauHoi.setText(ListcauHoi.get(mPageNumber).getCauHoi());
        rad_A.setText(getItem(mPageNumber).getDapAn_A());
        rad_B.setText(getItem(mPageNumber).getDapAn_B());
        rad_C.setText(getItem(mPageNumber).getDapAn_C());
        rad_D.setText(getItem(mPageNumber).getDapAn_D());

        if(checkAns!=0){
            rad_A.setClickable(false);
            rad_B.setClickable(false);
            rad_C.setClickable(false);
            rad_D.setClickable(false);
            getCheck(getItem(mPageNumber).getKetQua().toString());
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getItem(mPageNumber).choiceID = checkedId;
                getItem(mPageNumber).setTraLoi(getChoiceFromID(checkedId));
               // Toast.makeText(getActivity(), "Đây là đáp án"+checkedId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public CauHoi getItem(int position){
        return ListcauHoi.get(position);
    }

    //Lấy giá trị (vị trí) radiogroup chuyển thành đáp án A/B/C/D
    private String getChoiceFromID(int ID){
        if(ID == R.id.radA){
            return "A";
        } else if (ID == R.id.radB) {
            return "B";
        } else if (ID == R.id.radC) {
            return "C";
        } else if (ID == R.id.radD) {
            return "D";
        }else return "";
    }

    //Hàm kiểm tra câu đúng, nếu câu đúng thì đổi màu radio
    private void getCheck(String ans){
        if(ans.equals("A")==true){
            rad_A.setBackgroundColor(Color.RED);
        } else if (ans.equals("B")==true) {
            rad_B.setBackgroundColor(Color.RED);
        } else if (ans.equals("C")==true) {
            rad_C.setBackgroundColor(Color.RED);
        } else if (ans.equals("D")==true) {
            rad_D.setBackgroundColor(Color.RED);
        }else ;
    }
}