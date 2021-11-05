package it.sang.abroile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import it.sang.abroile.admin.MasterPenyakit;
import it.sang.abroile.utils.API;

public class BantuanActivity extends AppCompatActivity {

    EditText nama, email, nohp, kritik;
    Button kirim;
    API api = new API();
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);

        nama = findViewById(R.id.NamaHelp);
        email = findViewById(R.id.EmailHelp);
        nohp = findViewById(R.id.NoHelp);
        kritik = findViewById(R.id.laporanHelp);
        kirim = findViewById(R.id.kirim);

        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.setMessage("Memproses ...");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Bantuan");

        clearing();

        kirim.setOnClickListener(v -> {

            if (TextUtils.isEmpty(nama.getText())) {
                Toast.makeText(BantuanActivity.this, "Nama Kosong", Toast.LENGTH_SHORT).show();
                nama.setError("Masukan Nama Anda");
            } else if (TextUtils.isEmpty(nohp.getText()) && nohp.getText().length() <= 6) {
                Toast.makeText(BantuanActivity.this, "Nomor Salah", Toast.LENGTH_SHORT).show();
                nohp.setError("Masukan Nomor HP Anda");
            } else if (TextUtils.isEmpty(email.getText())&&!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                Toast.makeText(BantuanActivity.this, "Email Salah", Toast.LENGTH_SHORT).show();
                email.setError("Masukan Email Anda");
            } else if (TextUtils.isEmpty(kritik.getText())) {
                Toast.makeText(BantuanActivity.this, "Kritik & Saran Kosong", Toast.LENGTH_SHORT).show();
                kritik.setError("Masukan Kritik & Saran Anda");
            }
            if (!TextUtils.isEmpty(nama.getText())
                    && !TextUtils.isEmpty(email.getText())
                    && !TextUtils.isEmpty(nohp.getText())
                    && !TextUtils.isEmpty(kritik.getText())
                    && Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()
                    && nohp.getText().length() >= 6) {

                dialog.show();
                StringRequest penyakit = new StringRequest(Request.Method.POST, api.getApi_service() + "Kritik/", response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String berhasil = jsonObject.getString("hasil");
                        if (berhasil.equals("sukses")) {
                            Toast.makeText(BantuanActivity.this, "Kritik & Saran Berhasil Terkirim", Toast.LENGTH_SHORT).show();
                            clearing();
                            dialog.dismiss();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(BantuanActivity.this, "Kritik & Saran Gagal Terkirim, Coba Lagi", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        e.printStackTrace();
                    }
                }, error -> {
                    Toast.makeText(BantuanActivity.this, "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    error.printStackTrace();
                }) {
                    @Override

                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<>();
                        param.put("nama", nama.getText().toString());
                        param.put("no", nohp.getText().toString());
                        param.put("email", email.getText().toString());
                        param.put("kritik", kritik.getText().toString());
                        return param;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(penyakit);

            }
        });
    }

    private void clearing() {
        nama.setText("");
        email.setText("");
        nohp.setText("");
        kritik.setText("");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}