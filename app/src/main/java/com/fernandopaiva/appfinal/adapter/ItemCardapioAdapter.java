package com.fernandopaiva.appfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fernandopaiva.appfinal.R;
import com.fernandopaiva.appfinal.model.ItemCardapio;

import java.util.List;

public class ItemCardapioAdapter extends BaseAdapter {
    private final Context context;
    private List<ItemCardapio> itemCardapioList;

    public ItemCardapioAdapter(Context context, List<ItemCardapio> itemCardapioList) {
        this.context = context;
        this.itemCardapioList = itemCardapioList;
    }

    @Override
    public int getCount() {
        return itemCardapioList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemCardapioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_configuracao_cardapio, parent, false);
            holder = new ViewHolder();
            holder.txtTituloPrato = convertView.findViewById(R.id.txtTituloCardapio);
            holder.txtDescricaoPrato = convertView.findViewById(R.id.txtDescricaoCardapio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ItemCardapio itemCardapio = itemCardapioList.get(position);
        holder.txtTituloPrato.setText(itemCardapio.getTitulo());
        holder.txtDescricaoPrato.setText(itemCardapio.getDescricao());

        return convertView;
    }

    public void updateList(List<ItemCardapio> newList) {
        itemCardapioList = newList;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView txtTituloPrato;
        TextView txtDescricaoPrato;
    }
}
