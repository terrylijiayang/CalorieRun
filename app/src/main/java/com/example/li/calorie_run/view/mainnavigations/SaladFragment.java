package com.example.li.calorie_run.view.mainnavigations;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.li.calorie_run.R;
import com.example.li.calorie_run.view.mainnavigations.saladtabs.SaladHotFragment;
import com.example.li.calorie_run.view.mainnavigations.saladtabs.SaladIndexFragment;


public class SaladFragment extends Fragment {

    private TabLayout tabLayout = null;
    private ViewPager vp_pager;
    private View view;
    private Fragment[] mFragmentArrays = new Fragment[8];
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
        vp_pager.setAdapter(new MorePagerAdapter());
        tabLayout.setupWithViewPager(vp_pager);
        mFragmentArrays[0] = SaladIndexFragment.newInstance();
        mFragmentArrays[1] = SaladHotFragment.newInstance();
        mFragmentArrays[2] = SaladHotFragment.newInstance();
        mFragmentArrays[3] = SaladHotFragment.newInstance();
        mFragmentArrays[4] = SaladHotFragment.newInstance();
        mFragmentArrays[5] = SaladHotFragment.newInstance();
        mFragmentArrays[6] = SaladHotFragment.newInstance();
        mFragmentArrays[7] = SaladHotFragment.newInstance();
    }

    final class MorePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            TextView tv = new TextView(getActivity());
//            tv.setText("布局" + position);
//            tv.setTextSize(30.0f);
//            tv.setGravity(Gravity.CENTER);
//            (container).addView(tv);

            return mFragmentArrays[position].getView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "选项" + position;
        }
    }
}
