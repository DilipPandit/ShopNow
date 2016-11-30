package com.shopnow.app.Activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.halzhang.android.library.BottomTabIndicator;
import com.shopnow.R;
import com.shopnow.app.Adapters.MyPagerAdapter;

public class BaseActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    TextView tvActionBarTitle;
    MyPagerAdapter myPagerAdapter;
    ViewPager viewPager;
    BottomTabIndicator indicator;
    int [] tabIcons=new int[]{R.mipmap.ic_product,R.mipmap.ic_cart};
    String [] tabeTitle=new String[]{"Product","Cart"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_screen);
        init();
        
    }

    private void init() {
        tvActionBarTitle= (TextView) findViewById(R.id.tvActionBarTitle);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myPagerAdapter=new MyPagerAdapter(tabeTitle,tabIcons,getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOnPageChangeListener(this);
        indicator = (BottomTabIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager, 0);
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tvActionBarTitle.setText(tabeTitle[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
