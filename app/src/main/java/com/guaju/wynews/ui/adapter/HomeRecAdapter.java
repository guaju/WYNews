package com.guaju.wynews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guaju.wynews.R;

import java.util.ArrayList;

/**
 * Created by root on 17-6-5.
 */

public class HomeRecAdapter extends RecyclerView.Adapter<HomeRecAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> list;
    private LayoutInflater inflater;

    public HomeRecAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.home_item,null,false));
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(list.get(position));

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
//        public ImageView iv;
        public TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
        }

    }
}
