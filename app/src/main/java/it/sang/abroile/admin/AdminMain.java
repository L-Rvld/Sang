package it.sang.abroile.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import it.sang.abroile.R;

public class AdminMain extends AppCompatActivity {
    Button tambah, hapus, laporan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Admin ABroiler");

        tambah = findViewById(R.id.btntambah);
        hapus = findViewById(R.id.btnhapus);
        laporan = findViewById(R.id.btnlaporan);

        tambah.setOnClickListener(view ->
                startActivity(new Intent(AdminMain.this, MasterPenyakit.class)
                        .putExtra("req","tambah")));

        hapus.setOnClickListener(view ->
                startActivity(new Intent(AdminMain.this, MasterPenyakit.class)
                        .putExtra("req","hapus")));

        laporan.setOnClickListener(view ->{
            startActivity(new Intent(AdminMain.this, LaporanAdmin.class));
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}