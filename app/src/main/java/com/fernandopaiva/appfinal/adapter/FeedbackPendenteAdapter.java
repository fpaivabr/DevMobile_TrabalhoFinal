package com.fernandopaiva.appfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.fernandopaiva.appfinal.R;
import com.fernandopaiva.appfinal.activity.FeedbackPendenteActivity;
import com.fernandopaiva.appfinal.model.Feedback;

import java.util.List;

public class FeedbackPendenteAdapter extends BaseAdapter {
    private List<Feedback> feedbacks;
    private final Context context;

    public FeedbackPendenteAdapter(Context context, List<Feedback> feedbacks) {
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_feedback_pendente, parent, false);
            holder = new ViewHolder();
            holder.txtTituloFeedback = convertView.findViewById(R.id.txtTituloFeedback);
            holder.txtTituloCardapio = convertView.findViewById(R.id.txtTituloCardapio);
            holder.txtDescricaoFeedback = convertView.findViewById(R.id.txtDescricaoFeedback);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Feedback feedback = feedbacks.get(position);
        holder.txtTituloFeedback.setText(feedback.getTitulo());
        holder.txtTituloCardapio.setText(feedback.getTituloCardapio());
        holder.txtDescricaoFeedback.setText(feedback.getDescricao());

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FeedbackPendenteActivity.class);
            intent.putExtra("feedback", feedback);
            ContextCompat.startActivity(context, intent, null);
        });

        return convertView;
    }

    static class ViewHolder {
        TextView txtTituloFeedback;
        TextView txtTituloCardapio;
        TextView txtDescricaoFeedback;
    }

    public void updateList(List<Feedback> newFeedbacks) {
        this.feedbacks = newFeedbacks;
        notifyDataSetChanged();
    }
}
