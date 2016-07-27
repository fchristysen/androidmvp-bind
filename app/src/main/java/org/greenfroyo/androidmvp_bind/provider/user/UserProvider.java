package org.greenfroyo.androidmvp_bind.provider.user;

import android.content.Context;

import org.greenfroyo.androidmvp_bind.app.common.CommonTransformer;
import org.greenfroyo.androidmvp_bind.provider.common.BaseProvider;

import java.util.Random;

import rx.Observable;

/**
 * Created by fchristysen on 6/30/16.
 */

public class UserProvider extends BaseProvider {

    private static final String PREF_LOGIN = "pref.login";
    private static final String PREF_LOGIN_IS_LOGIN = "islogin";

    public static final int LOGIN_SUCCESS = 0;
    public static final int LOGIN_ERROR_NO_ACCOUNT = 1;

    public Boolean isLogin;

    public UserProvider() {
    }

    public Observable<Integer> login(String username, String password){
        Integer results;
        if(username.equals(password)) {
            results = new Integer(LOGIN_SUCCESS);
            setLogin(true);
        }else{
            results = new Integer(LOGIN_ERROR_NO_ACCOUNT);
            setLogin(false);
        }
        return Observable.just(results).map(next -> {
            try{
                Thread.sleep(new Random().nextInt(2000) + 1000);
            }catch (InterruptedException e){}
            return next;
        }).compose(CommonTransformer.toIOThread());
    }

    public Boolean getLogin() {
        if(isLogin == null){
            isLogin = getPreferenceDriver().getBoolean(PREF_LOGIN, PREF_LOGIN_IS_LOGIN, false);
        }
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
        getPreferenceDriver().put(PREF_LOGIN, PREF_LOGIN_IS_LOGIN, login);
    }
}
