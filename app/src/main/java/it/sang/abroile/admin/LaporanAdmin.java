package it.sang.abroile.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.sang.abroile.R;
import it.sang.abroile.utils.API;
import it.sang.abroile.utils.AdapterLaporan;
import it.sang.abroile.utils.ModelLaporan;

public class LaporanAdmin extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    ArrayList<ModelLaporan> list = new ArrayList<>();
    API apiService;
    Dialog dialog;
    AdapterLaporan adapterLaporan;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_admin);

        txt = findViewById(R.id.textLap);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Kritik Dan Saran");

        apiService = new API();
        dialog = new Dialog(this, android.R.style.ThemeOverlay_Material);
        dialog.setContentView(R.layout.dialog_laporan);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        recyclerView = findViewById(R.id.recyLaporan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        getData();
    }

    private void getData() {
        list.clear();
        progressDialog.setTitle("Mengambil Data");
        progressDialog.show();
        StringRequest ambilData = new StringRequest(Request.Method.GET, apiService.getApi_service() + "Kritik/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.isNull("data")){
                        progressDialog.dismiss();
                        recyclerView.setVisibility(View.GONE);
                        txt.setVisibility(View.VISIBLE);
                    }else {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject kritik = jsonArray.getJSONObject(i);
                            ModelLaporan modelLaporan = new ModelLaporan(kritik.getString("id_kritik"), kritik.getString("nama"), kritik.getString("no"), kritik.getString("email"), kritik.getString("laporan"));
                            list.add(modelLaporan);
                        }
                        adapterLaporan = new AdapterLaporan(list, LaporanAdmin.this);
                        recyclerView.setAdapter(adapterLaporan);
                        adapterLaporan.notifyDataSetChanged();
                        recyclerView.setVisibility(View.VISIBLE);
                        txt.setVisibility(View.GONE);
                        progressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LaporanAdmin.this, "Error, Ulangi Lagi", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LaporanAdmin.this, "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onErrorResponse: " + error);
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(ambilData);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}