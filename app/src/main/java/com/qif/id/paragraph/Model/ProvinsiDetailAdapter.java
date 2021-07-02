package com.qif.id.paragraph.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qif.id.paragraph.R;

import java.util.List;

public class ProvinsiDetailAdapter extends RecyclerView.Adapter<ProvinsiDetailAdapter.DataProvinceDetailViewHolder> {

    private List<GlobalDataCountryModel> dataList;
    private Context context;

    @NonNull
    @Override
    public DataProvinceDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_provinsi_detail,parent,false);
        return new ProvinsiDetailAdapter.DataProvinceDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataProvinceDetailViewHolder holder, int position) {
        GlobalDataCountryModel models = dataList.get(position);
        holder.mProvinceMeninggal.setText(models.getAttributes().getKasus_Meni());
        holder.mProvincePositif.setText(models.getAttributes().getKasus_Posi());
        holder.mProvinceSembuh.setText(models.getAttributes().getKasus_Semb());
        holder.mProvinceNama.setText(models.getAttributes().getProvinsi());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DataProvinceDetailViewHolder extends RecyclerView.ViewHolder {

        TextView mProvincePositif, mProvinceSembuh, mProvinceMeninggal, mProvinceNama;

        public DataProvinceDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            mProvincePositif = (TextView) itemView.findViewById(R.id.lblCountPositifProvinsiDetail);
            mProvinceSembuh = (TextView) itemView.findViewById(R.id.lblCountSembuhProvinsiDetail);
            mProvinceMeninggal = (TextView) itemView.findViewById(R.id.lblCountMeninggalProvinsiDetail);
            mProvinceNama = (TextView) itemView.findViewById(R.id.lblNamaProvinsiDetail);
        }
    }
}
