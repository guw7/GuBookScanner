package com.guw.gubook.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.guw.gubook.R;
import com.guw.gubook._sliders.FragmentSlider;
import com.guw.gubook._sliders.SliderIndicator;
import com.guw.gubook._sliders.SliderPagerAdapter;
import com.guw.gubook._sliders.SliderView;

import java.util.ArrayList;
import java.util.List;

public class FragAbout extends Fragment {

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        sliderView = (SliderView) view.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.pagesContainer);
        setupSlider();

        return view;
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://raw.githubusercontent.com/guw7/CV/master/img/slide-1.png"));
        fragments.add(FragmentSlider.newInstance("https://raw.githubusercontent.com/guw7/CV/master/img/slide-2.png"));
        fragments.add(FragmentSlider.newInstance("https://raw.githubusercontent.com/guw7/CV/master/img/slide-3.png"));
        fragments.add(FragmentSlider.newInstance("https://raw.githubusercontent.com/guw7/CV/master/img/slide-4.png"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }
}
