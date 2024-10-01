package com.exemplo.calculadoraapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import br.uniceub.cc.pdm.fitcalcapp.R;

public class TelaPrincipal extends AppCompatActivity {

    //  ÁREA DE ATRIBUTOS

    // Atributos para Calculadora de IMC
    private Button botaoCalcularImc, botaoNavegarPesoIdeal, botaoNavegarAlturaIdeal;
    private EditText campoAlturaImc, campoPesoImc;
    private TextView textoResultadoImc;
    private RadioGroup grupoSexoImc;

    // Atributos para Calculadora de Peso Ideal
    private Button botaoCalcularPesoIdeal;
    private EditText campoAlturaPesoIdeal;
    private TextView textoResultadoPesoIdeal;
    private RadioGroup grupoSexoPesoIdeal;

    // Atributos para Calculadora de Altura Ideal (Meme)
    private Button botaoCalcularAlturaIdeal;
    private EditText campoPesoAlturaIdeal;
    private TextView textoResultadoAlturaIdeal;
    private RadioGroup grupoSexoAlturaIdeal;

    //  ÁREA DE MÉTODOS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exibirTelaInicial();
    }

    // Métodos para Navegação entre Telas

    public void exibirTelaInicial() {
        setContentView(R.layout.tela_inicial);

        botaoNavegarPesoIdeal = findViewById(R.id.botaoPesoIdeal);
        botaoNavegarAlturaIdeal = findViewById(R.id.botaoAlturaIdeal);
        Button botaoCalcularImc = findViewById(R.id.botaoIMC);

        botaoNavegarPesoIdeal.setOnClickListener(view -> exibirCalculadoraPesoIdeal());
        botaoNavegarAlturaIdeal.setOnClickListener(view -> exibirCalculadoraAlturaIdeal());
        botaoCalcularImc.setOnClickListener(view -> exibirCalculadoraIMC());
    }

    // Carrega a Calculadora de IMC
    public void exibirCalculadoraIMC() {
        setContentView(R.layout.calculadora_imc);

        botaoCalcularImc = findViewById(R.id.botaoCalcularImc);
        campoAlturaImc = findViewById(R.id.campoAlturaImc);
        campoPesoImc = findViewById(R.id.campoPesoImc);
        textoResultadoImc = findViewById(R.id.textoResultadoImc);
        grupoSexoImc = findViewById(R.id.grupoSexoImc);

        // Botão Voltar
        Button botaoVoltar = findViewById(R.id.botaoVoltarIMC);
        botaoVoltar.setOnClickListener(view -> exibirTelaInicial());

        botaoCalcularImc.setOnClickListener(view -> calcularImc());
    }

    // Carrega a Calculadora de Peso Ideal
    public void exibirCalculadoraPesoIdeal() {
        setContentView(R.layout.calculadora_peso_ideal);

        botaoCalcularPesoIdeal = findViewById(R.id.botaoCalcularPesoIdeal);
        campoAlturaPesoIdeal = findViewById(R.id.campoAlturaPesoIdeal);
        textoResultadoPesoIdeal = findViewById(R.id.textoResultadoPesoIdeal);
        grupoSexoPesoIdeal = findViewById(R.id.grupoSexoPesoIdeal);

        // Botão Voltar
        Button botaoVoltar = findViewById(R.id.botaoVoltarPesoIdeal);
        botaoVoltar.setOnClickListener(view -> exibirTelaInicial());

        botaoCalcularPesoIdeal.setOnClickListener(view -> calcularPesoIdeal());
    }

    // Carrega a Calculadora de Altura Ideal (Meme)
    public void exibirCalculadoraAlturaIdeal() {
        setContentView(R.layout.calculadora_altura_ideal);

        botaoCalcularAlturaIdeal = findViewById(R.id.botaoCalcularAlturaIdeal);
        campoPesoAlturaIdeal = findViewById(R.id.campoPesoAlturaIdeal);
        textoResultadoAlturaIdeal = findViewById(R.id.textoResultadoAlturaIdeal);
        grupoSexoAlturaIdeal = findViewById(R.id.grupoSexoAlturaIdeal);

        // Botão Voltar
        Button botaoVoltar = findViewById(R.id.botaoVoltarAlturaIdeal);
        botaoVoltar.setOnClickListener(view -> exibirTelaInicial());

        botaoCalcularAlturaIdeal.setOnClickListener(view -> calcularAlturaIdeal());
    }

    //  Métodos de Cálculo

    // Método para calcular o IMC
    public void calcularImc() {
        try {
            float altura = Float.parseFloat(campoAlturaImc.getText().toString());
            float peso = Float.parseFloat(campoPesoImc.getText().toString());
            int sexoId = grupoSexoImc.getCheckedRadioButtonId();
            boolean isMasculino = sexoId == R.id.radioMasculinoImc;

            float imc = peso / (altura * altura);
            String resultado;

            if (isMasculino) {
                if (imc < 18.5) resultado = "Abaixo do peso";
                else if (imc < 24.9) resultado = "Peso normal";
                else if (imc < 29.9) resultado = "Pré-obesidade";
                else if (imc < 34.9) resultado = "Obesidade Grau 1";
                else if (imc < 39.9) resultado = "Obesidade Grau 2";
                else resultado = "Obesidade Grau 3";
            } else {
                if (imc < 18.5) resultado = "Abaixo do peso";
                else if (imc < 26.9) resultado = "Peso normal";
                else if (imc < 32.9) resultado = "Pré-obesidade";
                else if (imc < 37.9) resultado = "Obesidade Grau 1";
                else if (imc < 44.9) resultado = "Obesidade Grau 2";
                else resultado = "Obesidade Grau 3";
            }

            textoResultadoImc.setText("IMC: " + String.format("%.2f", imc) + " - " + resultado);
        } catch (NumberFormatException e) {
            textoResultadoImc.setText("Por favor, insira valores válidos.");
        }
    }

    // Método para calcular o Peso Ideal
    public void calcularPesoIdeal() {
        try {
            float altura = Float.parseFloat(campoAlturaPesoIdeal.getText().toString());
            int sexoId = grupoSexoPesoIdeal.getCheckedRadioButtonId();
            float imcIdeal = (sexoId == R.id.radioMasculinoPesoIdeal) ? 21.7f : 22.7f;

            float pesoIdeal = imcIdeal * (altura * altura);
            textoResultadoPesoIdeal.setText("Peso Ideal: " + String.format("%.2f", pesoIdeal) + " kg");
        } catch (NumberFormatException e) {
            textoResultadoPesoIdeal.setText("Por favor, insira valores válidos.");
        }
    }

    // Método para calcular a Altura Ideal (Meme)
    public void calcularAlturaIdeal() {
        try {
            float peso = Float.parseFloat(campoPesoAlturaIdeal.getText().toString());
            int sexoId = grupoSexoAlturaIdeal.getCheckedRadioButtonId();
            float imcIdeal = (sexoId == R.id.radioMasculinoAlturaIdeal) ? 21.7f : 22.7f;

            float alturaIdeal = (float) Math.sqrt(peso / imcIdeal);
            textoResultadoAlturaIdeal.setText("Altura Ideal: " + String.format("%.2f", alturaIdeal) + " m");
        } catch (NumberFormatException e) {
            textoResultadoAlturaIdeal.setText("Por favor, insira valores válidos.");
        }
    }
}
