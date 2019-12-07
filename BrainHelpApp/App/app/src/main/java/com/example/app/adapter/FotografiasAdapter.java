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
import com.example.app.model.Fotografia;
import com.example.app.util.BitmapUtils;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FotografiasAdapter extends ArrayAdapter<Fotografia> {

    Context context;

    public FotografiasAdapter(Context context, int resource, List<Fotografia> fotografias) {
        super(context, resource, fotografias);
        this.context = context;
    }

    private class ViewHolder{
        ImageView ivFoto;
        TextView tvTitulo;
        TextView tvLembrancas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        Fotografia fotografia = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_fotos, null);
            holder = new ViewHolder();
            holder.tvTitulo = convertView.findViewById(R.id.tv_titulo);
            holder.tvLembrancas = convertView.findViewById(R.id.tv_lembrancas);
            holder.ivFoto = convertView.findViewById(R.id.iv_foto);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        String data = dateFormat.format(fotografia.getData());

        holder.tvTitulo.setText(fotografia.getLugar() + " " + data);
        holder.tvLembrancas.setText(fotografia.getLembrancas());
        if(fotografia.getFoto() != null && !fotografia.getFoto().isEmpty()){
            holder.ivFoto.setImageBitmap(BitmapUtils.base64ToBitmap(fotografia.getFoto()));
        }else{
            holder.ivFoto.setImageResource(R.drawable.sem_foto);
        }
        return convertView;
    }
}
