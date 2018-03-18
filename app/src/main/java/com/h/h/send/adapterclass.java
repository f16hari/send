package com.h.h.send;

import android.support.v7.widget.CardView;
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
    private Boolean b;


    public adapterclass(List<String> messages)
    {
        this.messages=messages;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rowsend, parent, false));

        }

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false));


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String s=messages.get(position);
        char[] a=new char[50];
        a=s.toCharArray();
        s=s.substring(0,s.length()-1);
        holder.m.setText(s);

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        String s=messages.get(position);
        char[] a=new char[50];
        a=s.toCharArray();
        if(a[s.length()-1]=='t')
            return 0;
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView m;
        public ViewHolder(View itemView) {
            super(itemView);
            m=(TextView)itemView.findViewById(R.id.messageItem);

        }
    }
}
