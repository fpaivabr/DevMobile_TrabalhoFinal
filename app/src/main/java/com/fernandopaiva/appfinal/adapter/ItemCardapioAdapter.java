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
    private final List<ItemCardapio> itemCardapioList;
    private final LayoutInflater inflater;

    public ItemCardapioAdapter(Context context, List<ItemCardapio> itemCardapioList) {
        this.itemCardapioList = itemCardapioList;
        this.inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.activity_lista_configuracao_cardapio, parent, false);
            holder = new ViewHolder();
            holder.txtTituloPrato = convertView.findViewById(R.id.edtTituloPrato);
            holder.txtDescricaoPrato = convertView.findViewById(R.id.edtDescricaoPrato);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ItemCardapio item = itemCardapioList.get(position);
        holder.txtTituloPrato.setText(item.getTitulo());
        holder.txtDescricaoPrato.setText(item.getDescricao());

        return convertView;
    }

    static class ViewHolder {
        TextView txtTituloPrato;
        TextView txtDescricaoPrato;
    }

    public void updateList(List<ItemCardapio> newList) {
        itemCardapioList.clear();
        itemCardapioList.addAll(newList);
        notifyDataSetChanged();
    }
}
