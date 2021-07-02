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

import static com.qif.id.paragraph.ToolsHelper.Tools.FormatCurrency;

public class ProvinceDataAdapter extends RecyclerView.Adapter<ProvinceDataAdapter.DataProvinceViewHolder> {

    private List<GlobalDataCountryModel> dataList;
    private Context context;

    public ProvinceDataAdapter(List<GlobalDataCountryModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public DataProvinceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_province,parent,false);
        return new ProvinceDataAdapter.DataProvinceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataProvinceViewHolder holder, int position) {
        GlobalDataCountryModel models = dataList.get(position);
        double positif = Integer.parseInt(models.getAttributes().getKasus_Posi());
        double sembuh = Integer.parseInt(models.getAttributes().getKasus_Semb());
        double meninggal = Integer.parseInt(models.getAttributes().getKasus_Meni());

        String mPositif = FormatCurrency(positif);
        String mSembuh = FormatCurrency(sembuh);
        String mMeninggal = FormatCurrency(meninggal);

        holder.mProvinceId.setText(String.valueOf(models.getAttributes().getKode_Provi()));

        holder.mProvinceMeninggal.setText(mMeninggal.substring(0,mMeninggal.length() - 3));
        holder.mProvincePositif.setText(mPositif.substring(0,mPositif.length() - 3));
        holder.mProvinceSembuh.setText(mSembuh.substring(0,mSembuh.length() - 3));
        holder.mProvinceNama.setText(models.getAttributes().getProvinsi());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DataProvinceViewHolder extends RecyclerView.ViewHolder {

        TextView mProvincePositif, mProvinceSembuh, mProvinceMeninggal, mProvinceNama, mProvinceId;

        public DataProvinceViewHolder(@NonNull View itemView) {
            super(itemView);
            mProvincePositif = (TextView) itemView.findViewById(R.id.lblCountPositifProvince);
            mProvinceSembuh = (TextView) itemView.findViewById(R.id.lblCountSembuhProvince);
            mProvinceMeninggal = (TextView) itemView.findViewById(R.id.lblCountMeninggalProvince);
            mProvinceNama = (TextView) itemView.findViewById(R.id.lblNamaProvinsi);
            mProvinceId = (TextView) itemView.findViewById(R.id.lblProvinceId);
        }
    }
}
