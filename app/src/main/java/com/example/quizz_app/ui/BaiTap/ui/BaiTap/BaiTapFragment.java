package com.example.quizz_app.ui.BaiTap.ui.BaiTap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.quizz_app.databinding.FragmentBaitapBinding;
import com.example.quizz_app.databinding.FragmentHomeBinding;

public class BaiTapFragment extends Fragment {

    private FragmentBaitapBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentBaitapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}