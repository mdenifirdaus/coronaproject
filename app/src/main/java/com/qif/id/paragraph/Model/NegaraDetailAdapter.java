package com.qif.id.paragraph.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qif.id.paragraph.R;
import com.qif.id.paragraph.ToolsHelper.Tools;

import java.util.List;

public class NegaraDetailAdapter extends RecyclerView.Adapter<NegaraDetailAdapter.NegaraDetailViewHolder> {

    private Context context;
    private List<GlobalDataCountryModel> dataList;

    public NegaraDetailAdapter(List<GlobalDataCountryModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public NegaraDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_detail_negara_lain,parent,false);
        return new NegaraDetailAdapter.NegaraDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NegaraDetailViewHolder holder, int position) {
        GlobalDataCountryModel models = dataList.get(position);
        holder.mNamaNegara.setText(models.getAttributes().getCountry_Region());
        holder.mJumlahMeninggal.setText(Tools.FormatNumber(models.getAttributes().getDeaths()));
        holder.mJumlahSembuh.setText(Tools.FormatNumber(models.getAttributes().getRecovered()));
        holder.mJumlahPositif.setText(Tools.FormatNumber(models.getAttributes().getConfirmed()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class NegaraDetailViewHolder extends RecyclerView.ViewHolder {

        TextView mNamaNegara, mJumlahPositif, mJumlahSembuh, mJumlahMeninggal;

        public NegaraDetailViewHolder(@NonNull View itemView) {
            super(itemView);

            mNamaNegara = (TextView) itemView.findViewById(R.id.lblNegaraLainDetiailNamaNegara);
            mJumlahPositif = (TextView) itemView.findViewById(R.id.lblCountPositifNegaraDetail);
            mJumlahSembuh = (TextView) itemView.findViewById(R.id.lblCountSembuhNegaraDetail);
            mJumlahMeninggal = (TextView) itemView.findViewById(R.id.lblCountMeninggalNegaraDetail);
        }
    }
}
