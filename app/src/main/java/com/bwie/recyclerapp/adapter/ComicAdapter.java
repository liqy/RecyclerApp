package com.bwie.recyclerapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.recyclerapp.R;
import com.bwie.recyclerapp.model.CartoonItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liqy on 2017/8/10.
 */

public class ComicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<CartoonItem> items;

    public ComicAdapter() {
        this.items = new ArrayList<>();
    }

    public void setItems(List<CartoonItem> list) {
        this.items.addAll(list);
        notifyDataSetChanged();
    }

    public CartoonItem getItem(int position){
        return items.get(position);
    }

    /**
     * 初始化ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAdapter.ViewHolder(getView(parent, R.layout.item_my));
    }

    /**
     * 自己定义，自己实现的方法
     *
     * @param parent
     * @param res
     * @return
     */
    public View getView(ViewGroup parent, int res) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(res, parent, false);
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyAdapter.ViewHolder) {

            MyAdapter.ViewHolder mHolder = (MyAdapter.ViewHolder) holder;
            mHolder.hello.setText("条目："+position);

        }
    }

    /**
     * 获取条目展示类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        CartoonItem item = items.get(position);
        return item.type;
    }

    /**
     * 获取条目数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hello)
        TextView hello;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //0轮播图

    //11模块按钮

    //99子标题

    //图片文字加描述

    //左右布局：图左+字右

    //预约

    //结束


}
