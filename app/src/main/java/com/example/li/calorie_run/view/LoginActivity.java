package com.example.li.calorie_run.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.li.calorie_run.R;
import com.example.li.calorie_run.presenter.IAccountPresenter;
import com.example.li.calorie_run.presenter.Impl.AccountPresenterImpl;
import com.example.li.calorie_run.tools.ConstantValue;


import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity  implements View.OnKeyListener,AccountView {

    private String value;
    private IAccountPresenter presenter;
    private TextView user_id,user_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        presenter = new AccountPresenterImpl(this);
        user_id = this.findViewById(R.id.txt_id);
        user_pwd = this.findViewById(R.id.txt_password);

        //登录
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = user_id.getText().toString().trim();
                String pwd = user_pwd.getText().toString().trim();
                presenter.login(id,pwd);
            }
        });



        //注册
        findViewById(R.id.btn_toregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterConfirmActivity.class);
                startActivity(intent);
            }
        });

    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exitByDoubleClick();
        }
        return false;
    }

    final boolean[] isExit = {false};
    private void exitByDoubleClick() {
        Timer tExit = null;
        if(!isExit[0]){
            isExit[0] =true;
            Toast.makeText(LoginActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            tExit=new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit[0] =false;//取消退出
                }
            },1000);// 如果1秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        }else{
            finish();
            System.exit(0);
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }

    public void loginSuccess(){
        ConstantValue.USER_ID = user_id.getText().toString().trim();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToHome(String value) {
        this.value = value;
        loginSuccess();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
