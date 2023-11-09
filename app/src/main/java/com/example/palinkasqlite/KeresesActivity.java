package com.example.palinkasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class KeresesActivity extends AppCompatActivity {

    private Button buttonKeresesVissza;
    private Button buttonKeres;
    private EditText editTextFozoKeres;
    private EditText editTextGyumolcsKeres;
    private Intent intent;
    private TextView textViewKereses;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kereses);
        init();
        buttonKeresesVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(KeresesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonKeres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fozo = editTextFozoKeres.getText().toString();
                String gyumolcs = editTextGyumolcsKeres.getText().toString();

                if (fozo.isEmpty() || gyumolcs.isEmpty()) {
                    Toast.makeText(KeresesActivity.this,
                            "Minden mezőt ki kell tölteni", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor adatok = dbHelper.adatLekerdezes(fozo, gyumolcs);
                    if (adatok.getCount() == 0) {
                        textViewKereses.setText("A megadott adatok nem találhatóak");
                    } else {
                        StringBuilder builder = new StringBuilder();
                        while (adatok.moveToNext()) {
                            builder.append("Alkohol tartalom:").append(adatok.getInt(0)).append("%")
                                    .append("\n");
                        }
                        textViewKereses.setText(builder);
                    }

                }
            }
        });
    }

    public void init() {
        buttonKeresesVissza = findViewById(R.id.buttonKeresesVissza);
        buttonKeres = findViewById(R.id.buttonKeres);
        editTextFozoKeres = findViewById(R.id.editTextFozoKeres);
        editTextGyumolcsKeres = findViewById(R.id.editTextGyumolcsKeres);
        textViewKereses = findViewById(R.id.textViewKereses);
        dbHelper = new DBHelper(KeresesActivity.this);
    }
}