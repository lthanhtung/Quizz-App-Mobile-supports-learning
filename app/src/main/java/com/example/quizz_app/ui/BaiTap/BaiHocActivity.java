package com.example.quizz_app.ui.BaiTap;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.quizz_app.R;
import com.example.quizz_app.ui.Slide.ScreenSlideActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizz_app.databinding.ActivityBaiHocBinding;

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
    SQLiteDatabase database=null;
    String DATABASE_NAME="Quizz_database.db";
    //Khai báo ListView
    ListView ListView_BaiHoc;
    ArrayList<String> List_BaiHoc;
    ArrayAdapter<String> adapter;



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

        ListView_BaiHoc = findViewById(R.id.ListView_BaiHoc);
        List_BaiHoc = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,List_BaiHoc);
        ListView_BaiHoc.setAdapter(adapter);
        //-----------------Tiến hành copy------------------------
        processCopy();
        //Mở csdl
        database = openOrCreateDatabase("Quizz_database.db",MODE_PRIVATE,null);
        Cursor c = database.query("BaiHoc",null,null
        ,null,null,null,null,null);
        String data = "";
        c.moveToFirst();
        int dem =1;
        while (c.isAfterLast() == false){
            data ="Bài "+dem+ ": "+c.getString(2);
            dem++;
            List_BaiHoc.add(data);
            c.moveToNext();
        }
        c.close();
        adapter.notifyDataSetChanged();
        ListView_BaiHoc.setOnItemClickListener((parent, view, position, id) -> {
            // Lấy dữ liệu bản ghi được nhấn
            String baiHocDetail = List_BaiHoc.get(position);
            // Hiển thị thông báo
            String message = "Bạn đã chọn vị trí: " + (position + 1) + ", nội dung: " + baiHocDetail;
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            // Tạo Intent để chuyển sang màn hình ChiTietBaiHocActivity
            Intent intent = new Intent(BaiHocActivity.this, ScreenSlideActivity.class);

            // Truyền dữ liệu qua Intent
            intent.putExtra("BaiHocDetail", baiHocDetail);

            // Bắt đầu Activity
            startActivity(intent);
        });

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
    // Hàm copy CSDL Sqlite từ thư mục Assets vào thư mục cài đặt ứng dụng
    private void processCopy() {
//private app
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists())
        {
            try{CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
    public void CopyDataBaseFromAsset() {
// TODO Auto-generated method stub
        try {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);
// Path to the just created empty db
            String outFileName = getDatabasePath();
// if the path doesn't exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();
// Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
// transfer bytes from the inputfile to the outputfile
// Truyền bytes dữ liệu từ input đến output
            int size = myInput.available();
            byte[] buffer = new byte[size];
            myInput.read(buffer);
            myOutput.write(buffer);
// Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}