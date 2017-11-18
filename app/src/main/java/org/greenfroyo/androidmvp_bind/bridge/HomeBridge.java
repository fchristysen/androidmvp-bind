package org.greenfroyo.androidmvp_bind.bridge;

import org.greenfroyo.androidmvp_bind.app.home.HomeItemViewModel;
import org.greenfroyo.androidmvp_bind.provider.home.MainMenuDataModel;

/**
 * Created by fchristysen on 7/1/16.
 */

public class HomeBridge {

    public static HomeItemViewModel getHomeItem(MainMenuDataModel model){
        String displayName = model.getPages().getSimpleName();
        displayName = HomeBridge.normalizeCase(displayName);
        HomeItemViewModel r = new HomeItemViewModel(model.getPages(), displayName);
        return r;
    }
    
    private static String normalizeCase(String text){
        if(text == null || text.length()==0)
            return text;
        String r=text.substring(0,1);
        for(int i=1;i<text.length();i++) {
            char c = text.charAt(i);
            if(c >= 'A' && c <= 'Z')
                r+= " ";
            r += c;
        }
        return r;
    }
}
