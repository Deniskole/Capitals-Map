package com.example.capitals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFind;
    private EditText editTextCountry;
    private EditText editTextCapital;
    private TextView textViewCapital;
    private ListView listView;

    private String country, capital;
    static int counter = 0;

    private Map<String, String> capitals = new TreeMap<>();
    String temp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFind = findViewById(R.id.editTextFind);
        editTextCountry = findViewById(R.id.editTextCountry);
        editTextCapital = findViewById(R.id.editTextCapital);
        textViewCapital = findViewById(R.id.textViewCapital);
        listView = findViewById(R.id.list);
        Change();

    }


    public void Change() {
        editTextFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String countryStr = editTextFind.getText().toString();


                if (capitals.get(countryStr) != null) {
                    textViewCapital.setText("Столица - " + capitals.get(countryStr));
                } else {
                    textViewCapital.setText("Столица:");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void addCapital(View view) {

        country = editTextCountry.getText().toString();
        capital = editTextCapital.getText().toString();

        if (!country.isEmpty() && !capital.isEmpty()) {
            capitals.put(country, capital);
            String countryAndCapital = country + " - " + capital;
            temp += countryAndCapital + ",";
            String array[] = temp.split(",");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
            listView.setAdapter(adapter);
            editTextCountry.setText("");
            editTextCapital.setText("");
        } else {
            Toast.makeText(this, "Пусто!", Toast.LENGTH_SHORT).show();
        }

    }
}
