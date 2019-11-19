package com.example.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.app.R;
import com.example.app.classes.Vinculo;

import java.util.List;

public class ContatosAdapter extends ArrayAdapter<Vinculo> {

    Context context;

    public ContatosAdapter(Context context, int resource, List<Vinculo> vinculos) {
        super(context, resource, vinculos);
        this.context = context;
    }

    private class ViewHolder{
        ImageView ivFoto;
        TextView tvNome;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        Vinculo vinculo = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_contatos, null);
            holder = new ViewHolder();
            holder.tvNome = convertView.findViewById(R.id.tv_nome);
            holder.ivFoto = convertView.findViewById(R.id.iv_foto);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvNome.setText(vinculo.getNome()+" "+ vinculo.getSobrenome());
        holder.ivFoto.setImageResource(R.drawable.puzzle);
        return convertView;
    }
}
