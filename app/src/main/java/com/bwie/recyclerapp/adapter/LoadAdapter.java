package com.bwie.recyclerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.recyclerapp.R;
import com.bwie.recyclerapp.model.ComicItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liqy on 8/10/2017.
 */

public class LoadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ComicItem> items;
    Context context;
    OnItemClickListener listener;//定义监听事件

    public LoadAdapter(Context context) {
        this.items = new ArrayList<>();
        this.context = context;
    }

    /**
     * 加载新数据
     *
     * @param list
     * @param page
     */
    public void setItems(List<ComicItem> list, int page) {
        if (page == 1) this.items.clear();
        this.items.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 设置监听事件
     *
     * @param listener
     */
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ComicItem item = items.get(position);

        if (holder instanceof ComicViewHolder) {
            ComicViewHolder viewHolder = (ComicViewHolder) holder;
            viewHolder.title.setText(item.name);
            Glide.with(context).load(item.cover).into(viewHolder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)//记得判断null
                    listener.onItemClick(view, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    static class ComicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.m_title)
        TextView title;

        @BindView(R.id.m_image)
        ImageView imageView;

        public ComicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 点击事件回掉接口
     */
    public interface OnItemClickListener {
        void onItemClick(View var2, int var3);
    }
}
