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


public class HslMqttShowAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<Map<String, Object>> list;

    public HslMqttShowAdapter(Context context,List<Map<String , Object>> list ){
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
        convertView = layoutInflater.inflate(R.layout.item_hslmqtt_adapter , null);

        TextView a = convertView.findViewById(R.id.a);
        TextView b = convertView.findViewById(R.id.b);
        TextView c = convertView.findViewById(R.id.c);
        TextView d = convertView.findViewById(R.id.d);
        TextView e = convertView.findViewById(R.id.e);
        TextView f = convertView.findViewById(R.id.f);

        Map map = list.get(position);
        a.setText((String)map.get("a"));
        b.setText((String)map.get("b"));
        c.setText((String)map.get("c"));
        d.setText((String)map.get("d"));
        e.setText((String)map.get("e"));
        f.setText((String)map.get("f"));

        return convertView;
    }
}
