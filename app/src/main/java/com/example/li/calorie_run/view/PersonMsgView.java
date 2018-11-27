package com.example.li.calorie_run.view;

public interface PersonMsgView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome(String value);

    void showMsg(String msg);
}
