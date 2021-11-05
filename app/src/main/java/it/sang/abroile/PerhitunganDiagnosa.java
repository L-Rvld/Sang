package it.sang.abroile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.sang.abroile.utils.API;
import it.sang.abroile.utils.AdapterGejala;
import it.sang.abroile.utils.ModelGejala;

public class PerhitunganDiagnosa extends AppCompatActivity {

    RecyclerView recyclerView;
    Bundle bundle;
    API api;
    ProgressDialog dialog;
    TextView tvGetNamaPenyakit;
    String hsailbro;
    Button btnCekHasil;
    public static List<String> integerList ;
    List<ModelGejala> gejalas = new ArrayList<>();
    AdapterGejala gejala ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitungan_diagnosa);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        api=new API();

        dialog=new ProgressDialog(this);
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.setMessage("Memproses ...");

        integerList = new ArrayList<>();

        tvGetNamaPenyakit = findViewById(R.id.textView1);
        tvGetNamaPenyakit.setText(bundle.getString("nama"));
        btnCekHasil = findViewById(R.id.btnAnalis);

        recyclerView = findViewById(R.id.recyGejala);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getGejala();

        btnCekHasil.setOnClickListener(view -> {
            Boolean gas = Boolean.FALSE;
            for (int i=0; i<integerList.size(); i++) {
                if (integerList.get(i).equals("-2")) {
                    Snackbar snackbar = Snackbar
                            .make(view, "Harap Pilih Kepastian Gejala", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    break;
                }else {
                    gas = (i == integerList.size() - 1) ? Boolean.TRUE : Boolean.FALSE;
                }
            }
            if (gas) {
                sendGejala();
            }
        });
    }

    public void getGejala(){
        StringRequest penyakit = new StringRequest(Request.Method.GET, api.getApi_service()+"Diagnosa/?serv=getDataDiagnosa&id_penyakit="+bundle.getInt("id"), response -> {
            Log.d("DRF", "onResponse: "+response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    ModelGejala modelGejala = new ModelGejala(jsonObject1.getString("id_gejala"), "Apakah Ayam Mengalami "+jsonObject1.getString("nama_gejala")+" ?");
                    gejalas.add(modelGejala);
                    integerList.add("-2");
                }
                gejala = new AdapterGejala(gejalas, getApplicationContext());
                recyclerView.setAdapter(gejala);
                gejala.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.d("DRF", "onErrorResponse: "+error));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(penyakit);
    }

    public void sendGejala(){
        dialog.show();
        StringRequest penyakit = new StringRequest(Request.Method.POST, api.getApi_service()+"diagnosa/", response -> {
            Log.d("DRF", "onResponse: "+response);
            try {
                JSONObject jsonObject = new JSONObject(response);
                hsailbro = jsonObject.getString("hasil");
                JSONObject jsonObject1 = jsonObject.getJSONObject("penyakit");
                dialog.dismiss();
                startActivity(new Intent(PerhitunganDiagnosa.this,HasilGejala.class)
                        .putExtra("hasil",hsailbro)
                        .putExtra("nama", jsonObject1.getString("nama_penyakit"))
                        .putExtra("des",jsonObject1.getString("deskripsi"))
                        .putExtra("img",bundle.getInt("id"))
                        .putExtra("obat",jsonObject1.getString("obat")));
                finish();
            } catch (JSONException e) {
                Toast.makeText(PerhitunganDiagnosa.this, "Gagal,coba lagi", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }, error -> Log.e("DRF", "onErrorResponse: ",error )){
            @Override

            protected Map<String, String> getParams() {
                JSONObject jsonObject = new JSONObject();
                try {
                    for (int i = 0; i <integerList.size(); i++){

                        jsonObject.put("data"+i, integerList.get(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Map<String, String> param = new HashMap<>();
                param.put("api","diagnosa");
                param.put("cf",jsonObject.toString());
                param.put("id_penyakit", String.valueOf(bundle.getInt("id")));
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(penyakit);
    }
}