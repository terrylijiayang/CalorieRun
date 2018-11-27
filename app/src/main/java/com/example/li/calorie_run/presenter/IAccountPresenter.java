package com.example.li.calorie_run.presenter;

public interface IAccountPresenter {
    void login(String user_id, String password);

    void register(String user_id, String password);

    void onDestroy();
}
