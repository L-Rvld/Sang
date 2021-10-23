package it.sang.abroile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class DiagnoseActivity extends AppCompatActivity {

    Button btnLanjut;
    AutoCompleteTextView autoCompleteTextView;
    TextInputLayout textInputLayout;
    String[] penyakit = {"Kolera Unggas", "Chronic Respiratory Disease (CRD)", "Colibacillosis", "Infectious Bronchitis", "Gumboro", "Pullorum / Berak Kapur", "Flu Burung", "Malaria Unggas", "Snot"};
    int idP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Diagnosa ABroile");

        btnLanjut = findViewById(R.id.btnLanjut);
        autoCompleteTextView = findViewById(R.id.acText);
        textInputLayout = findViewById(R.id.menuPenyakit);
        autoCompleteTextView.setDropDownWidth(ViewGroup.LayoutParams.MATCH_PARENT);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, penyakit);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnLanjut.setEnabled(true);
                idP = position;
            }
        });

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiagnoseActivity.this,PerhitunganDiagnosa.class)
                .putExtra("id",idP+1)
                .putExtra("nama",penyakit[idP]));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}