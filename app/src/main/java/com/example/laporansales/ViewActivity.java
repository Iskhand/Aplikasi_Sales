package com.example.laporansales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.laporansales.databinding.ActivityViewBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ActivityViewBinding binding;

    private DatabaseReference database;
    private DataAdapter adapter;
    private ArrayList<Data> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String nama = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        database = FirebaseDatabase.getInstance().getReference("data").child(nama);
        binding.rvData.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvData.setLayoutManager(layoutManager);
        binding.rvData.setItemAnimator(new DefaultItemAnimator());


        tampilData();
        binding.fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    private void tampilData() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList = new ArrayList<>();

                for (DataSnapshot item : snapshot.getChildren()){
                    Data his = new Data();
                    his.setTanggal(item.child("Tanggal").getValue(String.class));
                    his.setArea(item.child("Area").getValue(String.class));
                    his.setHitam(item.child("Hitam").getValue(String.class));
                    his.setClassic(item.child("Classic").getValue(String.class));
                    his.setCoklat(item.child("Coklat").getValue(String.class));
                    his.setMerah(item.child("Merah").getValue(String.class));
                    his.setJawas(item.child("Jawas").getValue(String.class));
                    his.setJumlah(item.child("Total").getValue(String.class));
                    his.setSetor(item.child("Setor").getValue(String.class));
                    his.setTitip(item.child("Titip").getValue(String.class));
                    his.setOutlet(item.child("Nama_Outlet").getValue(String.class));
                    his.setAlamat(item.child("Alamat_Outlet").getValue(String.class));
                    his.setDepo(item.child("Depo").getValue(String.class));
                    arrayList.add(his);
                }
                adapter = new DataAdapter(arrayList, ViewActivity.this);
                binding.rvData.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(getApplicationContext(), Dashboard.class));
        finish();
    }
}