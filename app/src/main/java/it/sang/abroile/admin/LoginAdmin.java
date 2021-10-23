package it.sang.abroile.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import it.sang.abroile.R;

public class LoginAdmin extends AppCompatActivity {

    EditText e1, e2, e3, e4, e5, e6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Login Admin");

        e1 = findViewById(R.id.pass1);
        e2 = findViewById(R.id.pass2);
        e3 = findViewById(R.id.pass3);
        e4 = findViewById(R.id.pass4);
        e5 = findViewById(R.id.pass5);
        e6 = findViewById(R.id.pass6);

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (e1.getText().length() == 1) {
                    e1.clearFocus();
                    e2.requestFocus();
                    e2.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (e2.getText().length() == 1) {
                    e2.clearFocus();
                    e3.requestFocus();
                    e3.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (e3.getText().length() == 1) {
                    e3.clearFocus();
                    e4.requestFocus();
                    e4.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (e4.getText().length() == 1) {
                    e4.clearFocus();
                    e5.requestFocus();
                    e5.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (e5.getText().length() == 1) {
                    e5.clearFocus();
                    e6.requestFocus();
                    e6.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        e6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (e6.getText().length() == 1) {
                    checkCoode(e1, e2, e3, e4, e5, e6);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void checkCoode(EditText e1, EditText e2, EditText e3, EditText e4, EditText e5, EditText e6) {
        String kode = "123321";
        String user = e1.getText().toString() + "" + e2.getText().toString() + "" + e3.getText().toString() + "" + e4.getText().toString() + "" + e5.getText().toString() + "" + e6.getText().toString();
        if (user.equals(kode)) {
            startActivity(new Intent(LoginAdmin.this, AdminMain.class));
            finish();
        } else {
            e1.requestFocus();
            e1.setFocusable(true);
            e1.setText("");
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
            Toast.makeText(this, "Kode Salah, Hubungi Owner", Toast.LENGTH_SHORT).show();
        }
        Log.d("TAG", "checkCoode: " + user);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
