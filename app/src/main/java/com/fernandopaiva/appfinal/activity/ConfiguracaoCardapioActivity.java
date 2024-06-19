package com.fernandopaiva.appfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.fernandopaiva.appfinal.R;
import com.fernandopaiva.appfinal.model.ItemCardapio;
import com.fernandopaiva.appfinal.service.ApiService;
import com.fernandopaiva.appfinal.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfiguracaoCardapioActivity extends AppCompatActivity {
    private EditText edtTituloPrato, edtDescricaoPrato, filtrarPratosEditText;
    private Spinner spinnerTipoPrato;
    private Button btnAdicionarPrato;
    private ListView listaPratos;
    private ArrayAdapter<ItemCardapio> adapter;
    private List<ItemCardapio> itemCardapioList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_configuracao_cardapio);

        edtTituloPrato = findViewById(R.id.edtTituloPrato);
        edtDescricaoPrato = findViewById(R.id.edtDescricaoPrato);
        spinnerTipoPrato = findViewById(R.id.spinnerTipoPrato);
        btnAdicionarPrato = findViewById(R.id.btnAdicionarPrato);
        filtrarPratosEditText = findViewById(R.id.filtrarPratosEditText);
        listaPratos = findViewById(R.id.listaPratos);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemCardapioList);
        listaPratos.setAdapter(adapter);

        setupBottomNavigation();

        btnAdicionarPrato.setOnClickListener(v -> {
            String titulo = edtTituloPrato.getText().toString();
            String descricao = edtDescricaoPrato.getText().toString();
            String tipo = spinnerTipoPrato.getSelectedItem().toString();
            ItemCardapio novoItem = new ItemCardapio(0, titulo, descricao, tipo);
            adicionarItemCardapio(novoItem);
        });

        filtrarPratosEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarPratos(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        loadItemCardapio();
    }

    private void setupBottomNavigation() {
        findViewById(R.id.btnTodosFeedbacks).setOnClickListener(v -> startActivity(new Intent(this, ListaFeedbackActivity.class)));
        findViewById(R.id.btnFeedbacksPendentes).setOnClickListener(v -> startActivity(new Intent(this, ListaFeedbackPendenteActivity.class)));
        findViewById(R.id.btnListaAfazeres).setOnClickListener(v -> startActivity(new Intent(this, ListaAfazeresActivity.class)));
        findViewById(R.id.btnConfiguracaoCardapio).setOnClickListener(v -> startActivity(new Intent(this, ListaConfiguracaoCardapioActivity.class)));
        findViewById(R.id.btnHome).setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }

    private void adicionarItemCardapio(ItemCardapio novoItem) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<ItemCardapio> call = apiService.createItemCardapio(novoItem);

        call.enqueue(new Callback<ItemCardapio>() {
            @Override
            public void onResponse(Call<ItemCardapio> call, Response<ItemCardapio> response) {
                if (response.isSuccessful()) {
                    itemCardapioList.add(response.body());
                    adapter.notifyDataSetChanged();
                    edtTituloPrato.setText("");
                    edtDescricaoPrato.setText("");
                    spinnerTipoPrato.setSelection(0);
                }
            }

            @Override
            public void onFailure(Call<ItemCardapio> call, Throwable t) {
                // Tratar falha na chamada
            }
        });
    }

    private void loadItemCardapio() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<ItemCardapio>> call = apiService.getItemCardapio();

        call.enqueue(new Callback<List<ItemCardapio>>() {
            @Override
            public void onResponse(Call<List<ItemCardapio>> call, Response<List<ItemCardapio>> response) {
                if (response.isSuccessful()) {
                    itemCardapioList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<ItemCardapio>> call, Throwable t) {
                // Tratar falha na chamada
            }
        });
    }

    private void filtrarPratos(String query) {
        List<ItemCardapio> filteredList = new ArrayList<>();
        for (ItemCardapio item : itemCardapioList) {
            if (item.getTitulo().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.clear();
        adapter.addAll(filteredList);
        adapter.notifyDataSetChanged();
    }
}
