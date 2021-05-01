/*
package com.example.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.demo.R;
import com.example.demo.tools.SongData;
import java.util.List;
import java.util.Map;

*/
/**
 * BaseAdapter
 * 自定义的list 图文适配器
 *//*



public class mAdapter extends BaseAdapter {

    private Context context;
    private List<SongData> SongInfoList;


    public mAdapter(Context context, List<SongData> songInfoList) {
        this.context=context;
        this.SongInfoList=songInfoList;
    }




    @Override
    public int getCount() {
        return SongInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return SongInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.songlist,null);
        }
        TextView TextViewSongname=(TextView)convertView.findViewById(R.id.simple_name_Tv);
        TextView TextViewSingername=(TextView)convertView.findViewById(R.id.simple_edit_Tv);
        ImageView ImageViewImageView_album=(ImageView) convertView.findViewById(R.id.simple_cover_Iv);

        SongData songDatainfo=SongInfoList.get(position);
        TextViewSongname.setText(songDatainfo.getSongname());
        TextViewSingername.setText(songDatainfo.getSingername());




        return convertView;
    }
}
*/
