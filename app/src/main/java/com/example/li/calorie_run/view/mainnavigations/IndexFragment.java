package com.example.li.calorie_run.view.mainnavigations;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.li.calorie_run.R;
import com.example.li.calorie_run.util.HeadUtil;
import com.example.li.calorie_run.view.LoginActivity;
import com.example.li.calorie_run.view.MainActivity;
import com.example.li.calorie_run.view.adapter.MainHeaderAdAdapter;


public class IndexFragment extends Fragment {

    private View view;
    private SwipeRefreshLayout mRefreshLayout;// SwipeRefreshLayout下拉刷新控件
    private ImageView imageView;

    protected  int [] icons={R.mipmap.pic_index_black,R.mipmap.back41,R.mipmap.component64};
    protected ViewPager mVPagerHeaderAd;//广告头

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index, container, false);

        mVPagerHeaderAd= (ViewPager) view.findViewById(R.id.vpager_main_header_ad);
        MainHeaderAdAdapter adapter=new MainHeaderAdAdapter(getActivity(), HeadUtil.getHeaderAddInfo(getActivity(),icons));
        mVPagerHeaderAd.setAdapter(adapter);


        imageView=(ImageView)view.findViewById(R.id.img_user);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MeFragment.class);
//                startActivity(intent);
            }
        });


        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_index);
        // 下拉刷新颜色控制
//        mRefreshLayout.setColorSchemeResources(R.color.swiperefresh_color1,
//                R.color.swiperefresh_color2, R.color.swiperefresh_color3,
//                R.color.swiperefresh_color4);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // 启动刷新的控件
                mRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        // 设置是否开始刷新,true为刷新，false为停止刷新
                        mRefreshLayout.setRefreshing(true);
                        Toast.makeText(getActivity(),"刷新",Toast.LENGTH_SHORT).show();
                        mRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
