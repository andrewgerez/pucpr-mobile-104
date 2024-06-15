package com.projects.pucprcalculadoraimc.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.projects.pucprcalculadoraimc.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleCalculateIMC(View v) {
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText heightEditText = findViewById(R.id.heightEditNumber);
        EditText weightEditText = findViewById(R.id.weightEditNumber);

        String name = nameEditText.getText().toString();
        float height;
        float weight;
        float imc;

        if (heightEditText.getText().toString().equals("")) {
            height = 0;
        } else {
            height = Float.parseFloat(heightEditText.getText().toString()) / 100;
        }

        if (weightEditText.getText().toString().equals("")) {
            weight = 0;
        } else {
            weight = Float.parseFloat(weightEditText.getText().toString());
        }

        if (weight <= 0 || height <= 0) {
            Toast.makeText(
                    MainActivity.this,
                    getString(R.string.imc_zero),
                    Toast.LENGTH_LONG).show();
        } else {
            imc = weight / (height * height);

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putFloat("height", height);
            bundle.putFloat("imc", imc);

            intent.putExtras(bundle);
            startActivity(intent);

            nameEditText.setText("");
            heightEditText.setText("");
            weightEditText.setText("");
        }

    }
}
