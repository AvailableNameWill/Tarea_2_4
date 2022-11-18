package com.example.tarea_2_4.Clases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea_2_4.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<signaturess> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtDescS;
        ImageView imgSign;

        public MyViewHolder(View itemView){
            super(itemView);
            this.txtDescS = (TextView) itemView.findViewById(R.id.txtDescS);
            this.imgSign = (ImageView) itemView.findViewById(R.id.imgSign);
        }
    }

    public CustomAdapter(ArrayList<signaturess> data){
        dataSet = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        MyViewHolder nHolder = new MyViewHolder(view);
        return nHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView txtDesc = holder.txtDescS;
        ImageView imgSign = holder.imgSign;

        txtDesc.setText(dataSet.get(position).getDescripcion());
        imgSign.setImageBitmap(dataSet.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
