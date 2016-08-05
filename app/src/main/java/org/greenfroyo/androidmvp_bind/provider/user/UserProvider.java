package org.greenfroyo.androidmvp_bind.provider.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.greenfroyo.androidmvp_bind.provider.common.CommonTransformer;
import org.greenfroyo.androidmvp_bind.provider.common.BaseProvider;
import org.greenfroyo.androidmvp_bind.provider.common.LocalBroadcastBus;

import java.util.Random;

import rx.Observable;

/**
 * Created by fchristysen on 6/30/16.
 */

public class UserProvider extends BaseProvider {

    private static final String PREF_LOGIN = "pref.login";
    private static final String PREF_LOGIN_IS_LOGIN = "islogin";
    private static final String PREF_LOGIN_USERNAME = "username";
    private static final String PREF_LOGIN_FULLNAME = "fullname";
    private static final String PREF_LOGIN_AVATAR = "avatar";

    public static final int LOGIN_SUCCESS = 0;
    public static final int LOGIN_ERROR_NO_ACCOUNT = 1;

    private Boolean isLogin;
    private UserLoginDataModel.UserDataModel mUser;

    public UserProvider() {
    }

    //region accessor
    public Observable<Boolean> isLoginState(){
        return Observable.just(isLogin()).concatWith(Observable.create(subscriber -> {
            LocalBroadcastBus.get().register(PREF_LOGIN, new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    subscriber.onNext(isLogin());
                }
            });
        }));
    }

    public Boolean isLogin() {
        if(isLogin == null){
            isLogin = getPreferenceDriver().getBoolean(PREF_LOGIN, PREF_LOGIN_IS_LOGIN, false);
        }
        return isLogin;
    }

    public UserLoginDataModel.UserDataModel getUser(){
        if(mUser==null && isLogin()){
            mUser = new UserLoginDataModel.UserDataModel();
            mUser.fullname = getPreferenceDriver().getString(PREF_LOGIN, PREF_LOGIN_FULLNAME, "");
            mUser.username = getPreferenceDriver().getString(PREF_LOGIN, PREF_LOGIN_USERNAME, "");
            mUser.avatarUrl = getPreferenceDriver().getString(PREF_LOGIN, PREF_LOGIN_AVATAR, "");
        }
        return mUser;
    }
    //endregion

    //region mutator
    public Observable<UserLoginDataModel> login(String username, String password){
        UserLoginDataModel result = new UserLoginDataModel();
        if(username.equals(password)) {
            result.loginResult = new Integer(LOGIN_SUCCESS);
            result.user = UserMocker.mockUser(username);
        }else{
            result.loginResult = new Integer(LOGIN_ERROR_NO_ACCOUNT);
        }
        return Observable.just(result).map(next -> {
            try{
                Thread.sleep(new Random().nextInt(2000) + 1000);
            }catch (InterruptedException e){}
            if(next.loginResult == LOGIN_SUCCESS){
                setLoginData(result.user);
                setLogin(true);
            }
            return next;
        }).compose(CommonTransformer.doOnIOThread());
    }

    public void logout(){
        clearLoginData();
        setLogin(false);
    }

    private void setLogin(Boolean login) {
        isLogin = login;
        getPreferenceDriver().put(PREF_LOGIN, PREF_LOGIN_IS_LOGIN, login);
        Bundle bundle = new Bundle();
        bundle.putBoolean(PREF_LOGIN_IS_LOGIN, login);
        LocalBroadcastBus.get().send(PREF_LOGIN, bundle);
    }

    private void setLoginData(UserLoginDataModel.UserDataModel user){
        this.mUser = user;
        getPreferenceDriver().put(PREF_LOGIN, PREF_LOGIN_USERNAME, user.username);
        getPreferenceDriver().put(PREF_LOGIN, PREF_LOGIN_FULLNAME, user.fullname);
        getPreferenceDriver().put(PREF_LOGIN, PREF_LOGIN_AVATAR, user.avatarUrl);
    }

    private void clearLoginData(){
        mUser = null;
        getPreferenceDriver().delete(PREF_LOGIN, PREF_LOGIN_USERNAME);
        getPreferenceDriver().delete(PREF_LOGIN, PREF_LOGIN_FULLNAME);
        getPreferenceDriver().delete(PREF_LOGIN, PREF_LOGIN_AVATAR);
    }
    //endregion




}
