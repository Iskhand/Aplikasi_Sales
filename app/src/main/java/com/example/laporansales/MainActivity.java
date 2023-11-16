package com.example.laporansales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laporansales.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private DatabaseReference database;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pkl-laporan-default-rtdb.firebaseio.com/");

        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }else {
            binding.username.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        }

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        binding.date.setText(currentDate);
        DateFormat jam = new SimpleDateFormat(" HH : mm ");
        // Class Tampil Harga
        tampilHarga();


        // Dialog Chek
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_check);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_dialog));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.setCancelable(false);
        //      ------var tabel user-------
        String nama = binding.username.getText().toString();
        String tanggal = binding.date.getText().toString();

//      ------var tampilan check-------
        ImageView yes = dialog.findViewById(R.id.ivYes);
        ImageView no = dialog.findViewById(R.id.ivNo);
        TextView cnama = dialog.findViewById(R.id.cnama);
        TextView carea = dialog.findViewById(R.id.carea);
        TextView cdepo = dialog.findViewById(R.id.cdepo);
        TextView cjhitam = dialog.findViewById(R.id.cjhitam);
        TextView cnhitam = dialog.findViewById(R.id.cnhitam);
        TextView crhitam = dialog.findViewById(R.id.crhitam);
        TextView cjclassic = dialog.findViewById(R.id.cjclassic);
        TextView cnclassic = dialog.findViewById(R.id.cnclassic);
        TextView crclassic = dialog.findViewById(R.id.crclassic);
        TextView cjcoklat = dialog.findViewById(R.id.cjcoklat);
        TextView cncoklat = dialog.findViewById(R.id.cncoklat);
        TextView crcoklat = dialog.findViewById(R.id.crcoklat);
        TextView cjmerah = dialog.findViewById(R.id.cjmerah);
        TextView cnmerah = dialog.findViewById(R.id.cnmerah);
        TextView crmerah = dialog.findViewById(R.id.crmerah);
        TextView cjjawas = dialog.findViewById(R.id.cjjawas);
        TextView cnjawas = dialog.findViewById(R.id.cnjawas);
        TextView crjawas = dialog.findViewById(R.id.crjawas);
        TextView cnoutlet = dialog.findViewById(R.id.cnoutlet);
        TextView caoutlet = dialog.findViewById(R.id.caoutlet);
        TextView csetor = dialog.findViewById(R.id.csetor);
        TextView ctitip = dialog.findViewById(R.id.ctitip);
        TextView cjumlah = dialog.findViewById(R.id.cjumlah);



        binding.btKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.jmlHitam.getText().toString().isEmpty() || binding.jmlClassic.getText().toString().isEmpty()|| binding.jmlCoklat.getText().toString().isEmpty() || binding.jmlMerah.getText().toString().isEmpty()||binding.jmlJawas.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Mohon lengkapi data", Toast.LENGTH_SHORT).show();
                }else {
                    cnama.setText(binding.username.getText().toString());
                    carea.setText(binding.area.getText().toString());
                    cdepo.setText(binding.depo.getText().toString());
                    cjhitam.setText(binding.jmlHitam.getText().toString());
                    cnhitam.setText(binding.nilaiHitam.getText().toString());
                    cjclassic.setText(binding.jmlClassic.getText().toString());
                    cnclassic.setText(binding.nilaiClassic.getText().toString());
                    cjcoklat.setText(binding.jmlCoklat.getText().toString());
                    cncoklat.setText(binding.nilaiCoklat.getText().toString());
                    cjmerah.setText(binding.jmlMerah.getText().toString());
                    cnmerah.setText(binding.nilaiMerah.getText().toString());
                    cjjawas.setText(binding.jmlJawas.getText().toString());
                    cnjawas.setText(binding.nilaiJawas.getText().toString());
                    cnoutlet.setText(binding.outlet.getText().toString());
                    caoutlet.setText(binding.aloutlet.getText().toString());
                    csetor.setText(binding.setor.getText().toString());
                    ctitip.setText(binding.titip.getText().toString());


                    int a = Integer.parseInt(binding.jmlHitam.getText().toString());
                    int b = Integer.parseInt(binding.jmlClassic.getText().toString());
                    int c = Integer.parseInt(binding.jmlCoklat.getText().toString());
                    int d = Integer.parseInt(binding.jmlMerah.getText().toString());
                    int e = Integer.parseInt(binding.jmlJawas.getText().toString());

                    int f = Integer.parseInt(binding.nilaiHitam.getText().toString());
                    int g = Integer.parseInt(binding.nilaiClassic.getText().toString());
                    int h = Integer.parseInt(binding.nilaiCoklat.getText().toString());
                    int i = Integer.parseInt(binding.nilaiMerah.getText().toString());
                    int j = Integer.parseInt(binding.nilaiJawas.getText().toString());

                    int hitam = a * f;
                    crhitam.setText(String.valueOf(hitam));
                    int classic = b * g;
                    crclassic.setText(String.valueOf(classic));
                    int coklat = c * h;
                    crcoklat.setText(String.valueOf(coklat));
                    int merah = d * i;
                    crmerah.setText(String.valueOf(merah));
                    int jawas = e * j;
                    crjawas.setText(String.valueOf(jawas));
                    int sum = hitam + classic + coklat + merah + jawas;
                    cjumlah.setText(String.valueOf(sum));
                    dialog.show();
                }
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(carea.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Mohon Isi Area", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(cdepo.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Mohon Isi Depo",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(cjumlah.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Check data dahulu",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(caoutlet.getText().toString())) {
                    Toast.makeText(getApplicationContext(),"Mohon isi alamat toko",Toast.LENGTH_SHORT).show();
                }else {
                    csetor.setText("0");
                    ctitip.setText("0");

                    database = FirebaseDatabase.getInstance().getReference("data").child(nama).push();

                    database.child("Tanggal").setValue(binding.date.getText().toString() + ", Pukul : "+ jam.format(new Date()));
                    database.child("Area").setValue(binding.area.getText().toString());
                    database.child("Hitam").setValue(binding.jmlHitam.getText().toString());
                    database.child("Classic").setValue(binding.jmlClassic.getText().toString());
                    database.child("Coklat").setValue(binding.jmlCoklat.getText().toString());
                    database.child("Merah").setValue(binding.jmlMerah.getText().toString());
                    database.child("Jawas").setValue(binding.jmlJawas.getText().toString());
                    database.child("Total").setValue(cjumlah.getText().toString());
                    database.child("Setor").setValue(binding.setor.getText().toString());
                    database.child("Titip").setValue(binding.titip.getText().toString());
                    database.child("Nama_Outlet").setValue(binding.outlet.getText().toString());
                    database.child("Alamat_Outlet").setValue(binding.aloutlet.getText().toString());
                    database.child("Depo").setValue(binding.depo.getText().toString());

                    Toast.makeText(getApplicationContext(),"Data Tersimpan",Toast.LENGTH_SHORT).show();

                    binding.jmlHitam.setText("");
                    binding.jmlClassic.setText("");
                    binding.jmlCoklat.setText("");
                    binding.jmlMerah.setText("");
                    binding.jmlJawas.setText("");
                    binding.setor.setText("");
                    binding.titip.setText("");
                    binding.outlet.setText("");
                    binding.aloutlet.setText("");
                    binding.area.setText("");
                    binding.depo.setText("");
                    dialog.hide();
                    startActivity(new Intent(getApplicationContext(), ViewActivity.class));
                    finish();
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
    }
    private void tampilHarga() {
        database = FirebaseDatabase.getInstance().getReference("produk");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    binding.nilaiHitam.setText(item.child("hitam").getValue(String.class));
                    binding.nilaiClassic.setText(item.child("classic").getValue(String.class));
                    binding.nilaiCoklat.setText(item.child("coklat").getValue(String.class));
                    binding.nilaiMerah.setText(item.child("merah").getValue(String.class));
                    binding.nilaiJawas.setText(item.child("jawas").getValue(String.class));
                }
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