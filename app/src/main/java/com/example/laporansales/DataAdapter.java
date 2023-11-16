package com.example.laporansales;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder>{

    List<Data> mlist;
    Context context;

    public DataAdapter (List<Data> mlist, Context context){
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public DataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.MyViewHolder holder, int position) {

        final Data item = mlist.get(position);
        holder.tvDate.setText("Tanggal : " + item.getTanggal());
        holder.tvArea.setText("Area : " + item.getArea());
        holder.tvHitam.setText("Rosso Hitam : " + item.getHitam());
        holder.tvClassic.setText("Rosso 16 : " + item.getClassic());
        holder.tvCoklat.setText("Rosso Coklat : " + item.getCoklat());
        holder.tvMerah.setText("Rosso Merah : " + item.getMerah());
        holder.tvJawas.setText("Jawas : " + item.getJawas());
        holder.tvJumlah.setText("Jumlah : " + item.getJumlah());
        holder.tvSetor.setText("Tunai : " + item.getSetor());
        holder.tvTitip.setText("Titip : " + item.getTitip());
        holder.tvOutlet.setText("Nama Outlet : " + item.getOutlet());
        holder.tvAlamat.setText("Alamat Outlet : " + item.getAlamat());
        holder.tvDepo.setText("Depo : " + item.getDepo());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDate,tvArea,tvHitam,tvClassic,tvCoklat,tvMerah,tvJawas,tvJumlah,tvSetor,tvTitip,tvOutlet,tvAlamat,tvDepo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvArea = itemView.findViewById(R.id.tvArea);
            tvHitam = itemView.findViewById(R.id.tvHitam);
            tvClassic = itemView.findViewById(R.id.tvClassic);
            tvCoklat = itemView.findViewById(R.id.tvCoklat);
            tvMerah = itemView.findViewById(R.id.tvMerah);
            tvJawas = itemView.findViewById(R.id.tvJawas);
            tvJumlah = itemView.findViewById(R.id.tvJumlah);
            tvSetor = itemView.findViewById(R.id.tvSetor);
            tvTitip = itemView.findViewById(R.id.tvTitip);
            tvOutlet = itemView.findViewById(R.id.tvOutlet);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvDepo = itemView.findViewById(R.id.tvDepo);
        }
    }
}

