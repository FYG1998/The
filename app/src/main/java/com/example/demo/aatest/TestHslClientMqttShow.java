package com.example.demo.aatest;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.example.demo.BaseActivity;
import com.example.demo.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.adapter.HslMqttShowAdapter;
import com.example.demo.adapter.HslMqttShowAdapter2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHslClientMqttShow extends BaseActivity {

    private Context context;
    private ListView listViewtask, listViewstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testhslclientmqttshow);

        context = this;
        listViewtask = findViewById(R.id.task);
        listViewstate = findViewById(R.id.stackerstate);

        //获取将要显示的数据并绑定在适配器adapter
        HslMqttShowAdapter adapter = new HslMqttShowAdapter(this, getdata());
        listViewtask.setAdapter(adapter);

        HslMqttShowAdapter2 adapter2 = new HslMqttShowAdapter2(this, getdata2());
        listViewstate.setAdapter(adapter2);

    }


    private String[][] datas = new String[][]{
            {"202105001", "出库", "运行", "PT1010", "ExitStationA02", "PT01-01-01-01"},
            {"202105012", "入库", "等待", "PT1234", "EntranceStationA02", "PT01-02-03-01"},
            {"202105234", "出库", "运行", "PT1323", "ExitStationA01", "PT01-01-02-18"},
            {"202105901", "出库", "运行", "PT1320", "ExitStationA01", "PT01-01-01-20"},
            {"202105019", "入库", "运行", "PT1210", "EntranceStationA02", "PT01-03-01-12"}
    };
    private String[][] datas2 = new String[][]{
            {"Stacker1", "正常", "待机", "state", "12:12:56"},
            {"Stacker2", "正常", "运行", "state", "13:11:34"},
            {"Stacker3", "正常", "运行", "state", "15:20:27"},
            {"Stacker4", "正常", "维修", "伺服故障", "17:33:51"},
    };

    private List<Map<String, Object>> getdata() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < datas.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("a", datas[i][0]);
            map.put("b", datas[i][1]);
            map.put("c", datas[i][2]);
            map.put("d", datas[i][3]);
            map.put("e", datas[i][4]);
            map.put("f", datas[i][5]);
            list.add(map);
        }

          /* Map<String,Object> map = new HashMap<String, Object>();
            map.put("a", "任务");
            map.put("b", "任务类型");
            map.put("c", "任务状态");
            map.put("d", "托盘号");
            map.put("e", "起始位置");
            map.put("f", "目的位置");
            list.add(map);*/

        return list;
    }


    private List<Map<String, Object>> getdata2() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < datas2.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("aa", datas2[i][0]);
            map.put("bb", datas2[i][1]);
            map.put("cc", datas2[i][2]);
            map.put("dd", datas2[i][3]);
            map.put("ee", datas2[i][4]);

            list.add(map);
        }

        return list;
    }


}