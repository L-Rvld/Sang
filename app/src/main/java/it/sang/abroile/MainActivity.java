package it.sang.abroile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import it.sang.abroile.admin.LoginAdmin;

public class MainActivity extends AppCompatActivity {
    int clickcount=10;
    TextView name;
    CardView diag, info, bantuan, tntg, keluar;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();

        this.getSupportActionBar().setDisplayOptions(actionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        View view = getSupportActionBar().getCustomView();
        name = view.findViewById(R.id.actionbarTitle);
        diag = findViewById(R.id.cardDiag);
        info = findViewById(R.id.cardInfo);
        bantuan = findViewById(R.id.cardBantuan);
        tntg = findViewById(R.id.cardTntng);
        keluar = findViewById(R.id.cardKeluar);

        diag.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, DiagnoseActivity.class)));

        info.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TentangActivity.class)));

        bantuan.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, BantuanActivity.class)));

        keluar.setOnClickListener(view1 -> exitclick());

        tntg.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,InfoActivity.class)));

    };
    public void exitclick(){
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.chicken)
                .setTitle(R.string.app_name)
                .setMessage("Kamu yakin ingin keluar?")
                .setPositiveButton("OK", (dialog, which) -> finish())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }

    @Override
    public void onBackPressed() {
        exitclick();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        name.setOnClickListener(v -> {
            clickcount=clickcount-1;
            if(clickcount<=5)
            {
                final Toast toast = Toast.makeText(getApplicationContext(),"Klik "+clickcount+"x lagi untuk masuk ke Login Admin", Toast.LENGTH_SHORT);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 200);
            }
            if (clickcount==0)
            {
                name.setClickable(false);
                MenuItem item = menu.findItem(R.id.login);
                item.setVisible(true);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idLogin;
        idLogin = item.getItemId();
        if (idLogin == R.id.login) {
            startActivity(new Intent(MainActivity.this, LoginAdmin.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
