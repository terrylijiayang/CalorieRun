package com.example.li.calorie_run.view.mainnavigations.shoptabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.li.calorie_run.R;
import com.example.li.calorie_run.view.adapter.ShopDataAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopSellerFragment extends Fragment {
    private View view;
    private ShopDataAdapter adapter;
    private ListView listView;

    public static ShopSellerFragment newInstance(){
        ShopSellerFragment shopSellerFragment = new ShopSellerFragment();
        return shopSellerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        view = inflater.inflate(R.layout.fragment_shop_seller, container, false);
        initView();
        return view;
    }

    private void initView(){
        ArrayList<Map> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("seller_title","测试");
        map.put("seller_img_path","");
        list.add(map);

//        listView = view.findViewById(R.id.lv_seller);
//        adapter = new ShopDataAdapter(getActivity(),(ArrayList)list);
//        listView.setAdapter(adapter);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
