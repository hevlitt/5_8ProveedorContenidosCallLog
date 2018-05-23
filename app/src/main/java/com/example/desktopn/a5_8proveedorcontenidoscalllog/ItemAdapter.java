package com.example.desktopn.a5_8proveedorcontenidoscalllog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ernesto on 21/05/18.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    ArrayList<Item> list;

    public ItemAdapter(ArrayList<Item> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ItemViewHolder itemViewHolder=new ItemViewHolder(v);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.number.setText(list.get(position).getNumber());
        holder.date.setText(list.get(position).getDate());
        holder.duration.setText(list.get(position).getDuration());
        holder.type.setText(list.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView name,number,date,duration,type;
        public ItemViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
            date=itemView.findViewById(R.id.date);
            duration=itemView.findViewById(R.id.duration);
            type=itemView.findViewById(R.id.type);
        }
    }
}
