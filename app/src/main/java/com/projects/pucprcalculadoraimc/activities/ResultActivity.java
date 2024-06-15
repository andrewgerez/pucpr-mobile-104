package com.projects.pucprcalculadoraimc.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.projects.pucprcalculadoraimc.R;
import com.projects.pucprcalculadoraimc.services.WeightChecker;
import com.projects.pucprcalculadoraimc.services.ImcInfo;

public class ResultActivity extends AppCompatActivity {

    TextView imcTextView;
    TextView explanationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        float height = extras.getFloat("height");
        float imc = extras.getFloat("imc");

        imcTextView = findViewById(R.id.imcTextView);
        explanationTextView = findViewById(R.id.explanationTextView);

        float minimumWeight = WeightChecker.minimum(height);
        float maximumWeight = WeightChecker.maximum(height);
        imcChecker(imc, minimumWeight, maximumWeight);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void imcChecker(float imc, float minimumWeight, float maximumWeight) {

        imcTextView.setText(String.format("O resultado do seu IMC é: %.2f", imc));

        ImcInfo[] imcInfos = new ImcInfo[] {
            new ImcInfo(18.5, "MAGREZA: Segundo a OMS, seu IMC está abaixo do recomendado de acordo com a sua altura. IMC normal: entre %.2f e %.2f."),
            new ImcInfo(24.9, "NORMAL: Segundo a OMS, seu IMC é considerado normal de acordo com a sua altura. IMC normal: entre %.2f e %.2f kg."),
            new ImcInfo(29.9, "SOBREPESO: Segundo a OMS, seu IMC está um pouco acima do recomendado de acordo com a sua altura. IMC normal: entre %.2f e %.2f kg."),
            new ImcInfo(34.9, "OBESIDADE GRAU I: Segundo a OMS, seu IMC está acima do recomendado de acordo com a sua altura. IMC normal: entre %.2f e %.2f."),
            new ImcInfo(39.9, "OBESIDADE GRAU II: Segundo a OMS, seu IMC está bem acima do recomendado de acordo com a sua altura. IMC normal: entre %.2f e %.2f."),
            new ImcInfo(Double.MAX_VALUE, "OBESIDADE GRAU III: Segundo a OMS, seu IMC está extremamente acima do recomendado de acordo com a sua altura. IMC normal: entre %.2f e %.2f.")
        };

        for (ImcInfo imcInfo : imcInfos) {
            if (imc <= imcInfo.upperBound) {
                explanationTextView.setText(String.format(imcInfo.message, minimumWeight, maximumWeight));
                break;
            }
        }
    }

    public void backOnClick(View v) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
