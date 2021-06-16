package com.example.myapp.home;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    //    -----------banner------------
    private ViewPager viewPager;
    List<BannerModel> bannerModelList;
    private int currantPage = 1;
    Timer timer;
    //    -----------banner-----------

    //    -----------category recycler------------
    RecyclerView categoryRecyclerView;
    //    -----------category recycler------------

    //    -----------product recycler------------
    RecyclerView productRecyclerView;
    //    -----------product recycler------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //    -----------category recycler------------

        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(view.getContext());
        categoryLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);

        List<CategoryModel> categoryModelList=new ArrayList<>();

        categoryModelList.add(new CategoryModel(R.drawable.ic_home_black_24dp,"Home"));
        categoryModelList.add(new CategoryModel(R.drawable.fruits,"fruits"));
        categoryModelList.add(new CategoryModel(R.drawable.vegetables,"vegetables"));


        CategoryAdapter categoryAdapter=new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);


        //    -----------category recycler------------


        //    -----------banner------------
        viewPager = view.findViewById(R.id.bannerViewPager);
        bannerModelList = new ArrayList<>();
        bannerModelList.add(new BannerModel(R.drawable.s3));

        bannerModelList.add(new BannerModel(R.drawable.s1));
        bannerModelList.add(new BannerModel(R.drawable.s2));
        bannerModelList.add(new BannerModel(R.drawable.s3));
        bannerModelList.add(new BannerModel(R.drawable.s1));
        bannerModelList.add(new BannerModel(R.drawable.s2));
        bannerModelList.add(new BannerModel(R.drawable.s3));

        bannerModelList.add(new BannerModel(R.drawable.s1));

        BannerViewPagerAdapter pagerAdapter = new BannerViewPagerAdapter(bannerModelList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(20);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currantPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == viewPager.SCROLL_STATE_IDLE) {
                    pageLooper();
                }
            }
        });

        startBannerStartSlideShow();

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopSlideShow();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    startBannerStartSlideShow();
                }
                return false;
            }
        });
        //    -----------banner------------



        //    -----------product recycler------------

        TextView mainTitle=view.findViewById(R.id.mainTitle);
        mainTitle.setText("Main Title");
        productRecyclerView=view.findViewById(R.id.productRecyclerView);
        LinearLayoutManager productLLayoutManager = new LinearLayoutManager(view.getContext());
        productLLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        productRecyclerView.setLayoutManager(productLLayoutManager);

        List <ProductItemModel> productItemModelList=new ArrayList<>();
        productItemModelList.add(new ProductItemModel(R.drawable.aloo,"Aloo",250,500));
        productItemModelList.add(new ProductItemModel(R.drawable.mirchi,"Mirchie",250,500));
        productItemModelList.add(new ProductItemModel(R.drawable.aloo,"Aloo",250,500));
        productItemModelList.add(new ProductItemModel(R.drawable.mirchi,"Mirchie",250,500));
        productItemModelList.add(new ProductItemModel(R.drawable.tamater,"Tamater",250,500));

        ProductAdapter productAdapter=new ProductAdapter(productItemModelList);
        productRecyclerView.setAdapter(productAdapter);


        //    -----------product recycler------------


        return view;
    }

    //    -----------banner------------
    private void pageLooper() {
        if (currantPage == bannerModelList.size() - 1) {
            currantPage = 1;
            viewPager.setCurrentItem(currantPage, false);
        }
        if (currantPage == 0) {
            currantPage = bannerModelList.size() - 2;
            viewPager.setCurrentItem(currantPage, false);
        }
    }

    private void startBannerStartSlideShow() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currantPage >= bannerModelList.size()) {
                    currantPage = 0;
                }
                viewPager.setCurrentItem(currantPage++);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 3000, 3000);
    }

    private void stopSlideShow() {
        timer.cancel();
    }
    //    -----------banner------------
}
