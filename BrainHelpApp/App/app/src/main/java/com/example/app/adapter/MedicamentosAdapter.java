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
import com.example.app.classes.Medicamento;
import com.example.app.enumerated.Frequencia;
import com.example.app.utils.BitmapUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MedicamentosAdapter extends ArrayAdapter<Medicamento> {

    Context context;

    public MedicamentosAdapter(Context context, int resource, List<Medicamento> medicamentos) {
        super(context, resource, medicamentos);
        this.context = context;
    }

    private class ViewHolder{
        ImageView ivFoto;
        TextView tvTitulo;
        TextView tvQuantidade;
        TextView tvFrequencia;
        TextView tvHorario;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        Medicamento medicamento = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_medicamentos, null);
            holder = new ViewHolder();
            holder.tvTitulo = convertView.findViewById(R.id.tv_titulo);
            holder.tvQuantidade = convertView.findViewById(R.id.tv_quantidade);
            holder.tvFrequencia = convertView.findViewById(R.id.tv_frequencia);
            holder.tvHorario = convertView.findViewById(R.id.tv_horario);
            holder.ivFoto = convertView.findViewById(R.id.iv_foto);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        String frequencia = null;
        int horaAcrescentada = 0;
        switch (medicamento.getFrequencia()){
            case QUATRO:
                frequencia = "De 4 em 4 horas";
                horaAcrescentada = 4;
                break;
            case SEIS:
                frequencia = "De 6 em 6 horas";
                horaAcrescentada = 6;
                break;
            case OITO:
                frequencia = "De 8 em 8 horas";
                horaAcrescentada = 8;
                break;
            case DOZE:
                frequencia = "De 12 em 12 horas";
                horaAcrescentada = 12;
                break;
            case DIARIA:
                frequencia = "1 vez por dia";
                break;
            case SEMANAL:
                frequencia = "1 vez por semana";
                break;
            case MENSAL:
                frequencia = "1 vez por mês";
                break;
        }


        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", new Locale("pt", "BR"));
        String data = dateFormat.format(medicamento.getHorario());


        if(medicamento.getFrequencia().equals(Frequencia.QUATRO) || medicamento.getFrequencia().equals(Frequencia.SEIS)
                || medicamento.getFrequencia().equals(Frequencia.OITO) || medicamento.getFrequencia().equals(Frequencia.DOZE)){

            String [] horario = data.split(":");

            Calendar horaMedicamento = Calendar.getInstance();
            horaMedicamento.set(Calendar.HOUR, Integer.parseInt(horario[0]));
            horaMedicamento.set(Calendar.MINUTE, Integer.parseInt(horario[1]));

            if(Calendar.getInstance().getTimeInMillis() > horaMedicamento.getTimeInMillis()){
                horaMedicamento.set(Calendar.HOUR, horaMedicamento.get(Calendar.HOUR) + horaAcrescentada);
            }

            data = dateFormat.format(horaMedicamento.getTime());
        }

        holder.tvTitulo.setText(medicamento.getNomeMedicamento());
        holder.tvFrequencia.setText(frequencia);
        holder.tvHorario.setText(data);
        holder.tvQuantidade.setText(String.valueOf(medicamento.getQuantidade()));
        if(medicamento.getFoto() != null && !medicamento.getFoto().isEmpty()){
            holder.ivFoto.setImageBitmap(BitmapUtils.base64ToBitmap(medicamento.getFoto()));
        }else{
            holder.ivFoto.setImageResource(R.drawable.sem_foto);
        }
        return convertView;
    }
}
