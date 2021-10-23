package it.sang.abroile.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.sang.abroile.PerhitunganDiagnosa;
import it.sang.abroile.R;

public class AdapterGejala extends RecyclerView.Adapter<AdapterGejala.ViewHolder> {
    List<ModelGejala> gejalas;
    Context context;

    public AdapterGejala(List<ModelGejala> modelGejalas, Context context) {
        this.gejalas = modelGejalas;
        this.context = context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View gejaka= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gejala,parent,false);
        return new ViewHolder(gejaka);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.nmGejala.setText(gejalas.get(position).getNama_gejala());
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.r1:
                        holder.cfu.setText("0");
                        holder.idsoal.setText(gejalas.get(position).getId_gejala());
                        break;
                    case R.id.r2:
                        holder.cfu.setText("0.2");
                        holder.idsoal.setText(gejalas.get(position).getId_gejala());
                        break;
                    case R.id.r3:
                        holder.cfu.setText("0.4");
                        holder.idsoal.setText(gejalas.get(position).getId_gejala());
                        break;
                    case R.id.r4:
                        holder.cfu.setText("0.6");
                        holder.idsoal.setText(gejalas.get(position).getId_gejala());
                        break;
                    case R.id.r5:
                        holder.cfu.setText("0.8");
                        holder.idsoal.setText(gejalas.get(position).getId_gejala());
                        break;
                    case R.id.r6:
                        holder.cfu.setText("1");
                        holder.idsoal.setText(gejalas.get(position).getId_gejala());
                        break;
                }
                PerhitunganDiagnosa.integerList.set(position, holder.cfu.getText().toString());
            }
        });
    }


    @Override
    public int getItemCount() {
        if (gejalas != null){
            return gejalas.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nmGejala, idsoal, cfu;
        RadioGroup radioGroup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idsoal = itemView.findViewById(R.id.idsoal);
            cfu = itemView.findViewById(R.id.idcfuser);
            nmGejala = itemView.findViewById(R.id.nmGejala);
            radioGroup = itemView.findViewById(R.id.radioGrup);
        }
    }

}
