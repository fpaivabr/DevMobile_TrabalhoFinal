package com.fernandopaiva.appfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.fernandopaiva.appfinal.R;
import com.fernandopaiva.appfinal.model.Usuario;
import com.fernandopaiva.appfinal.service.ApiService;
import com.fernandopaiva.appfinal.service.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtLoginUsuario, edtNomeUsuario, edtSobrenomeUsuario, edtDescricaoUsuario, edtSenhaUsuario;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtLoginUsuario = findViewById(R.id.edtLoginUsuario);
        edtNomeUsuario = findViewById(R.id.edtNomeUsuario);
        edtSobrenomeUsuario = findViewById(R.id.edtSobrenomeUsuario);
        edtDescricaoUsuario = findViewById(R.id.edtDescricaoUsuario);
        edtSenhaUsuario = findViewById(R.id.edtSenhaUsuario);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario() {
        String login = edtLoginUsuario.getText().toString();
        String nome = edtNomeUsuario.getText().toString();
        String sobrenome = edtSobrenomeUsuario.getText().toString();
        String descricao = edtDescricaoUsuario.getText().toString();
        String senha = edtSenhaUsuario.getText().toString();

        Usuario usuario = new Usuario(login, nome, sobrenome, descricao, senha);

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Usuario> call = apiService.createUsuario(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CadastroActivity.this, "Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(CadastroActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
