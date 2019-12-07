package com.example.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.app.R;
import com.example.app.model.Desempenho;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DesempenhosAdapter extends ArrayAdapter<Desempenho> {

    Context context;

    public DesempenhosAdapter(Context context, int resource, List<Desempenho> desempenhos) {
        super(context, resource, desempenhos);
        this.context = context;
    }

    private class ViewHolder{
        TextView tvAtividade;
        TextView tvPontuacao;
        TextView tvDesempenho;
        TextView tvDataRealizacao;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        Desempenho desempenho = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_desempenhos, null);
            holder = new ViewHolder();
            holder.tvAtividade = convertView.findViewById(R.id.tv_atividade);
            holder.tvPontuacao = convertView.findViewById(R.id.tv_pontuacao);
            holder.tvDesempenho = convertView.findViewById(R.id.tv_desempenho);
            holder.tvDataRealizacao = convertView.findViewById(R.id.tv_data_realizacao);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));
        String data = dateFormat.format(desempenho.getDataRealizacao());

        holder.tvAtividade.setText("Atividade: "+desempenho.getAtividade());
        holder.tvPontuacao.setText("Pontuação: "+desempenho.getPontuacao()+ " pontos");
        holder.tvDesempenho.setText("Desempenho: "+desempenho.getAvaliacaoDesempenho());
        holder.tvDataRealizacao.setText("Data realização: "+data);
        return convertView;
    }
}
