package com.example.demo.activity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.demo.R;
import com.example.demo.adapter.ItemAdapter;
import com.example.demo.base.BaseActivity;
import com.example.demo.base.BaseActivityTwo;
import com.example.demo.model.Entity;
import com.example.demo.model.ItemInfo;
import com.example.demo.model.ItemView;

import java.util.ArrayList;
import java.util.List;

public class StationActivity extends BaseActivityTwo {
    Context context;
    private static final String TAG = "MainActivity";
    private static final String[] LOCATION_AND_CONTACTS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS};

    RecyclerView rvBeforeInfo, rvInfo;
    List<ItemView> beforeInfoLst, infoLst;
    public ItemAdapter beforeAdapter, infoAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        context = this;
        beforeInfoLst = new ArrayList<>();
        infoLst = new ArrayList<>();



        rvBeforeInfo = findViewById(R.id.beforeinfo_recyclerview);
        rvInfo = findViewById(R.id.info_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvBeforeInfo.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        rvBeforeInfo.addItemDecoration(itemDecoration);
        beforeAdapter = new ItemAdapter(beforeInfoLst);
        rvBeforeInfo.setAdapter(beforeAdapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        rvInfo.setLayoutManager(layoutManager2);
        DividerItemDecoration itemDecoration2 = new DividerItemDecoration(this, layoutManager.getOrientation());
        rvInfo.addItemDecoration(itemDecoration2);
        infoAdapter = new ItemAdapter(infoLst);
        rvInfo.setAdapter(infoAdapter);

        beforeInfoLst.addAll(getItemViewEmptyData());
        infoLst.addAll(getItemViewEmptyData());



    }
    /**
     * 空的数据布局UI
     * @return
     */
    private List<ItemView> getItemViewEmptyData() {
        List<ItemView> temps = new ArrayList<>();
        temps.add(new ItemView(
                new ItemInfo("产品文件：", R.color.colorLabel),
                new ItemInfo(""),
                new ItemInfo("图纸文件：", R.color.colorLabel),
                new ItemInfo("")
        ));
        temps.add(new ItemView(
                new ItemInfo("场内批号：", R.color.colorLabel),
                new ItemInfo(""),
                new ItemInfo("产品型号：", R.color.colorLabel),
                new ItemInfo("")
        ));
        temps.add(new ItemView(
                new ItemInfo("机种名称：", R.color.colorLabel),
                new ItemInfo(""),
                new ItemInfo("数量：", R.color.colorLabel),
                new ItemInfo("")
        ));
        temps.add(new ItemView(
                new ItemInfo("进站时间：", R.color.colorLabel),
                new ItemInfo(""),
                new ItemInfo("出站时间：", R.color.colorLabel),
                new ItemInfo("")
        ));
        temps.add(new ItemView(
                new ItemInfo("工步名称：", R.color.colorLabel),
                new ItemInfo(""),
                new ItemInfo("程序名：", R.color.colorLabel),
                new ItemInfo("")
        ));
        temps.add(new ItemView(
                new ItemInfo("设备编号：", R.color.colorLabel),
                new ItemInfo(""),
                new ItemInfo("设备位置：", R.color.colorLabel),
                new ItemInfo("")
        ));
        temps.add(new ItemView(
                new ItemInfo("作业结果：", R.color.colorLabel),
                new ItemInfo(""),
                new ItemInfo("",R.color.colorLabel),
                new ItemInfo("")
        ));
        temps.add(new ItemView(
                new ItemInfo("其它信息：", R.color.colorDec),
                new ItemInfo(""),
                new ItemInfo("备注：", R.color.colorDec),
                new ItemInfo("")
        ));
        return temps;
    }






}