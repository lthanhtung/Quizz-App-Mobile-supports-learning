package com.example.quizz_app.ui.BaiTap;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quizz_app.R;
import com.example.quizz_app.databinding.ActivityBaiHocBinding;
import com.example.quizz_app.ui.Slide.ScreenSlideActivity;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class BaiHocActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityBaiHocBinding binding;

    String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;
    String DATABASE_NAME = "Quizz_database.db";

    // Khai báo ListView
    ListView ListView_BaiHoc;
    ArrayList<String> List_BaiHoc;
    ArrayList<String> List_MaBaiHoc;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBaiHocBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarBaiHoc.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder()
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_bai_hoc);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        ListView_BaiHoc = findViewById(R.id.ListView_BaiHoc);
        List_BaiHoc = new ArrayList<>();
        List_MaBaiHoc = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, List_BaiHoc);
        ListView_BaiHoc.setAdapter(adapter);

        processCopy();
        database = openOrCreateDatabase("Quizz_database.db", MODE_PRIVATE, null);

        // Nhận thông tin môn học từ Intent
        String subject = getIntent().getStringExtra("MaMonHoc");
        if (subject != null) {
            loadLessonsForSubject(subject);
        }

        ListView_BaiHoc.setOnItemClickListener((parent, view, position, id) -> {
            String baiHocDetail = List_BaiHoc.get(position);
            String maBaiHoc = List_MaBaiHoc.get(position);//Lấy mã bài học tương ứng với mỗi dòng
            //String message = "Bạn đã chọn vị trí: " + (position + 1) + ", nội dung: " + baiHocDetail;
            String message = "Bạn đã chọn: " + baiHocDetail;
            //String idBaihoc = "Bạn đã chọn: " + maBaiHoc;
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BaiHocActivity.this, ScreenSlideActivity.class);
            intent.putExtra("MaBaiHoc",maBaiHoc);
            startActivity(intent);
        });
    }

    private void loadLessonsForSubject(String subject) {
        Cursor c = database.query("BaiHoc", null, "MaMonHoc = ?", new String[]{subject},
                null, null, null, null);

        List_BaiHoc.clear();
        List_MaBaiHoc.clear();//Xóa dữ liệu cũ

        String data = "";
        int dem = 1;

        if (c.moveToFirst()) {
            do {
                data = "Bài " + dem + ": " + c.getString(2); // Cột 2 là tên bài học
                dem++;
                List_BaiHoc.add(data);
                List_MaBaiHoc.add(c.getString(0));//Trường chứ mã bài học
            } while (c.moveToNext());
        }
        c.close();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bai_hoc, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_bai_hoc);
        if (!navController.popBackStack()) {
            finish();
        }
        return true;
    }

    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying success from Assets folder", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public void CopyDataBaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            int size = myInput.available();
            byte[] buffer = new byte[size];
            myInput.read(buffer);
            myOutput.write(buffer);
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
