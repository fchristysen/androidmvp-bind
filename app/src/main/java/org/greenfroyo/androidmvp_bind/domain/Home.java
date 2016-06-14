package org.greenfroyo.androidmvp_bind.domain;

import java.util.Random;

/**
 * Created by fchristysen on 6/7/16.
 */

public class Home {

    /**
     * This method decides if the main menu is allowed to show or not
     * @return
     */
    public static boolean isAllowedToShow(){
        Random random = new Random();
        return random.nextInt(10) < 5;
    }

}
