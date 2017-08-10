package com.bwie.recyclerapp;

import android.app.Application;

import org.xutils.x;

/**
 * Created by liqy on 2017/8/10.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
