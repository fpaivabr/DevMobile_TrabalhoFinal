package com.fernandopaiva.appfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.fernandopaiva.appfinal.R;
import com.fernandopaiva.appfinal.model.Atividade;
import com.fernandopaiva.appfinal.service.ApiService;
import com.fernandopaiva.appfinal.service.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AfazeresActivity extends AppCompatActivity {
    private TextView txtTituloAtividade;
    private TextView txtFeedbackRelacionado;
    private TextView txtDescricaoAtividade;
    private EditText edtAcao;
    private Button btnVoltar;
    private Button btnSalvarAcao;
    private Atividade atividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afazeres);

        txtTituloAtividade = findViewById(R.id.txtTituloAtividade);
        txtFeedbackRelacionado = findViewById(R.id.txtFeedbackRelacionado);
        txtDescricaoAtividade = findViewById(R.id.txtDescricaoAtividade);
        edtAcao = findViewById(R.id.edtAcao);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnSalvarAcao = findViewById(R.id.btnSalvarAcao);

        setupBottomNavigation();

        atividade = (Atividade) getIntent().getSerializableExtra("atividade");

        if (atividade != null) {
            txtTituloAtividade.setText(atividade.getTituloAtividade());
            txtFeedbackRelacionado.setText(atividade.getTituloFeedback());
            txtDescricaoAtividade.setText(atividade.getDescricaoAtividade());
        }

        btnVoltar.setOnClickListener(v -> finish());

        btnSalvarAcao.setOnClickListener(v -> {
            String acao = edtAcao.getText().toString();
            salvarAcao(acao);
        });
    }

    private void setupBottomNavigation() {
        findViewById(R.id.btnTodosFeedbacks).setOnClickListener(v -> startActivity(new Intent(this, ListaFeedbackActivity.class)));
        findViewById(R.id.btnFeedbacksPendentes).setOnClickListener(v -> startActivity(new Intent(this, ListaFeedbackPendenteActivity.class)));
        findViewById(R.id.btnListaAfazeres).setOnClickListener(v -> startActivity(new Intent(this, ListaAfazeresActivity.class)));
        findViewById(R.id.btnConfiguracaoCardapio).setOnClickListener(v -> startActivity(new Intent(this, ListaConfiguracaoCardapioActivity.class)));
        findViewById(R.id.btnHome).setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }

    private void salvarAcao(String acao) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        atividade.setAcao(acao);
        Call<Atividade> call = apiService.updateAtividade(String.valueOf(atividade.getId()), atividade);

        call.enqueue(new Callback<Atividade>() {
            @Override
            public void onResponse(Call<Atividade> call, Response<Atividade> response) {
                if (response.isSuccessful()) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Atividade> call, Throwable t) {
                // Tratar falha na chamada
            }
        });
    }
}
