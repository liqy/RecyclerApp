package com.bwie.recyclerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.recyclerapp.adapter.MyAdapter;
import com.bwie.recyclerapp.adapter.MyItem;
import com.bwie.recyclerapp.adapter.MyLine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    Unbinder unbinder;

    @BindView(R.id.recycler)
    RecyclerView recycler;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recycler.addItemDecoration(new MyLine(this,R.color.colorAccent));
        GridLayoutManager manager = new GridLayoutManager(this, 12);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {//实现此接口，可以定义电商首页布局
            @Override
            public int getSpanSize(int position) {

                MyItem item = adapter.getItem(position);

                switch (item.type) {
                    case 0:
                        return 12;
                    case 1:
                        return 3;
                    case 2:
                        return 12;
                    default:
                        return 4;
                }

            }
        });

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);


        List<MyItem> list = new ArrayList<>();

        for (int i = 0; i < 110; i++) {
            switch (i) {
                case 0:
                    list.add(new MyItem(i + "条",0));
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    list.add(new MyItem(i + "条",1));
                    break;
                case 5:
                    list.add(new MyItem(i + "条",2));
                    break;
                default:
                    list.add(new MyItem(i + "条",3));
                    break;
            }

        }
        adapter = new MyAdapter(list);
        recycler.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
