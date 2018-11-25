package com.example.li.calorie_run.view.mainnavigations;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.li.calorie_run.R;
import com.example.li.calorie_run.view.mainnavigations.shoptabs.ShopRecommendFragment;
import com.example.li.calorie_run.view.mainnavigations.shoptabs.ShopSellerFragment;


public class ShopFragment extends Fragment {

    private TabLayout tabLayout = null;

    private ViewPager viewPager;

    private Fragment[] mFragmentArrays = new Fragment[2];

    private String[] mTabTitles = new String[2];

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_shop, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.shop_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.tab_shopviewpager);
        initView();
        return view;
    }

    private void initView() {
        mTabTitles[0] = "商城";
        mTabTitles[1] = "推荐";
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //设置tablayout距离上下左右的距离
        //tab_title.setPadding(20,20,20,20);
        mFragmentArrays[0] = ShopSellerFragment.newInstance();
        mFragmentArrays[1] = ShopRecommendFragment.newInstance();
        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tabLayout.setupWithViewPager(viewPager);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }
}
