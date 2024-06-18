package com.fernandopaiva.appfinal.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fernandopaiva.appfinal.R;
import com.fernandopaiva.appfinal.model.Feedback;

public class FeedbackActivity extends AppCompatActivity {
    private TextView txtTituloFeedback;
    private TextView txtTituloCardapio;
    private TextView txtDescricaoFeedback;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        txtTituloFeedback = findViewById(R.id.txtTituloFeedback);
        txtTituloCardapio = findViewById(R.id.txtTituloCardapio);
        txtDescricaoFeedback = findViewById(R.id.txtDescricaoFeedback);
        btnVoltar = findViewById(R.id.btnVoltar);

        // Obter o feedback passado pela intent
        Feedback feedback = (Feedback) getIntent().getSerializableExtra("feedback");

        if (feedback != null) {
            txtTituloFeedback.setText(feedback.getTitulo());
            txtTituloCardapio.setText(feedback.getTituloCardapio());
            txtDescricaoFeedback.setText(feedback.getDescricao());
        }

        btnVoltar.setOnClickListener(v -> finish());
    }
}
