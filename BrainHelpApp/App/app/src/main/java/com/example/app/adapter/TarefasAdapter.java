package com.example.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.R;
import com.example.app.activities.TelaInicialActivity;
import com.example.app.utils.MyErrorMessage;
import com.example.app.utils.RetrofitUtils;
import com.example.app.classes.Tarefa;
import com.example.app.enumerated.StatusTarefa;
import com.example.app.services.TarefaService;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        CheckBox cbCompleta;
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
            holder.cbCompleta = convertView.findViewById(R.id.cb_completa);
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
                holder.cbCompleta.setChecked(true);
                break;
            case NAO_CONCLUIDA:
                holder.cbCompleta.setChecked(false);
                break;
        }

        holder.cbCompleta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tarefa.setStatusTarefa(StatusTarefa.CONCLUIDA);
                }else{
                    tarefa.setStatusTarefa(StatusTarefa.NAO_CONCLUIDA);
                }
                atualizaStatus();
            }
        });


        return convertView;
    }

    private void atualizaStatus(){
        TarefaService tarefaService = RetrofitUtils.retrofit.create(TarefaService.class);
        tarefaService.editarStatusTarefa(TelaInicialActivity.sp.getString("token", null), tarefa).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "Cadastro concluído", Toast.LENGTH_LONG).show();
                }else{
                    Gson gson = new Gson();
                    MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                    Toast.makeText(context, message.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
