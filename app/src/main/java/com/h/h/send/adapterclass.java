package com.h.h.send;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HARI JEYACHANDRAN on 2/28/2018.
 */

public class adapterclass extends RecyclerView.Adapter<adapterclass.ViewHolder> {

    private List<String> messages=new ArrayList<>();

    public adapterclass(List<String> messages)
    {
        this.messages=messages;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.m.setText(messages.get(position));

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView m;
        public ViewHolder(View itemView) {
            super(itemView);
            m=(TextView)itemView.findViewById(R.id.messageItem);
        }
    }
}
