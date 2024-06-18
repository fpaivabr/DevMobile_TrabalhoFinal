package com.fernandopaiva.appfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fernandopaiva.appfinal.R;
import com.fernandopaiva.appfinal.model.Feedback;

import java.util.List;

public class FeedbackAdapter extends BaseAdapter {
    private Context context;
    private List<Feedback> feedbacks;

    public FeedbackAdapter(Context context, List<Feedback> feedbacks) {
        this.context = context;
        this.feedbacks = feedbacks;
    }

    @Override
    public int getCount() {
        return feedbacks.size();
    }

    @Override
    public Object getItem(int position) {
        return feedbacks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Feedback feedback = feedbacks.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_feedback, parent, false);
        }

        TextView tituloFeedback = convertView.findViewById(R.id.txtTituloFeedback);
        TextView tituloCardapio = convertView.findViewById(R.id.txtTituloCardapio);
        TextView descricaoFeedback = convertView.findViewById(R.id.txtDescricaoFeedback);

        tituloFeedback.setText(feedback.getTitulo());
        tituloCardapio.setText(feedback.getTituloCardapio());
        descricaoFeedback.setText(feedback.getDescricao());

        return convertView;
    }
}
