package org.greenfroyo.androidmvp_bind.provider.user;

import java.util.Random;

/**
 * Created by fchristysen on 8/4/16.
 */

public class UserMocker {

    public static final String[] LAST_NAME = {"Drinkwater", "Southgate", "Frigate", "Wood"
            , "Clementine", "Sorkinson", "Terense", "Pallington", "Salvatore"};

    public static UserLoginDataModel.UserDataModel mockUser(String name) {
        UserLoginDataModel.UserDataModel user = new UserLoginDataModel.UserDataModel();
        user.id = new Random(name.length()).nextInt();
        user.username = name;
        user.fullname = name + " " + LAST_NAME[new Random().nextInt(LAST_NAME.length)];
        user.avatarUrl = "https://robohash.org/"+ name +".png";
        return user;
    }
}
