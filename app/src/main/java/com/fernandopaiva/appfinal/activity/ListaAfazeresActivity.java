package com.fernandopaiva.appfinal.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import com.fernandopaiva.appfinal.R;
import com.fernandopaiva.appfinal.adapter.AtividadeAdapter;
import com.fernandopaiva.appfinal.model.Atividade;
import com.fernandopaiva.appfinal.service.ApiService;
import com.fernandopaiva.appfinal.service.RetrofitClient;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaAfazeresActivity extends AppCompatActivity {
    private ListView atividadesListView;
    private EditText filtrarAtividadesEditText;
    private AtividadeAdapter adapter;
    private List<Atividade> atividadeList;
    private RadioGroup statusRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_afazeres);

        atividadesListView = findViewById(R.id.atividadesListView);
        filtrarAtividadesEditText = findViewById(R.id.filtrarAtividadesEditText);
        statusRadioGroup = findViewById(R.id.statusRadioGroup);

        loadAtividades("todos");

        filtrarAtividadesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterAtividades(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Configurar filtro de status
        statusRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String status = "todos";
            if (checkedId == R.id.pendentesAtividadesRadioButton) {
                status = "pendentes";
            } else if (checkedId == R.id.concluidasAtividadesRadioButton) {
                status = "concluidas";
            }
            loadAtividades(status);
        });
    }

    private void loadAtividades(String status) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Atividade>> call = apiService.getAtividades(status);

        call.enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                if (response.isSuccessful()) {
                    atividadeList = response.body();
                    adapter = new AtividadeAdapter(ListaAfazeresActivity.this, atividadeList);
                    atividadesListView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {
                // Tratar falha na chamada
            }
        });
    }

    private void filterAtividades(String query) {
        List<Atividade> filteredList = new ArrayList<>();
        for (Atividade atividade : atividadeList) {
            if (atividade.getTituloAtividade().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(atividade);
            }
        }
        adapter.updateList(filteredList);
    }
}
