package com.example.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demo.R;

import java.util.List;
import java.util.Map;

public class HslMqttShowAdapter2 extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<Map<String, Object>> list;

    public HslMqttShowAdapter2(Context context, List<Map<String , Object>> list ){
        layoutInflater = LayoutInflater.from(context);
        this.list = list;

    }


    @Override
    public int getCount() {
        //在此适配器方法返回的是中所代表的数据集中的条目数，
        //我们可以获取数据的size（）方法知道数据数量。从而决定需要多少列表项
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        //获取数据集中与指定索引对应的数据项
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        //获取在列表中与指定索引对应的行id
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取一个在数据集中指定索引的视图来显示数据
        convertView = layoutInflater.inflate(R.layout.item_hslmqtt_adapter2 , null);

        TextView aa = convertView.findViewById(R.id.aa);
        TextView bb = convertView.findViewById(R.id.bb);
        TextView cc = convertView.findViewById(R.id.cc);
        TextView dd = convertView.findViewById(R.id.dd);
        TextView ee = convertView.findViewById(R.id.ee);


        Map map = list.get(position);
        aa.setText((String)map.get("aa"));
        bb.setText((String)map.get("bb"));
        cc.setText((String)map.get("cc"));
        dd.setText((String)map.get("dd"));
        ee.setText((String)map.get("ee"));


        return convertView;
    }
}
