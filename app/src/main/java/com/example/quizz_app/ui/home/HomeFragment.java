package com.example.quizz_app.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.quizz_app.MainActivity;
import com.example.quizz_app.R;
import com.example.quizz_app.databinding.FragmentHomeBinding;
import com.example.quizz_app.ui.BaiTap.BaiHocActivity;
import com.example.quizz_app.ui.Slide.ScreenSlideActivity;
import com.example.quizz_app.ui.c.CFragment;
import com.google.android.material.navigation.NavigationView;


public class HomeFragment extends Fragment {


    Button button_C;
    Button button_Java;
    Button button_PHP;
    Button button_Python;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.getRoot().findViewById(R.id.button_C).setOnClickListener(v -> {
            NavigationView navigationView = requireActivity().findViewById(R.id.nav_view);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.C);
            navigationView.setCheckedItem(R.id.C);
        });
        binding.getRoot().findViewById(R.id.button_Java).setOnClickListener(v -> {
            NavigationView navigationView = requireActivity().findViewById(R.id.nav_view);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.Java);
            navigationView.setCheckedItem(R.id.Java);
        });
        binding.getRoot().findViewById(R.id.button_Python).setOnClickListener(v -> {
            NavigationView navigationView = requireActivity().findViewById(R.id.nav_view);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.python);
            navigationView.setCheckedItem(R.id.python);
        });
        binding.getRoot().findViewById(R.id.button_PHP).setOnClickListener(v -> {
            NavigationView navigationView = requireActivity().findViewById(R.id.nav_view);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.php);
            navigationView.setCheckedItem(R.id.php);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}