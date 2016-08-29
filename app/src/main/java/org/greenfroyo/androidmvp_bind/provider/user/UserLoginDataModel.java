package org.greenfroyo.androidmvp_bind.provider.user;

/**
 * Created by fchristysen on 8/4/16.
 */

public class UserLoginDataModel {

    public Integer loginResult;
    public UserDataModel user;

    public static class UserDataModel{
        public int id;
        public String username;
        public String avatarUrl;
        public String fullname;
    }
}
