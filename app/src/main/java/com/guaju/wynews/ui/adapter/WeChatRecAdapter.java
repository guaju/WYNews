package com.guaju.wynews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guaju.wynews.R;
import com.guaju.wynews.bean.WeChatNewsBean;
import com.guaju.wynews.ui.activity.WeChatDetailActivity;

import java.util.ArrayList;

/**
 * Created by root on 17-6-5.
 */

public class WeChatRecAdapter extends RecyclerView.Adapter<WeChatRecAdapter.MyViewHolder> {
    private Context context;
    private ArrayList list;
    private LayoutInflater inflater;

    public WeChatRecAdapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.wechat_item,null,false));
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final WeChatNewsBean.NewslistBean bean = (WeChatNewsBean.NewslistBean) list.get(position);
        if (bean.getTitle()!=null){
            holder.title.setText(bean.getTitle());
        }
        if (bean.getDescription()!=null){

        holder.des.setText(bean.getDescription());
        }
        if (bean.getCtime()!=null){
        holder.time.setText(bean.getCtime());
        }
        if(bean.getPicUrl()!=null){
        Glide.with(context)
                .load(bean.getPicUrl())
                .into(holder.iv);
        }
        if (!TextUtils.isEmpty(bean.getUrl())){
            /**
             * let the view can click
              */
        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WeChatDetailActivity.class);
                intent.putExtra("url",bean.getUrl());
                context.startActivity(intent);
            }
        });}
    }

    @Override
    public int getItemCount() {
        if(list!=null) {
            return list.size();
        }else{
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv;
        public TextView title,des,time;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.iv);
            title=(TextView)itemView.findViewById(R.id.title);
            des=(TextView)itemView.findViewById(R.id.des);
            time=(TextView)itemView.findViewById(R.id.time);
        }

    }
}
