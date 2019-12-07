package com.example.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.app.R;
import com.example.app.model.Tarefa;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TarefasAdapter extends ArrayAdapter<Tarefa> {

    Context context;
    Tarefa tarefa;

    public TarefasAdapter(Context context, int resource, List<Tarefa> tarefas) {
        super(context, resource, tarefas);
        this.context = context;
    }

    private class ViewHolder{
        TextView tvTitulo;
        TextView tvData;
        TextView tvHorario;
        TextView tvStatus;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        tarefa = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_tarefas, null);
            holder = new ViewHolder();
            holder.tvTitulo = convertView.findViewById(R.id.tv_titulo);
            holder.tvData = convertView.findViewById(R.id.tv_data);
            holder.tvHorario = convertView.findViewById(R.id.tv_horario);
            holder.tvStatus = convertView.findViewById(R.id.tv_status);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        String data = dateFormat.format(tarefa.getDataRealizacao());

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", new Locale("pt", "BR"));
        String hora = timeFormat.format(tarefa.getDataRealizacao());

        holder.tvTitulo.setText("Tarefa: "+tarefa.getTarefa());
        holder.tvData.setText("Data: "+data);
        holder.tvHorario.setText("Horário: "+hora);

        switch (tarefa.getStatusTarefa()){
            case CONCLUIDA:
                holder.tvStatus.setText("Status: Concluída");
                break;
            case NAO_CONCLUIDA:
                holder.tvStatus.setText("Status: Não concluída");
                break;
        }
        return convertView;
    }
}
