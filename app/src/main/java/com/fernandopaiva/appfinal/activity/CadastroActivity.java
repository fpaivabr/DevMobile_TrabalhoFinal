package com.fernandopaiva.appfinal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.fernandopaiva.appfinal.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtNomeUsuario;
    private EditText edtSobrenomeUsuario;
    private EditText edtDescricaoUsuario;
    private EditText edtSenhaUsuario;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNomeUsuario = findViewById(R.id.edtNomeUsuario);
        edtSobrenomeUsuario = findViewById(R.id.edtSobrenomeUsuario);
        edtDescricaoUsuario = findViewById(R.id.edtDescricaoUsuario);
        edtSenhaUsuario = findViewById(R.id.edtSenhaUsuario);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNomeUsuario.getText().toString();
                String sobrenome = edtSobrenomeUsuario.getText().toString();
                String descricao = edtDescricaoUsuario.getText().toString();
                String senha = edtSenhaUsuario.getText().toString();
                // Aqui você pode implementar a lógica para salvar o usuário no banco de dados

                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

