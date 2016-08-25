package org.greenfroyo.androidmvp_bind.domain;

import org.greenfroyo.androidmvp_bind.R;
import org.greenfroyo.androidmvp_bind.app.MVPBApp;

/**
 * Created by fchristysen on 6/29/16.
 */

public class UserLogin {

    public static final int VALID_USERNAME = 100;
    public static final int ERROR_USERNAME_EMPTY = 101;
    public static final int ERROR_USERNAME_TOO_SHORT = 102;
    public static final int ERROR_USERNAME_TOO_LONG = 103;

    public static final int MIN_LENGTH_USERNAME = 4;
    public static final int MAX_LENGTH_USERNAME = 12;

    public static int checkUsername(String username){
        if(username.length() == 0 ){
            return ERROR_USERNAME_EMPTY;
        }else if(username.length() < MIN_LENGTH_USERNAME){
            return ERROR_USERNAME_TOO_SHORT;
        }else if(username.length() > MAX_LENGTH_USERNAME){
            return ERROR_USERNAME_TOO_LONG;
        }else{
            return VALID_USERNAME;
        }
    }

    /**
     * @param validity error state
     * @return null if the error state is unknown
     */
    public static String getUsernameErrorString(int validity){
        switch (validity){
            case UserLogin.ERROR_USERNAME_EMPTY:
                return MVPBApp.resources().getString(R.string.login_field_empty);
            case UserLogin.ERROR_USERNAME_TOO_SHORT:
                return MVPBApp.resources().getString(R.string.login_password_too_short, UserLogin.MIN_LENGTH_USERNAME);
            case UserLogin.ERROR_USERNAME_TOO_LONG:
                return MVPBApp.resources().getString(R.string.login_password_too_long, UserLogin.MAX_LENGTH_USERNAME);
        }
        return null;
    }

    public static final int VALID_PASSWORD = 100;
    public static final int ERROR_PASSWORD_EMPTY = 101;
    public static final int ERROR_PASSWORD_TOO_SHORT = 102;
    public static final int ERROR_PASSWORD_TOO_LONG = 103;

    public static final int MIN_LENGTH_PASSWORD = 4;
    public static final int MAX_LENGTH_PASSWORD = 12;

    public static int checkPassword(String password){
        if(password.length() == 0 ){
            return ERROR_PASSWORD_EMPTY;
        }else if(password.length() < MIN_LENGTH_PASSWORD){
            return ERROR_PASSWORD_TOO_SHORT;
        }else if(password.length() > MAX_LENGTH_PASSWORD){
            return ERROR_PASSWORD_TOO_LONG;
        }else{
            return VALID_PASSWORD;
        }
    }

    /**
     * @param validity error state
     * @return null if the error state is unknown
     */
    public static String getPasswordErrorString(int validity){
        switch (validity){
            case UserLogin.ERROR_PASSWORD_EMPTY:
                return MVPBApp.resources().getString(R.string.login_field_empty);
            case UserLogin.ERROR_PASSWORD_TOO_SHORT:
                return MVPBApp.resources().getString(R.string.login_password_too_short, UserLogin.MIN_LENGTH_PASSWORD);
            case UserLogin.ERROR_PASSWORD_TOO_LONG:
                return MVPBApp.resources().getString(R.string.login_password_too_long, UserLogin.MAX_LENGTH_PASSWORD);
        }
        return null;
    }

}
