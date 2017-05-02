package com.jsonpen.jhjmoudle;

import android.content.Context;

/**
 * Created by user on 2017/4/26.
 */

public class AppInstence {
    public static App appContext;
    public static void init(App app){
        if (null==appContext){
            appContext =app;
        }
    }
}
