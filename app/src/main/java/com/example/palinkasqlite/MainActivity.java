package com.example.palinkasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonKeresesre;
    private Button buttonFelvetelre;
    private Button buttonListazas;
    private Intent intent;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> entryList;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        buttonKeresesre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, KeresesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonFelvetelre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, AdatrogzitesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonListazas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor adatok = dbHelper.adatLekerdezes();
                if (adatok.getCount() == 0) {
                    Toast.makeText(MainActivity.this,
                            "Nincs az adatbázisban bejegyzés",
                            Toast.LENGTH_SHORT).show();
                } else {
                    entryList.clear();
                    while (adatok.moveToNext()) {
                        String entry = "ID: " + adatok.getInt(0) + "\n" +
                                "Főző: " + adatok.getString(1) + "\n" +
                                "Gyümölcs: " + adatok.getString(2) + "\n" +
                                "Alkohol: " + adatok.getInt(3) + "\n\n";
                        entryList.add(entry);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void init() {
        buttonKeresesre = findViewById(R.id.buttonKeresesre);
        buttonFelvetelre = findViewById(R.id.buttonFelvetelre);
        buttonListazas = findViewById(R.id.buttonListazas);
        listView = findViewById(R.id.listView);
        dbHelper = new DBHelper(MainActivity.this);
        entryList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, entryList);
        listView.setAdapter(adapter);
    }
}