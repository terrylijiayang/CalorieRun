package com.example.li.calorie_run.presenter.Impl;


import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.li.calorie_run.presenter.IAccountPresenter;
import com.example.li.calorie_run.tools.ConstantValue;
import com.example.li.calorie_run.tools.MapTool;
import com.example.li.calorie_run.view.AccountView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


public class AccountPresenterImpl implements IAccountPresenter,AccountView {
    private AccountView accountView;

    private AccountPresenterImpl accountPresenterImpl;

    private AsyncHttpClient client = new AsyncHttpClient();

    public AccountPresenterImpl(AccountView AccountView) {
        this.accountView = AccountView;

        this.accountPresenterImpl = this;
    }

    //登录服务器并且保存信息到本地
    @Override
    public void login(final String user_id, final String password) {
        new android.os.Handler().postDelayed(new Runnable(){
        @Override
            public void run() {
                new Thread(new Runnable(){
            @Override
            public void run() {
                Looper.prepare();

                String path = "";
                try {
                    path = ConstantValue.SERVER_PATH + "/user/login?user_id="
                            + URLEncoder.encode(user_id, "utf-8")
                            + "&user_password="
                            + URLEncoder.encode(password, "utf-8") + "";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }



                client.get(path, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {

                            String msg = new String(bytes, "utf-8");

                            if(msg.charAt(0) == '"'){
                                msg = msg.substring(1, msg.length() - 1);
                            }

                            List<Map> list = JSON.parseArray(msg, Map.class);

                            String value = MapTool.getValueInList(list,"result").toString();

                            if(value.equals("1")){
                                accountPresenterImpl.navigateToHome(msg);
                            }
                            if(value.equals("-1"))
                                showMsg("登录失败");

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] responseBody, Throwable error) {
                        Toast.makeText((Context) accountView, "连接服务器失败",
                                Toast.LENGTH_LONG).show();
                    }
                });

                Looper.loop();
            }
        }).start();
        }
        }, 500);

    }

    @Override
    public void register(final String user_id, final String password) {

         new android.os.Handler().postDelayed(new Runnable(){
        @Override
            public void run() {
            new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                String path = "";
                try {
                    path = ConstantValue.SERVER_PATH + "/user/register?user_id="
                            + URLEncoder.encode(user_id, "utf-8")
                            + "&user_password="
                            + URLEncoder.encode(password, "utf-8") + "";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                client.get(path, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        try {

                            String msg = new String(bytes, "utf-8");
                            if(msg.charAt(0) == '"'){
                                msg = msg.substring(1, msg.length() - 1);
                            }
                            Map maps = (Map) JSON.parse(msg);
                            String value = MapTool.getValue(maps, "result").toString();

                            //成功
                            if(value.equals("1")){
                                showMsg("注册成功");
                                showMsg("");
//                        User user = new User(Integer.parseInt(user_id),password);
//                        IaccountModel.save(user, accountPresenterImpl);
                                //return ;
                            }else if(value.equals("0")){
                                showMsg("用户已存在");
                            }
                            else showMsg("注册失败");
                            //return ;

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] responseBody, Throwable error) {
                        Toast.makeText((Context) accountView, "连接服务器失败",
                                Toast.LENGTH_LONG).show();
                    }
                });

                Looper.loop();

            }
        }).start();
        }
        }, 1000);


    }

    @Override
    public void onDestroy() {
        accountView = null;
    }

    @Override
    public void navigateToHome(String value) {
        accountView.navigateToHome(value);
    }

    @Override
    public void showMsg(String msg) {
        accountView.showMsg(msg);
    }
}
