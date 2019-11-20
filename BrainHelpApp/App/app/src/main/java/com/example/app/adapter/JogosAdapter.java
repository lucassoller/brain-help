package com.example.app.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.app.R;
import com.example.app.classes.Atividade;
import java.util.List;

public class JogosAdapter extends ArrayAdapter<Atividade> {

    private Context context;
    private Atividade atividade;
    private AlertDialog alerta;

    public JogosAdapter(Context context, int resource, List<Atividade> jogos) {
        super(context, resource, jogos);
        this.context = context;
    }

    private class ViewHolder{
        TextView tvNome;
        Button btDescricao;
        ImageView ivFoto;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        atividade = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_jogos, null);
            holder = new ViewHolder();
            holder.tvNome = convertView.findViewById(R.id.tv_nome);
            holder.btDescricao = convertView.findViewById(R.id.bt_descricao);
            holder.ivFoto = convertView.findViewById(R.id.iv_foto);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvNome.setText(atividade.getNome());
        holder.btDescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        holder.ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "oi", Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(atividade.getNome());
        builder.setMessage(atividade.getDescricao()+"\n\n"+"Pontuação total: "+atividade.getPontuacaoTotal() + " pontos");
        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        alerta = builder.create();
        alerta.show();
    }
}
