package com.qif.id.paragraph.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qif.id.paragraph.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * author:
 * M Deni Firdaus - Paragraph.id
 * 2020
 */

public class CountryDataAdapter extends RecyclerView.Adapter<CountryDataAdapter.CountryDataViewHolder>  {

    private List<GlobalDataCountryModel> dataList;
    private Context context;

    public CountryDataAdapter(List<GlobalDataCountryModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryDataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_country,viewGroup,false);
        return new CountryDataAdapter.CountryDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryDataViewHolder holder, int position) {
        GlobalDataCountryModel models = dataList.get(position);
        try {
            holder.mIdCountry.setText(String.valueOf(models.getAttributes().getOBJECTID()));
            holder.mCountryName.setText(models.getAttributes().getCountry_Region());
            holder.mPositifCountry.setText(NumberFormat.getNumberInstance(Locale.US).format(models.getAttributes().Confirmed));

        } catch (Exception ex) {
            String err = ex.toString();
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CountryDataViewHolder extends RecyclerView.ViewHolder {

        TextView mIdCountry, mCountryName, mPositifCountry;

        public CountryDataViewHolder(@NonNull View itemView) {
            super(itemView);

            mIdCountry = (TextView) itemView.findViewById(R.id.lblCountryId);
            mCountryName = (TextView) itemView.findViewById(R.id.lblCountryName);
            mPositifCountry = (TextView) itemView.findViewById(R.id.lblCountryKasusPositif);
        }
    }
}
