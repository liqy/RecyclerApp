package com.bwie.recyclerapp.adapter;

/**
 * Created by liqy on 2017/8/9.
 */

public class MyItem {
    /**
     * 0：大图
     * 1：按钮模块
     * 2：子标题
     * 3：小图
     */
    public  int type;
    public  String string;



    public MyItem( String string,int type) {
        this.type = type;
        this.string = string;
    }

    public MyItem(String string) {
        this.string = string;
    }
}
