package com.fernandopaiva.appfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import com.fernandopaiva.appfinal.R;
import com.fernandopaiva.appfinal.adapter.FeedbackPendenteAdapter;
import com.fernandopaiva.appfinal.model.Feedback;
import com.fernandopaiva.appfinal.service.ApiService;
import com.fernandopaiva.appfinal.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaFeedbackPendenteActivity extends AppCompatActivity {
    private ListView feedbacksPendentesListView;
    private EditText filtrarFeedbacksEditText;
    private FeedbackPendenteAdapter adapter;
    private List<Feedback> feedbackPendenteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_feedback_pendente);

        feedbacksPendentesListView = findViewById(R.id.feedbacksPendentesListView);
        filtrarFeedbacksEditText = findViewById(R.id.filtrarFeedbacksEditText);

        loadFeedbacks("pendente");

        filtrarFeedbacksEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterFeedbacks(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        setupBottomNavigation();
    }

    private void loadFeedbacks(String status) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Feedback>> call = apiService.getFeedbacks(status);

        call.enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {
                if (response.isSuccessful()) {
                    feedbackPendenteList = response.body();
                    adapter = new FeedbackPendenteAdapter(ListaFeedbackPendenteActivity.this, feedbackPendenteList);
                    feedbacksPendentesListView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Feedback>> call, Throwable t) {
                // Tratar falha na chamada
            }
        });
    }

    private void filterFeedbacks(String query) {
        List<Feedback> filteredList = new ArrayList<>();
        for (Feedback feedback : feedbackPendenteList) {
            if (feedback.getTitulo().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(feedback);
            }
        }
        adapter.updateList(filteredList);
    }

    private void setupBottomNavigation() {
        findViewById(R.id.btnTodosFeedbacks).setOnClickListener(v -> startActivity(new Intent(this, ListaFeedbackActivity.class)));
        findViewById(R.id.btnFeedbacksPendentes).setOnClickListener(v -> startActivity(new Intent(this, ListaFeedbackPendenteActivity.class)));
        findViewById(R.id.btnListaAfazeres).setOnClickListener(v -> startActivity(new Intent(this, ListaAfazeresActivity.class)));
        findViewById(R.id.btnConfiguracaoCardapio).setOnClickListener(v -> startActivity(new Intent(this, ListaConfiguracaoCardapioActivity.class)));
        findViewById(R.id.btnHome).setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }
}
