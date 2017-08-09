package com.bwie.recyclerapp.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by liqy on 2017/8/9.
 */

public class MyLine extends RecyclerView.ItemDecoration {
    public MyLine() {
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(8,8,8,8);
    }
}
