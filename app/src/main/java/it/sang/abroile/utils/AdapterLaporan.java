package it.sang.abroile.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.sang.abroile.R;

public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.ViewHolder> {
    List<ModelLaporan> laporans;
    Context context;
    Dialog dialog;
    ImageButton btn;
    TextView txt;

    public AdapterLaporan(List<ModelLaporan> modelLaporans, Context context) {
        this.laporans = modelLaporans;
        this.context = context;
        dialog = new Dialog(context,android.R.style.ThemeOverlay_Material);
        dialog.setContentView(R.layout.layout_full);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    @NonNull
    @Override
    public AdapterLaporan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View laporan = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laporan, parent, false);
        return new AdapterLaporan.ViewHolder(laporan);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterLaporan.ViewHolder holder, final int position) {
        holder.nama.setText(laporans.get(position).getNama()+" : ");
        holder.no.setText("No : "+laporans.get(position).getNo());
        holder.email.setText("Email : "+laporans.get(position).getEmail());
        holder.lapor.setText('"'+laporans.get(position).getLaporan()+'"');
        holder.vap.setOnClickListener(v -> {
            openDialog(position);
        });
    }

    private void openDialog(int pos){
        dialog.show();
        btn = dialog.findViewById(R.id.btnClose);
        txt = dialog.findViewById(R.id.txtNextDesc);

        btn.setOnClickListener(v ->{
            dialog.dismiss();
        });
        txt.setText(laporans.get(pos).getLaporan());

    }

    @Override
    public int getItemCount() {
        if (laporans != null) {
            return laporans.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, no, email, lapor;
        CardView vap;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.itemNA);
            no = itemView.findViewById(R.id.itemNO);
            email = itemView.findViewById(R.id.itemEM);
            lapor= itemView.findViewById(R.id.itemPE);
            vap = itemView.findViewById(R.id.cardClickV);
        }
    }
}
