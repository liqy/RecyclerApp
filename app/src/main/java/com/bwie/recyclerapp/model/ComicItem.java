package com.bwie.recyclerapp.model;

import java.util.List;

/**
 * Created by liqy on 2017/8/10.
 */

public class ComicItem {

    public int type;//展示类型

    public String cover;
    public String name;


    //轮播图
    public int linkType;//跳转页面的类型
    public int id;
    public String title;
    public String content;

    //四个模块点击按钮
    public String argName;
    public String argValue;
    public String itemTitle;

    public long comicId;
    public List<String> tags;
    public String description;
    public String cornerInfo;
    public String short_description;
    public String author_name;
    public int  is_vip;





}
