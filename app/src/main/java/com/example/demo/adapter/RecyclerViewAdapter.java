package com.example.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo.R;
import com.example.demo.activity.PictureDetail;
import com.example.demo.model.ImgModel;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView 布局适配器
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ImgModel> imglistmodel;
    private static Context mContext;

    public RecyclerViewAdapter(Context mContext, List<ImgModel> imglist) {
        this.mContext = mContext;
        imglistmodel = imglist;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  GridImageView;

        public ViewHolder(View view) {
            super(view);
            GridImageView = (ImageView) view.findViewById(R.id.GridImage);

        }

        void bind(final ImgModel data) {
            // Glide.with(mContext).load("http://ww1.sinaimg.cn/large/610dc034jw1f6e1f1qmg3j20u00u0djp.jpg").apply(new RequestOptions().centerCrop()).into(GridImage);
            Glide.with(mContext).load(data.getImageId()).apply(new RequestOptions().centerCrop()).into(GridImageView);
            GridImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PictureDetail.class);
                    intent.putExtra("pic_url", data.getImageId());
                    mContext.startActivity(intent);
                }
            });
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridimage_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(imglistmodel.get(position));

    }

    public int getItemCount() {
        return imglistmodel.size();
    }




}