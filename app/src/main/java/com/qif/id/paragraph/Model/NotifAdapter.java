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

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.NotifViewHolder> {

    Context context;
    List<NotifModel> dataList;

    public NotifAdapter(Context context, List<NotifModel> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public NotifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_notif,parent,false);
        return new NotifAdapter.NotifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifViewHolder holder, int i) {
        NotifModel model = dataList.get(i);
        holder.mTitleNotif.setText(model.getTitle());
        holder.mNotifDate.setText(model.getNotif_date_form());
        holder.mDesc.setText(model.getDesc_title());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class NotifViewHolder extends RecyclerView.ViewHolder {

        TextView mNotifDate, mTitleNotif, mDesc;

        public NotifViewHolder(@NonNull View itemView) {
            super(itemView);

            mNotifDate = (TextView) itemView.findViewById(R.id.lblNotifikasiNotifDate);
            mTitleNotif = (TextView) itemView.findViewById(R.id.lblNotifikasiSubject);
            mDesc = (TextView) itemView.findViewById(R.id.lblNotifikasiMessages);
        }
    }
}
