package com.fernandopaiva.appfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.fernandopaiva.appfinal.R;
import com.fernandopaiva.appfinal.adapter.FeedbackAdapter;
import com.fernandopaiva.appfinal.model.Feedback;
import com.fernandopaiva.appfinal.service.ApiService;
import com.fernandopaiva.appfinal.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaFeedbackActivity extends AppCompatActivity {
    private ListView feedbacksListView;
    private ImageButton btnTodosFeedbacksNav;
    private ImageButton btnFeedbacksPendentesNav;
    private ImageButton btnListaAfazeresNav;
    private ImageButton btnConfiguracaoCardapioNav;
    private ImageButton btnHomeNav;
    private RadioGroup statusRadioGroup;
    private RadioButton todosFeedbacksRadioButton;
    private RadioButton pendentesFeedbacksRadioButton;
    private RadioButton resolvidosFeedbacksRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_feedback);

        feedbacksListView = findViewById(R.id.feedbacksListView);
        statusRadioGroup = findViewById(R.id.statusRadioGroup);
        todosFeedbacksRadioButton = findViewById(R.id.todosFeedbacksRadioButton);
        pendentesFeedbacksRadioButton = findViewById(R.id.pendentesFeedbacksRadioButton);
        resolvidosFeedbacksRadioButton = findViewById(R.id.resolvidosFeedbacksRadioButton);

        // Inicializar os botões de navegação
        btnTodosFeedbacksNav = findViewById(R.id.btnTodosFeedbacks);
        btnFeedbacksPendentesNav = findViewById(R.id.btnFeedbacksPendentes);
        btnListaAfazeresNav = findViewById(R.id.btnListaAfazeres);
        btnConfiguracaoCardapioNav = findViewById(R.id.btnConfiguracaoCardapio);
        btnHomeNav = findViewById(R.id.btnHome);

        // Configurar listeners para navegação
        btnTodosFeedbacksNav.setOnClickListener(v -> {
            Intent intent = new Intent(ListaFeedbackActivity.this, ListaFeedbackActivity.class);
            startActivity(intent);
        });

        btnFeedbacksPendentesNav.setOnClickListener(v -> {
            Intent intent = new Intent(ListaFeedbackActivity.this, ListaFeedbackPendenteActivity.class);
            startActivity(intent);
        });

        btnListaAfazeresNav.setOnClickListener(v -> {
            Intent intent = new Intent(ListaFeedbackActivity.this, ListaAfazeresActivity.class);
            startActivity(intent);
        });

        btnConfiguracaoCardapioNav.setOnClickListener(v -> {
            Intent intent = new Intent(ListaFeedbackActivity.this, ListaConfiguracaoCardapioActivity.class);
            startActivity(intent);
        });

        btnHomeNav.setOnClickListener(v -> {
            Intent intent = new Intent(ListaFeedbackActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Carregar feedbacks
        loadFeedbacks(null);

        // Configurar filtro de status
        statusRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String status = null;
            if (checkedId == R.id.pendentesFeedbacksRadioButton) {
                status = "pendente";
            } else if (checkedId == R.id.resolvidosFeedbacksRadioButton) {
                status = "resolvido";
            }
            loadFeedbacks(status);
        });
    }

    private void loadFeedbacks(String status) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Feedback>> call = apiService.getFeedbacks(status);

        call.enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FeedbackAdapter adapter = new FeedbackAdapter(ListaFeedbackActivity.this, response.body());
                    feedbacksListView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Feedback>> call, Throwable t) {
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
