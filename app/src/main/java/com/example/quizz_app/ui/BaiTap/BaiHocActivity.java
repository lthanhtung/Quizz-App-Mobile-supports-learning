package com.example.quizz_app.ui.BaiTap;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.quizz_app.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizz_app.databinding.ActivityBaiHocBinding;

public class BaiHocActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityBaiHocBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBaiHocBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarBaiHoc.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder()
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_bai_hoc);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bai_hoc, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_bai_hoc);

        // Kiểm tra xem có thể quay lại màn hình trước đó không
        if (!navController.popBackStack()) {
            //Kiểm tra nếu có màn hình đến thì quay lại màn hình trước đó A-->B
            // thì popBackStack() sẽ hoạt động B--> A
            // Nếu không có màn hình nào trong back stack, thực hiện hành vi mặc định
            finish(); // Kết thúc Activity (nếu cần)
        }
        return true;
    }

}