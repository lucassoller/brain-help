package com.example.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.app.R;
import com.example.app.model.Endereco;
import com.example.app.util.BitmapUtils;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EnderecosAdapter extends ArrayAdapter<Endereco> {

    Context context;

    public EnderecosAdapter(Context context, int resource, List<Endereco> enderecos) {
        super(context, resource, enderecos);
        this.context = context;
    }

    private class ViewHolder{
        ImageView ivFoto;
        TextView tvTitulo;
        TextView tvEndereco;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        Endereco endereco = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_enderecos, null);
            holder = new ViewHolder();
            holder.tvTitulo = convertView.findViewById(R.id.tv_titulo);
            holder.tvEndereco = convertView.findViewById(R.id.tv_endereco);
            holder.ivFoto = convertView.findViewById(R.id.iv_foto);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvTitulo.setText(endereco.getTitulo());
        holder.tvEndereco.setText("Endereço: " + endereco.getLogradouro() + ", "+ endereco.getNumero() + " - Bairro" + endereco.getBairro() + ", " +
                endereco.getCidade() + " - " + endereco.getEstado());
        if(endereco.getFoto() != null && !endereco.getFoto().isEmpty()){
            holder.ivFoto.setImageBitmap(BitmapUtils.base64ToBitmap(endereco.getFoto()));
        }else{
            holder.ivFoto.setImageResource(R.drawable.sem_foto);
        }
        return convertView;
    }
}
