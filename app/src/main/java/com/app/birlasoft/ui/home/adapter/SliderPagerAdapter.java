package com.app.birlasoft.ui.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.birlasoft.R;
import com.app.birlasoft.data.model.BannerData;
import com.app.birlasoft.utils.GlideConnector;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class SliderPagerAdapter extends PagerAdapter {
    private final ArrayList<BannerData> bannerList;
    private final Context context;

    public SliderPagerAdapter(Context context, ArrayList<BannerData> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return view == object;
    }

    @Override
    public @NotNull
    Object instantiateItem(@NotNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_child_item, null);
        ImageView imageView = view.findViewById(R.id.imageView);

        GlideConnector.getInstance().loadImageDirectly(context, bannerList.get(position).getImage(), imageView);
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        Log.v("position", String.valueOf(position));
        return view;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
