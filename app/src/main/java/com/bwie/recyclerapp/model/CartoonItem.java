package com.bwie.recyclerapp.model;

import java.util.List;

/**
 * Created by liqy on 2017/8/10.
 */

public class CartoonItem {

    public int type;

    // 轮播图
    public List<ComicItem> galleryItems;

    public ComicItem comicItem;


    public String titleIconUrl;
    public String newTitleIconUrl;
    public String description;
    public String itemTitle;


    public CartoonItem(int type) {
        this.type = type;
    }

    public CartoonItem(U17Item item) {
        type = 99;//TODO
        titleIconUrl = item.titleIconUrl;
        newTitleIconUrl = item.newTitleIconUrl;
        description = item.description;
        itemTitle = item.itemTitle;
    }


    public CartoonItem(int type, ComicItem comicItem) {
        this.type = type;
        this.comicItem = comicItem;
    }

    public CartoonItem(int type, List<ComicItem> galleryItems) {
        this.galleryItems = galleryItems;
    }
}
