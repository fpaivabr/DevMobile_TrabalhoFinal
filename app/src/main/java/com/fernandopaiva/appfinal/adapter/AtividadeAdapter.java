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
import com.fernandopaiva.appfinal.activity.AfazeresActivity;
import com.fernandopaiva.appfinal.model.Atividade;
import java.util.List;

public class AtividadeAdapter extends BaseAdapter {
    private final List<Atividade> atividades;
    private final Context context;

    public AtividadeAdapter(Context context, List<Atividade> atividades) {
        this.context = context;
        this.atividades = atividades;
    }

    @Override
    public int getCount() {
        return atividades.size();
    }

    @Override
    public Object getItem(int position) {
        return atividades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_afazeres, parent, false);
            holder = new ViewHolder();
            holder.txtTituloAtividade = convertView.findViewById(R.id.txtTituloAtividade);
            holder.txtTituloFeedback = convertView.findViewById(R.id.txtTituloCardapio);
            holder.txtDescricaoAtividade = convertView.findViewById(R.id.txtDescricaoAtividade);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Atividade atividade = atividades.get(position);
        holder.txtTituloAtividade.setText(atividade.getTituloAtividade());
        holder.txtTituloFeedback.setText(atividade.getTituloFeedback());
        holder.txtDescricaoAtividade.setText(atividade.getDescricaoAtividade());

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AfazeresActivity.class);
            intent.putExtra("atividade", atividade);
            ContextCompat.startActivity(context, intent, null);
        });

        return convertView;
    }

    static class ViewHolder {
        TextView txtTituloAtividade;
        TextView txtTituloFeedback;
        TextView txtDescricaoAtividade;
    }

    public void updateList(List<Atividade> newList) {
        atividades.clear();
        atividades.addAll(newList);
        notifyDataSetChanged();
    }
}
