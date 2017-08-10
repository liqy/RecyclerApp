package com.bwie.recyclerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bwie.recyclerapp.adapter.ComicAdapter;
import com.bwie.recyclerapp.adapter.MyLine;
import com.bwie.recyclerapp.model.CartoonItem;
import com.bwie.recyclerapp.model.ComicItem;
import com.bwie.recyclerapp.model.ReturnData;
import com.bwie.recyclerapp.model.RootData;
import com.bwie.recyclerapp.model.U17Item;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    ComicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
        getNetData();
    }

    /**
     * 初始化界面
     */
    private void initUI() {
        ButterKnife.bind(this);
        GridLayoutManager manger = new GridLayoutManager(this, 12);
        manger.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                CartoonItem item = adapter.getItem(position);

                switch (item.type) {
                    case 0:
                        return 12;
                    case 11:
                        return 3;
                    case 99:
                        return 12;
                    case 12:
                        return 6;
                    case 7:
                        return 4;
                    case 5:
                        return 6;
                    case 3:
                        return 6;
                    case 13:
                        return 12;
                    case 10:
                        return 4;
                    case 9:
                        return 6;
                }

                return 99;
            }
        });

        adapter = new ComicAdapter();
        recycler.addItemDecoration(new MyLine(this,R.color.colorAccent));
        recycler.setLayoutManager(manger);
        recycler.setAdapter(adapter);
    }

    /**
     * 网络请求
     */
    private void getNetData() {
        String url = "http://app.u17.com/v3/appV3_3/android/phone/comic/boutiqueListNew?sexType=1&android_id=4058040115108878&v=3330110&model=GT-P5210&come_from=Tg002";

        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                parseData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * 数据解析
     *
     * @param result
     */
    private void parseData(String result) {
        Log.d(getLocalClassName(), result);

        RootData rootData = new Gson().fromJson(result, RootData.class);

        //填入Adapter数据源
        List<CartoonItem> list = new ArrayList<CartoonItem>();

        if (rootData != null && rootData.data != null) {
            if (rootData.data.stateCode == 1 && rootData.data.returnData != null) {
                ReturnData data = rootData.data.returnData;
                //轮播图
                CartoonItem banner = new CartoonItem(0, data.galleryItems);
                list.add(banner);

                for (U17Item u17Item : data.comicLists) {
                    //四个模块按钮
                    if (u17Item.comicType == 11) {
                        for (ComicItem comic : u17Item.comics) {
                            list.add(new CartoonItem(u17Item.comicType, comic));
                        }
                    } else {
                        list.add(new CartoonItem(u17Item));//标题栏
                        for (ComicItem comic : u17Item.comics) {
                            list.add(new CartoonItem(u17Item.comicType, comic));
                        }
                    }
                }

            }
        }

        adapter.setItems(list);
    }


}
