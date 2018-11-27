package com.example.li.calorie_run.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.li.calorie_run.R;
import com.example.li.calorie_run.tools.BottomNavigationViewHelper;
import com.example.li.calorie_run.view.mainnavigations.IndexFragment;
import com.example.li.calorie_run.view.mainnavigations.SaladFragment;
import com.example.li.calorie_run.view.mainnavigations.ShopFragment;
import com.example.li.calorie_run.view.mainnavigations.SportsFragment;


public class MainActivity extends AppCompatActivity implements
        IndexFragment.OnFragmentInteractionListener,
        SaladFragment.OnFragmentInteractionListener,
        ShopFragment.OnFragmentInteractionListener,
        SportsFragment.OnFragmentInteractionListener {

    private BottomNavigationView bottomNavigationView;
    private IndexFragment indexFragment;
    private SaladFragment saladFragment;
    private ShopFragment shopFragment;
    private SportsFragment sportsFragment;
    private Fragment[] fragments;
    private int lastfragment;//用于记录上个选择的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
    }

    //初始化fragment和fragment数组
    private void initFragment()
    {
        //import android.app.Fragment;   Fragment的类型
        indexFragment  = new IndexFragment();
        saladFragment  = new SaladFragment();
        shopFragment = new ShopFragment();
        sportsFragment = new SportsFragment();
        fragments = new Fragment[]{indexFragment,saladFragment,shopFragment,sportsFragment};

        lastfragment=0;

        //初始化 显示Fragment
        FragmentManager homePagemanager = getSupportFragmentManager();
        FragmentTransaction homePagetransaction = homePagemanager.beginTransaction();
        //HomePageFragment homePageFragment = new HomePageFragment();
        homePagetransaction.replace(R.id.main_view,indexFragment,"indexFragment");
        homePagetransaction.commit();

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }

    //判断选择的菜单
    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId())
            {
                case R.id.navigation_index:
                {
                    if(lastfragment!=0)
                    {
                        switchFragment(lastfragment,0);
                        lastfragment=0;
                    }
                    return true;
                }
                case R.id.navigation_salad:
                {
                    if(lastfragment!=1)
                    {
                        switchFragment(lastfragment,1);
                        lastfragment=1;
                    }

                    return true;
                }
                case R.id.navigation_shop:
                {
                    if(lastfragment!=2)
                    {
                        switchFragment(lastfragment,2);
                        lastfragment=2;
                    }
                    return true;
                }
                case R.id.navigation_sports:
                {
                    if(lastfragment!=3)
                    {
                        switchFragment(lastfragment,3);
                        lastfragment=3;
                    }
                    return true;
                }
            }
            return false;
        }
    };

    //切换Fragment
    private void switchFragment(int lastfragment,int index)
    {
        FragmentManager Pagemanager = getSupportFragmentManager( );
        FragmentTransaction transaction = Pagemanager.beginTransaction();

        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment

        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.main_view,fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
