package org.sadalsuud.androidmvp_bind.util;

import android.util.Log;

/**
 * Created by fchristysen on 1/25/16.
 */
public class AppUtil {

    public static final boolean isVerbose = true;
    public static void log(String message){
        if(isVerbose){
            Log.i("MVPBind", message);
        }
    }

}
