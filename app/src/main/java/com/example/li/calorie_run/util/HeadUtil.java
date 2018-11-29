package com.example.li.calorie_run.util;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HeadUtil {
    /**
     *
     * @param context
     * @param icons
     * @return
     */
    public static List<ImageView> getHeaderAddInfo(Context context, int icons[]){
        List<ImageView> datas = new ArrayList<>();
        for (int i=0 ; i< icons.length; i++){
            ImageView icon = new ImageView(context);
            icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            icon.setImageResource(icons[i]);
            datas.add(icon);
        }
        return  datas;
    }
}
