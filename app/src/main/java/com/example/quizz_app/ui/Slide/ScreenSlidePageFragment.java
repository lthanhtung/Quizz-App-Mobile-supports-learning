package com.example.quizz_app.ui.Slide;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.quizz_app.R;
import com.example.quizz_app.ui.CauHoi.CauHoi;

import java.util.ArrayList;


public class ScreenSlidePageFragment extends Fragment {

    ArrayList<CauHoi> ListcauHoi;
    public static final String ARG_PAGE = "page";//Key cho Bundle
    private int mPageNumber;  //Vị trí trang hiện tại

    //Gọi các  thành phần xml
    TextView tvNum, tvCauHoi;
    RadioGroup radioGroup;
    RadioButton rad_A,rad_B,rad_C,rad_D;


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



        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListcauHoi = new ArrayList<CauHoi>();
        ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
        ListcauHoi = slideActivity.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    public static ScreenSlidePageFragment Create(int pageNumber){
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNum.setText("Câu " + (mPageNumber+1));
        tvCauHoi.setText(ListcauHoi.get(mPageNumber).getCauHoi());
        rad_A.setText(ListcauHoi.get(mPageNumber).getDapAn_A());
        rad_B.setText(ListcauHoi.get(mPageNumber).getDapAn_B());
        rad_C.setText(ListcauHoi.get(mPageNumber).getDapAn_C());
        rad_D.setText(ListcauHoi.get(mPageNumber).getDapAn_D());
    }
}