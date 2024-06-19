package com.fernandopaiva.appfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fernandopaiva.appfinal.R;
import com.fernandopaiva.appfinal.model.Atividade;
import com.fernandopaiva.appfinal.model.Feedback;
import com.fernandopaiva.appfinal.service.ApiService;
import com.fernandopaiva.appfinal.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackPendenteActivity extends AppCompatActivity {
    private TextView txtTituloFeedback;
    private TextView txtTituloCardapio;
    private TextView txtDescricaoFeedback;
    private EditText edtResposta;
    private RadioGroup rgInserirAtividade;
    private RadioButton rbSim;
    private RadioButton rbNao;
    private EditText edtTituloAtividade;
    private EditText edtDescricaoAtividade;
    private Button btnVoltar;
    private Button btnEnviarResposta;

    private Feedback feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_pendente);

        txtTituloFeedback = findViewById(R.id.txtTituloFeedback);
        txtTituloCardapio = findViewById(R.id.txtTituloCardapio);
        txtDescricaoFeedback = findViewById(R.id.txtDescricaoFeedback);
        edtResposta = findViewById(R.id.edtResposta);
        rgInserirAtividade = findViewById(R.id.rgInserirAtividade);
        rbSim = findViewById(R.id.rbSim);
        rbNao = findViewById(R.id.rbNao);
        edtTituloAtividade = findViewById(R.id.edtTituloAtividade);
        edtDescricaoAtividade = findViewById(R.id.edtDescricaoAtividade);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnEnviarResposta = findViewById(R.id.btnEnviarResposta);

        feedback = (Feedback) getIntent().getSerializableExtra("feedback");

        if (feedback != null) {
            txtTituloFeedback.setText(feedback.getTitulo());
            txtTituloCardapio.setText(feedback.getTituloCardapio());
            txtDescricaoFeedback.setText(feedback.getDescricao());
        }

        btnVoltar.setOnClickListener(v -> finish());

        btnEnviarResposta.setOnClickListener(v -> {
            String resposta = edtResposta.getText().toString();
            boolean criarAtividade = rbSim.isChecked();
            if (criarAtividade) {
                String tituloAtividade = edtTituloAtividade.getText().toString();
                String descricaoAtividade = edtDescricaoAtividade.getText().toString();
                criarAtividade(resposta, tituloAtividade, descricaoAtividade);
            } else {
                enviarResposta(resposta);
            }
        });
    }

    private void enviarResposta(String resposta) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        feedback.setResposta(resposta);
        feedback.setStatus("resolvido");
        Call<Feedback> call = apiService.updateFeedback(String.valueOf(feedback.getId()), feedback);

        call.enqueue(new Callback<Feedback>() {
            @Override
            public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                if (response.isSuccessful()) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Feedback> call, Throwable t) {
                // Tratar falha na chamada
            }
        });
    }

    private void criarAtividade(String resposta, String tituloAtividade, String descricaoAtividade) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        Atividade atividade = new Atividade();
        atividade.setTituloAtividade(tituloAtividade);
        atividade.setDescricaoAtividade(descricaoAtividade);
        atividade.setFeedbackId(feedback.getId());

        Call<Atividade> callAtividade = apiService.createAtividade(atividade);
        callAtividade.enqueue(new Callback<Atividade>() {
            @Override
            public void onResponse(Call<Atividade> call, Response<Atividade> response) {
                if (response.isSuccessful()) {
                    enviarResposta(resposta);
                }
            }

            @Override
            public void onFailure(Call<Atividade> call, Throwable t) {
                // Tratar falha na chamada
            }
        });
    }
    private void setupBottomNavigation() {
        findViewById(R.id.btnTodosFeedbacks).setOnClickListener(v -> startActivity(new Intent(this, ListaFeedbackActivity.class)));
        findViewById(R.id.btnFeedbacksPendentes).setOnClickListener(v -> startActivity(new Intent(this, ListaFeedbackPendenteActivity.class)));
        findViewById(R.id.btnListaAfazeres).setOnClickListener(v -> startActivity(new Intent(this, ListaAfazeresActivity.class)));
        findViewById(R.id.btnConfiguracaoCardapio).setOnClickListener(v -> startActivity(new Intent(this, ListaConfiguracaoCardapioActivity.class)));
        findViewById(R.id.btnHome).setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }
}
