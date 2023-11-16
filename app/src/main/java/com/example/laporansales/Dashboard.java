package com.example.laporansales;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.laporansales.databinding.ActivityDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    ActivityDashboardBinding binding;

    Dialog dialogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialogout = new Dialog(Dashboard.this);
        dialogout.setContentView(R.layout.dialog_logout);
        dialogout.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_dialog));
        dialogout.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogout.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialogout.setCancelable(true);

        Button out = dialogout.findViewById(R.id.btLogout);
        Button kel = dialogout.findViewById(R.id.btKeluar);


        binding.addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        binding.viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewActivity.class));
                finish();
            }
        });

        binding.exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.whatsapp.com/send/?phone=628971681850&text=Halo+Admin+PGT&type=phone_number&app_absent";
                Intent wa = new Intent(Intent.ACTION_VIEW);
                wa.setData(Uri.parse(url));
                startActivity(wa);
            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogout.show();

                out.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                });
                kel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogout.hide();
                    }
                });
            }
        });
    }
}