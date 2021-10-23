package it.sang.abroile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class HasilGejala extends AppCompatActivity {
    TextView listdata;
    ImageView imageView;
    TextView keterangan, obat;
    Bundle bundle;
    String[] img = {"kolera","crd","ecoli","ib","gumboro","berakkapur","fluburung","malaria","snot"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_gejala);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Hasil Diagnosa");

        bundle=getIntent().getExtras();
        obat = findViewById(R.id.obattx);

        int res = getResources().getIdentifier(img[bundle.getInt("img")-1], "drawable",
                this.getPackageName());

        listdata = findViewById(R.id.listdata);
        imageView = findViewById(R.id.imageView);
        keterangan = findViewById(R.id.keterangan);
        imageView.setImageResource(res);
        listdata.setText(bundle.getString("nama")+" ("+bundle.getString("hasil")+"%)");
        keterangan.setText(bundle.getString("des"));
        obat.setText(bundle.getString("obat"));

        TabHost tabhost = (TabHost) findViewById(R.id.tabhost);
        tabhost.setup();

        TabHost.TabSpec spec = tabhost.newTabSpec("Deskripsi Penyakit");

        spec.setContent(R.id.tab1);
        spec.setIndicator("Deskripsi Penyakit");
        tabhost.addTab(spec);

        spec = tabhost.newTabSpec("Pengobatan");

        spec.setContent(R.id.tab2);
        spec.setIndicator("Pengobatan");
        tabhost.addTab(spec);

        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            View v = tabhost.getTabWidget().getChildAt(i);

            TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(getResources().getColor(R.color.white));
            tv.setAllCaps(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}