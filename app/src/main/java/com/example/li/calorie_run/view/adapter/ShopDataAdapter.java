package com.example.li.calorie_run.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.li.calorie_run.R;
import com.example.li.calorie_run.tools.ConstantValue;
import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Author Li
 * @create_date 2018-11-24-19:48
 */
public class ShopDataAdapter extends BaseAdapter{

    private Context c;
    private ArrayList<Map> arrayList;

    public ShopDataAdapter(Context c, ArrayList<Map> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(c, R.layout.item_shop,null);

        TextView txTitle = view.findViewById(R.id.txt_title);
        txTitle.setText(arrayList.get(i).get("seller_title").toString());

        SmartImageView smartImageView = (SmartImageView) view.findViewById(R.id.img_title);
        smartImageView.setImageUrl(ConstantValue.SERVER_PATH + arrayList.get(i).get("seller_img_path").toString(),//加载指定地址的图片http://s1.dwstatic.com/lushicard/Lyra_the_Sunshard.png
                R.mipmap.pic_index_haiwaigou_black,//指定图片没找到时显示的图片
                R.mipmap.pic_index_haiwaigou_black);//正在加载中显示的图片.
        return view;
    }
}
