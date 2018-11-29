package com.example.li.calorie_run.view.mainnavigations;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.li.calorie_run.R;
import com.example.li.calorie_run.presenter.IPersonPagePresenter;
import com.example.li.calorie_run.presenter.Impl.PersonPagePresenterImpl;
import com.example.li.calorie_run.tools.ConstantValue;
import com.example.li.calorie_run.tools.MapTool;
import com.example.li.calorie_run.view.AccountView;
import com.example.li.calorie_run.view.LoginActivity;
import com.loopj.android.image.SmartImageView;

import java.util.Map;

public class MeFragment extends Fragment implements AccountView {

    private OnFragmentInteractionListener mListener;

    // TODO:用户创建的属性
    private SmartImageView header;
    private TextView name, integral;
    private View view;
    private IPersonPagePresenter presenter;
    private Handler handler;
    private String value;
    private Button btn_logout;
    private TextView btn_person_msg;
    private SmartImageView user_header;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //更新ui
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        header = (SmartImageView) view.findViewById(R.id.header);
                        name = (TextView) view.findViewById(R.id.name);
                        integral = (TextView) view.findViewById(R.id.integral);
                        showPersonMsg(value);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter = new PersonPagePresenterImpl(this);
        view = inflater.inflate(R.layout.fragment_me, container, false);
        presenter.getUserMsgById(ConstantValue.USER_ID);
        return view;
    }


    //fragment的点击事件写在这里
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn_logout = (Button) getActivity().findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"注销成功", Toast.LENGTH_SHORT).show();
//                SharedPreferences.Editor editor = LoginActivity.sp.edit();
//                editor.putString("USER_NAME", "");
//                editor.putString("PASSWORD","");
//                editor.putBoolean("ISCHECK",false);
//                editor.commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        /*
        btn_person_msg = (TextView) getActivity().findViewById(R.id.btn_person_msg);
        btn_person_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Person_msg_Fragment demoFragment = new Person_msg_Fragment();
                ft
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment_person_page,demoFragment)
                        .show(demoFragment)
                        .commit();
            }
        });

        user_header = (SmartImageView)getActivity().findViewById(R.id.header);
        user_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                UserMsgFragment demoFragment = new UserMsgFragment();
                ft
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment_person_page,demoFragment)
                        .show(demoFragment)
                        .commit();
            }
        });

        */

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //显示用户信息
    private void showPersonMsg( String data){
        Map map = (Map) JSON.parse(data);

        String img_path = MapTool.getValue(map,"user_img_path").toString();
        String txt_name = MapTool.getValue(map,"user_name").toString();
        String txt_integral = MapTool.getValue(map,"user_integral").toString();

        integral.setText(txt_integral + "分");
        name.setText(txt_name);
        header.setImageUrl(ConstantValue.SERVER_PATH + img_path,//加载指定地址的图片http://s1.dwstatic.com/lushicard/Lyra_the_Sunshard.png
                R.drawable.ic_launcher_foreground,//指定图片没找到时显示的图片
                R.drawable.ic_launcher_foreground);//正在加载中显示的图片.
    }

    @Override
    public void navigateToHome(String value) {
        this.value = value;handler.sendEmptyMessage(0);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }


}
