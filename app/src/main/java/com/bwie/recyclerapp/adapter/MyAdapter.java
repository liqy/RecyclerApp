package com.bwie.recyclerapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.recyclerapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liqy on 2017/8/9.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MyItem> list;

    public MyAdapter(List<MyItem> list) {
        this.list = list;
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
        switch (viewType) {
            case 0:
                return new BigViewHolder(getView(parent, R.layout.item_big_image));
            case 1:
                return new IconViewHolder(getView(parent, R.layout.item_icon_title));
            case 2:
                return new ViewHolder(getView(parent, R.layout.item_my));
            default:
                return new SmallViewHolder(getView(parent, R.layout.item_small_image_title));
        }
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
     * 自己定义实现，获取单个条目对象
     *
     * @param position
     * @return
     */
    public MyItem getItem(int position) {
        return list.get(position);
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String str = list.get(position).string;
        if (holder instanceof ViewHolder) {

            ViewHolder mHolder = (ViewHolder) holder;
            mHolder.hello.setText(str);

        } else if (holder instanceof BigViewHolder) {
            BigViewHolder bigViewHolder = (BigViewHolder) holder;

        } else if (holder instanceof IconViewHolder) {
            IconViewHolder iconViewHolder = (IconViewHolder) holder;

        } else {
            SmallViewHolder smallViewHolder = (SmallViewHolder) holder;

        }
    }

    /**
     * 多条目展示，获取条目类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        MyItem item = list.get(position);
        return item.type;
    }

    /**
     * 获取数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hello)
        TextView hello;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class BigViewHolder extends RecyclerView.ViewHolder {
        public BigViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class SmallViewHolder extends RecyclerView.ViewHolder {
        public SmallViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class IconViewHolder extends RecyclerView.ViewHolder {
        public IconViewHolder(View itemView) {
            super(itemView);
        }
    }
}
