package com.fernandopaiva.appfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fernandopaiva.appfinal.R;

public class LoginActivity extends AppCompatActivity {
    private EditText edtLogin;
    private EditText edtSenha;
    private Button btnEntrar;
    private TextView txtNaoPossuiCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLogin = findViewById(R.id.edtLogin);
        edtSenha = findViewById(R.id.edtSenha);
        btnEntrar = findViewById(R.id.btnEntrar);
        txtNaoPossuiCadastro = findViewById(R.id.txtNaoPossuiCadastro);

        btnEntrar.setOnClickListener(v -> {
            String login = edtLogin.getText().toString();
            String senha = edtSenha.getText().toString();

            if (login.equals("usuarioteste") && senha.equals("teste123")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                // Lógica para autenticar o usuário no banco de dados (se necessário)
                Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        });

        txtNaoPossuiCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(intent);
        });
    }
}
