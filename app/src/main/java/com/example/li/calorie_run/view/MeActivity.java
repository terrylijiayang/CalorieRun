package com.example.li.calorie_run.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.loopj.android.image.SmartImageView;

import java.util.Map;

public class MeActivity extends AppCompatActivity implements AccountView {


    // TODO:用户创建的属性
    private SmartImageView header;
    private TextView name, integral;
    private IPersonPagePresenter presenter;
    private Handler handler;
    private String data;
    private Button btn_logout;
    private TextView btn_person_msg;
    private SmartImageView user_header;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MeActivity.this,"注销成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //更新ui
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        header = (SmartImageView) findViewById(R.id.header);
                        name = (TextView) findViewById(R.id.name);
                        integral = (TextView) findViewById(R.id.integral);
                        showPersonMsg(data);
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
        presenter = new PersonPagePresenterImpl(this);
        presenter.getUserMsgById(ConstantValue.USER_ID);
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
        this.data = value;handler.sendEmptyMessage(0);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(MeActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

}
