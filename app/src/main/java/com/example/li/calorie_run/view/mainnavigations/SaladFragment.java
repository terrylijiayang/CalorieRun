package com.example.li.calorie_run.view.mainnavigations;

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

import com.example.li.calorie_run.R;
import com.example.li.calorie_run.view.mainnavigations.saladtabs.SaladHotFragment;
import com.example.li.calorie_run.view.mainnavigations.saladtabs.SaladHomeFragment;
import com.example.li.calorie_run.view.mainnavigations.saladtabs.SaladMainFragment;


public class SaladFragment extends Fragment {

    private TabLayout tabLayout = null;
    private ViewPager vp_pager;
    private View view;
    private Fragment[] mFragmentArrays = new Fragment[6];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_salad, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.salad_tablayout);
        vp_pager = (ViewPager) view.findViewById(R.id.tab_saladviewpager);
        initView();
        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void initView() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        vp_pager.setAdapter(new MorePagerAdapter());
//        tabLayout.setupWithViewPager(vp_pager);
        mFragmentArrays[0] = SaladHomeFragment.newInstance();
        mFragmentArrays[1] = SaladHotFragment.newInstance();
        mFragmentArrays[2] = SaladMainFragment.newInstance();
        mFragmentArrays[3] = SaladHomeFragment.newInstance();
        mFragmentArrays[4] = SaladHotFragment.newInstance();
        mFragmentArrays[5] = SaladMainFragment.newInstance();

        PagerAdapter pagerAdapter = new SaladFragment.MorePagerAdapter(getFragmentManager());
        vp_pager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tabLayout.setupWithViewPager(vp_pager);
        tabLayout.getTabAt(0).select();
    }

    final class MorePagerAdapter extends FragmentPagerAdapter {

        String[] arr={"首页","沙拉","最热","活动","会员","评价"};

        public MorePagerAdapter(FragmentManager fm) {
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
            return arr[position];

        }
    }
}
