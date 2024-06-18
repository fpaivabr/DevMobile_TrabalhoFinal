package com.fernandopaiva.appfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fernandopaiva.appfinal.R;

public class MainActivity extends AppCompatActivity {
    private TextView txtBemVindo;
    private TextView txtSelecioneOpcao;
    private Button btnTodosFeedbacks;
    private Button btnFeedbacksPendentes;
    private Button btnListaAfazeres;
    private Button btnConfiguracaoCardapio;
    private TextView txtSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBemVindo = findViewById(R.id.txtBemVindo);
        txtSelecioneOpcao = findViewById(R.id.txtSelecioneOpcao);
        btnTodosFeedbacks = findViewById(R.id.btnTodosFeedbacks);
        btnFeedbacksPendentes = findViewById(R.id.btnFeedbacksPendentes);
        btnListaAfazeres = findViewById(R.id.btnListaAfazeres);
        btnConfiguracaoCardapio = findViewById(R.id.btnConfiguracaoCardapio);
        txtSair = findViewById(R.id.txtSair);

        btnTodosFeedbacks.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaFeedbackActivity.class);
            startActivity(intent);
        });

        btnFeedbacksPendentes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaFeedbackPendenteActivity.class);
            startActivity(intent);
        });

        btnListaAfazeres.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaAfazeresActivity.class);
            startActivity(intent);
        });

        btnConfiguracaoCardapio.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaConfiguracaoCardapioActivity.class);
            startActivity(intent);
        });

        txtSair.setOnClickListener(v -> {
            // Lógica para sair
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Fecha a MainActivity
        });
    }
}
