package com.example.li.calorie_run.presenter.Impl;


import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.li.calorie_run.presenter.IPersonPagePresenter;
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

public class PersonPagePresenterImpl implements IPersonPagePresenter, AccountView {

    private AccountView accountView;
    private PersonPagePresenterImpl personMsgPresenterImpl;

    private AsyncHttpClient client = new AsyncHttpClient();

    public PersonPagePresenterImpl(AccountView accountView) {
        this.accountView = accountView;
        this.personMsgPresenterImpl = this;
    }

    @Override
    public void getUserMsgById(final String user_id) {
        new android.os.Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        Looper.prepare();

                        String path = "";
                        try {
                            path = ConstantValue.SERVER_PATH + "/user/getUserById?user_id="
                                    + URLEncoder.encode(user_id, "utf-8") + "";
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

                                    Map map = (Map) JSONObject.parseObject(msg);

                                    String value = MapTool.getValue(map,"result").toString();

                                    if(value.equals("1")){
                                        navigateToHome(msg);
                                    }
                                    if(value.equals("-1"))
                                        showMsg("获取失败");

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
    public void updateMsgById(String user_id) {

    }

    @Override
    public void onDestroy() {

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
