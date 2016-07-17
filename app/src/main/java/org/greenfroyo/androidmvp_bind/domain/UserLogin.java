package org.greenfroyo.androidmvp_bind.domain;

import static android.provider.Telephony.Carriers.PASSWORD;

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

}
