package com.example.li.calorie_run.presenter.Impl;


import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.example.li.calorie_run.presenter.IEarlyClickPresenter;
import com.example.li.calorie_run.tools.ConstantValue;
import com.example.li.calorie_run.tools.MapTool;
import com.example.li.calorie_run.view.AccountView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;


public class EarlyClickPresenterImpl implements IEarlyClickPresenter, AccountView {
    private AccountView accountView;
    private EarlyClickPresenterImpl earlyClickPresenter;

    private AsyncHttpClient client = new AsyncHttpClient();

    public EarlyClickPresenterImpl(AccountView accountView) {
        this.accountView = accountView;
        this.earlyClickPresenter = this;
    }

    @Override
    public void earlyClick(final String user_id) {
        new android.os.Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();

                        String path = "";
                        try {
                            path = ConstantValue.SERVER_PATH + "/earlyclock/addById?user_id="
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
                                    Map maps = (Map) JSON.parse(msg);
                                    String value = MapTool.getValue(maps, "result").toString();

                                    //成功
                                    if(value.equals("1")){
                                        navigateToHome("打卡成功");
                                        //return ;
                                    }else if(value.equals("0")){
                                        showMsg("用户已打卡");
                                    }else if(value.equals("2")){
                                        showMsg("该时间段不能打卡");
                                    }
                                    else showMsg("打卡失败");
                                    //return ;

                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers,
                                                  byte[] responseBody, Throwable error) {
//                                Toast.makeText((Context) earlyClickView, "连接服务器失败",
//                                        Toast.LENGTH_LONG).show();
                                showMsg("连接服务器失败");
                            }
                        });

                        Looper.loop();

                    }
                }).start();
            }
        }, 1000);

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
