package com.example.my_smk_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_smk_app.Models.ListHomeModel;
import com.example.my_smk_app.R;

import java.util.ArrayList;

public class ListHomeAdapter extends RecyclerView.Adapter<ListHomeAdapter.ViewHolder> {

    private ArrayList<ListHomeModel> listHomeModels;

    public ListHomeAdapter(ArrayList<ListHomeModel> listHomeModels) {
        this.listHomeModels = listHomeModels;
    }


    @NonNull
    @Override
    public ListHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListHomeAdapter.ViewHolder holder, int position) {
        holder.nama_mapel.setText(listHomeModels.get(position).getNamaMapel());
        holder.logo_mapel.setImageResource(listHomeModels.get(position).getLogoMapel());

    }

    @Override
    public int getItemCount() {
        return listHomeModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama_mapel;
        ImageView logo_mapel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama_mapel = itemView.findViewById(R.id.mapel);
            logo_mapel = itemView.findViewById(R.id.logo);
        }
    }
}