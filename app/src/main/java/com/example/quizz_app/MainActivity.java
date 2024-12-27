package com.example.quizz_app;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.quizz_app.ui.CauHoi.DBHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizz_app.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.C, R.id.Java,R.id.php,R.id.python,R.id.score)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        DBHelper dbHelper = new DBHelper(this);
        // Phương thức xoa database viết vào hàm MainActivity
        //dbHelper.deleteDataBase();
        //Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();

        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                navController.navigate(R.id.nav_home);// Điều hướng màn hình đến fragment Home
                Snackbar.make(drawer, "Trang Chủ", Snackbar.LENGTH_SHORT).show();
            }
            else if (id == R.id.C) {
                navController.navigate(R.id.C);// Điều hướng màn hình đến fragment C
                Snackbar.make(drawer, "Đã chọn môn C", Snackbar.LENGTH_SHORT).show();
            } else if (id == R.id.Java) {
                navController.navigate(R.id.Java);// Điều hướng màn hình đến fragment Java
                //Thông báo màn hình được chọn
                Snackbar.make(drawer, "Đã chọn môn Java", Snackbar.LENGTH_SHORT).show();
            } else if (id == R.id.php) {
                navController.navigate(R.id.php);// Điều hướng màn hình đến fragment php
                Snackbar.make(drawer, "Đã chọn môn PHP", Snackbar.LENGTH_SHORT).show();
            } else if (id == R.id.python) {
                navController.navigate(R.id.python);// Điều hướng màn hình đến fragment python
                Snackbar.make(drawer, "Đã chọn môn Python", Snackbar.LENGTH_SHORT).show();
            }else if (id == R.id.score) {
                navController.navigate(R.id.score);// Điều hướng màn hình đến fragment score
                Snackbar.make(drawer, "Xem điểm", Snackbar.LENGTH_SHORT).show();
            } else {
                NavigationUI.onNavDestinationSelected(item, navController);
            }
            drawer.closeDrawers();
            return true;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}