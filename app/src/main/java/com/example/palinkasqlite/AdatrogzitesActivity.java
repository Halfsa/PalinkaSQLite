package com.example.palinkasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdatrogzitesActivity extends AppCompatActivity {

    private Button buttonRogzit;
    private Button buttonRogzitVissza;
    private EditText editTextFozoRogzit;
    private EditText editTextGyumolcsRogzit;
    private EditText editTextAlkoholRogzit;
    private Intent intent;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adatrogzites);
        init();

        buttonRogzitVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AdatrogzitesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fozo = editTextFozoRogzit.getText().toString();
                String gyumolcs = editTextGyumolcsRogzit.getText().toString();
                String alkohol = editTextAlkoholRogzit.getText().toString();

                if (fozo.isEmpty() || gyumolcs.isEmpty() || alkohol.isEmpty()) {
                    Toast.makeText(AdatrogzitesActivity.this,
                            "Minden adatot meg kell adni", Toast.LENGTH_SHORT).show();
                } else {
                    int alkoholInt = Integer.parseInt(alkohol);
                    if (dbHelper.adatRogzites(fozo,gyumolcs,alkoholInt)){
                        Toast.makeText(AdatrogzitesActivity.this, "Sikeres adatfelvétel", Toast.LENGTH_SHORT).show();
                        editTextReset();
                    } else {
                        Toast.makeText(AdatrogzitesActivity.this, "Sikertelen adatfelvétel", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void editTextReset() {
        editTextAlkoholRogzit.setText(null);
        editTextGyumolcsRogzit.setText(null);
        editTextFozoRogzit.setText(null);
    }

    public void init() {
        buttonRogzit = findViewById(R.id.buttonRogzit);
        buttonRogzitVissza = findViewById(R.id.buttonRogzitVissza);
        editTextFozoRogzit = findViewById(R.id.editTextFozoRogzit);
        editTextGyumolcsRogzit = findViewById(R.id.editTextGyumolcsRogzit);
        editTextAlkoholRogzit = findViewById(R.id.editTextAlkoholRogzit);
        dbHelper = new DBHelper(AdatrogzitesActivity.this);
    }
}