package com.example.my_smk_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_smk_app.Activity.DetailMateriActivity;
import com.example.my_smk_app.Models.MapelModel;
import com.example.my_smk_app.Models.RombelModel;
import com.example.my_smk_app.R;

import java.util.List;

public class ListMateriAdapter extends RecyclerView.Adapter<ListMateriAdapter.ViewHolder> {

    private List<MapelModel> mapelModels;

    public void setSearchList(List<MapelModel> dataSearchList) {
        this.mapelModels = dataSearchList;
        notifyDataSetChanged();
    }

    public ListMateriAdapter(Context context, List<MapelModel> mapelModels) {
        this.mapelModels = mapelModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_materi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MapelModel materiModel = mapelModels.get(position);
        RombelModel rombelModel = materiModel.getRombel();

        holder.bg_materi.setImageResource(R.drawable.ic_launcher_background );
        holder.tv_mapel.setText(materiModel.getNamaMapel());

        if (rombelModel != null) {
            holder.tv_kelas.setText(rombelModel.getNamaKelas());
        } else {
            holder.tv_kelas.setText("Kelas Not Available");
        }

        holder.rc_card.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailMateriActivity.class);
            intent.putExtra("IdMateri", materiModel.getIdMapel());
            intent.putExtra("JudulMateri", materiModel.getKelasId());
            intent.putExtra("Deskripsi", materiModel.getNamaMapel());
            intent.putExtra("Pertemuan", materiModel.getNipId());

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mapelModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_mapel, tv_kelas;
        CardView rc_card;
        ImageView bg_materi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_mapel = itemView.findViewById(R.id.tv_mapel);
            tv_kelas = itemView.findViewById(R.id.tv_kelas);
            bg_materi = itemView.findViewById(R.id.bg_materi);
            rc_card = itemView.findViewById(R.id.rc_card);
        }
    }
}