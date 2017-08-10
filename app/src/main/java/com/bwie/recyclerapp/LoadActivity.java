package com.bwie.recyclerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bwie.recyclerapp.adapter.LoadAdapter;
import com.bwie.recyclerapp.adapter.MyLine;
import com.bwie.recyclerapp.model.RootData;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadActivity extends AppCompatActivity implements XRecyclerView.LoadingListener ,LoadAdapter.OnItemClickListener{

    @BindView(R.id.xRecycler)
    XRecyclerView xRecycler;

    int page = 1;

    LoadAdapter adapter;

    String url = "http://app.u17.com/v3/appV3_3/android/phone/list/commonComicList?argValue=23&argName=sort&argCon=0&android_id=4058040115108878&v=3330110&model=GT-P5210&come_from=Tg002&page=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        initUI();
        getData(page);
    }

    private void initUI() {
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecycler.setLayoutManager(layoutManager);

        xRecycler.addItemDecoration(new MyLine(this,R.drawable.item_line));

        adapter = new LoadAdapter(this);
        xRecycler.setAdapter(adapter);

        xRecycler.setLoadingListener(this);

        adapter.setListener(this);

    }

    private void getData(final int page) {
        x.http().get(new RequestParams(url + page), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                RootData rootData = new Gson().fromJson(result, RootData.class);

                //判断空指针
                if (rootData != null && rootData.data != null && rootData.data.returnData != null) {
                    adapter.setItems(rootData.data.returnData.comics, page);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                //刷新完成
                xRecycler.refreshComplete();
                xRecycler.loadMoreComplete();
            }
        });
    }

    @Override
    public void onRefresh() {//刷新
        page = 1;
        getData(page);
    }

    @Override
    public void onLoadMore() {// 加载更多
        page++;
        getData(page);
    }

    @Override
    public void onItemClick(View var2, int var3) {
        //TODO 处理点击事件
        Toast.makeText(LoadActivity.this,"位置："+var3,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoadActivity.this,HomeActivity.class));
    }

    @Override
    public void onImageClick(View view, int pos) {
        //TODO 处理点击事件

    }
}
