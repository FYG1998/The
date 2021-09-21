package com.example.demo.adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.demo.R;
import com.example.demo.base.APPAplication;
import com.example.demo.model.ItemView;
import java.util.List;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


/**
 * ItemView信息适配器
 * Created by gpk on 2019/6/19.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private OnLongClickListener mLongClickListener;
    private List<ItemView> items;
    private View view;
    private Context context;
    public void setLongClickListener(OnLongClickListener longClickListener) {
        mLongClickListener = longClickListener;
    }
    public interface OnLongClickListener{
        boolean onLongClick(View view, int position);
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView item1TV;
        TextView item2TV;
        TextView item3TV;
        TextView item4TV;

        public ViewHolder(View view){
            super(view);

            item1TV=(TextView) view.findViewById(R.id.item1);
            item2TV=(TextView) view.findViewById(R.id.item2);
            item3TV=(TextView) view.findViewById(R.id.item3);
            item4TV=(TextView) view.findViewById(R.id.item4);
        }
    }
    public ItemAdapter(List<ItemView> itemLst)
    {
        items=itemLst;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        context= APPAplication.getAppContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ItemView item=items.get(position);
        if(item.getItem1().getCorlor()>0)
        {
            setColor(holder.item1TV,item.getItem1().getCorlor());
        }
        holder.item1TV.setText(item.getItem1().getInfo());
        if(item.getItem2().getCorlor()>0)
        {
            setColor(holder.item2TV,item.getItem2().getCorlor());
        }
        holder.item2TV.setText(item.getItem2().getInfo());
        if(item.getItem3().getCorlor()>0)
        {
            setColor(holder.item3TV,item.getItem3().getCorlor());
        }
        holder.item3TV.setText(item.getItem3().getInfo());
        if(item.getItem4().getCorlor()>0)
        {
            setColor(holder.item4TV,item.getItem4().getCorlor());
        }
        holder.item4TV.setText(item.getItem4().getInfo());
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if(mLongClickListener!=null){
//                    mLongClickListener.onLongClick(holder.itemView,position);
//                }
//                return false;
//            }
//        });
    }
    private void setColor(TextView tv, int color)
    {
        tv.setTextColor(ContextCompat.getColor(context,color));
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
