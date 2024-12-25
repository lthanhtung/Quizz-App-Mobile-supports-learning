package com.example.quizz_app.ui.Slide;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.quizz_app.R;
import com.example.quizz_app.ui.CauHoi.CauHoi;
import com.example.quizz_app.ui.CauHoi.CauHoiController;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private int SoCauhoi;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;
    public int checkAns = 0;

    TextView tvKiemTra, tvTimer, tvXemDiem;

    //Cơ sở dữ liệu
    CauHoiController cauHoiController;
    ArrayList<CauHoi> listCauHoi;
    CounterClass timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());


        timer = new CounterClass(60*1000, 1000);
        tvKiemTra = (TextView)findViewById(R.id.tvKiemTra);
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        tvXemDiem = (TextView) findViewById(R.id.tvScore) ;
        tvKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvXemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent1 = new Intent(ScreenSlideActivity.this, TestDoneActivity.class);
                intent1.putExtra("list CauHoi", listCauHoi);
                startActivity(intent1);
            }
        });

        timer.start();
        // Nhận thông tin mã bài học từ Intent BaiHocActivity
        String maBaiHoc = getIntent().getStringExtra("MaBaiHoc");
        String maMonHoc = getIntent().getStringExtra("MaMonHoc");
        cauHoiController = new CauHoiController(this);
        listCauHoi = new ArrayList<CauHoi>();
        if (maBaiHoc !=null){
            listCauHoi = cauHoiController.getCauHoi(maBaiHoc);
            SoCauhoi = listCauHoi.size();
        }
        else {
            listCauHoi = cauHoiController.getCauHoiNgauNhien(maMonHoc);
            SoCauhoi = listCauHoi.size();
        }


    }
//Tạo 1 phương thức để fragment lấy được data của từ activity
    public ArrayList<CauHoi> getData(){
        return listCauHoi;
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {

            return ScreenSlidePageFragment.Create(position, checkAns);
        }

        @Override
        public int getItemCount() {
            return SoCauhoi;
        }
        }
    public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;
        @Override
        public void transformPage(@NonNull View page, float position) {
            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.setAlpha(0f);
            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well.
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    page.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    page.setTranslationX(-horzMargin + vertMargin / 2);
                }
                // Scale the page down (between MIN_SCALE and 1).
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                page.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                page.setAlpha(0f);
            }
        }
    }

    public  void checkAnswer(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_answer_dialog);
        dialog.setTitle("Danh sách câu trả lời");

        CheckAnswerAdapter answerAdapter = new CheckAnswerAdapter(listCauHoi, this);
        GridView gvLsQuestion = (GridView) dialog.findViewById(R.id.gvLsQuestion);
        gvLsQuestion.setAdapter(answerAdapter);

        //Huỷ hộp thoại
        gvLsQuestion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewPager.setCurrentItem(position);
                dialog.dismiss();
            }
        });

        Button btnCancle, btnFinish;
        btnCancle = (Button) dialog.findViewById(R.id.btnCancle);
        btnFinish = (Button) dialog.findViewById(R.id.btnFinish);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                result();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void result(){
        checkAns = 1;
        //if (viewPager.getCurrentItem() >= 5) viewPager.setCurrentItem(viewPager.getCurrentItem()-4);
        //else if (viewPager.getCurrentItem() <5) viewPager.setCurrentItem(viewPager.getCurrentItem()+4);
        viewPager.setCurrentItem(0); //về câu 1

        tvXemDiem.setVisibility(View.VISIBLE);
        tvKiemTra.setVisibility(View.GONE);
    }

    public class CounterClass extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */

        //milisInFuture : 60 *1000
        //countDownInterval
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTimer.setText(countTime); //SetText cho textview hiện thị thời gian.
        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");  //SetText cho textview hiện thị thời gian.
        }
    }
}