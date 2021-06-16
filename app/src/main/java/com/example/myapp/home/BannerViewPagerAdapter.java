package com.example.myapp.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapp.R;

import java.util.List;

public class BannerViewPagerAdapter extends PagerAdapter {

    List<BannerModel> bannerModelList;

    public BannerViewPagerAdapter(List<BannerModel> bannerModelList) {
        this.bannerModelList=bannerModelList;
    }

    @Override
    public int getCount() {
        return bannerModelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.banner_view_pager_item,container,false);
        ImageView imageView=view.findViewById(R.id.bannerItemIimage);
        imageView.setImageResource(bannerModelList.get(position).getImage());

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager viewPager = (ViewPager) container;
        viewPager.removeView((View)object);
    }
}
