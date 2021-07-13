package com.example.amexmate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Myviewholder> {

    List<String>category;
    List<String>amount;

    public Adapter(List<String>cat,List<String>amt){
        this.category=cat;
        this.amount=amt;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        Myviewholder viewHolder = new Myviewholder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {

        String catt=category.get(position);
        String amtt=amount.get(position);

        holder.amount.setText("$"+amtt);
        holder.category.setText(catt);




    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder {
        TextView category,
                amount;

        public Myviewholder(View itemView) {
            super(itemView);
            category=itemView.findViewById(R.id.category);
            amount=itemView.findViewById(R.id.amount);

        }
    }
}
