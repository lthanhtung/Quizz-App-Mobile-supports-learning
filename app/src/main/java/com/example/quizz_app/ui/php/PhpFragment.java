package com.example.quizz_app.ui.php;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.quizz_app.R;
import com.example.quizz_app.databinding.FragmentPhpBinding;
import com.example.quizz_app.ui.BaiTap.BaiHocActivity;
import com.example.quizz_app.ui.Slide.ScreenSlideActivity;

public class PhpFragment extends Fragment {

    private FragmentPhpBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentPhpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Lấy các button từ layout
//Button Bài Tập
        Button btnBaiHoc = binding.getRoot().findViewById(R.id.button_ChuDe);
        btnBaiHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageBaiTap = new Intent(getActivity(), BaiHocActivity.class);
                pageBaiTap.putExtra("MaMonHoc", "MH03"); // Truyền thông tin môn học
                startActivity(pageBaiTap);
            }
        });
        //Button RanDom
        Button btnRandom = binding.getRoot().findViewById(R.id.buttonRandom);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageCauHoi = new Intent(getActivity(), ScreenSlideActivity.class);
                pageCauHoi.putExtra("MaMonHoc", "MH03"); // Truyền thông tin môn học
                startActivity(pageCauHoi);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}