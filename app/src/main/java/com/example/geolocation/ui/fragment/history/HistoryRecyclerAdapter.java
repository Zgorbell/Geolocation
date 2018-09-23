package com.example.geolocation.ui.fragment.history;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geolocation.R;
import com.example.geolocation.data.db.model.WeatherDbModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.Holder> {

    private List<WeatherDbModel> objects;
    private OnCardItemClickListener listener;

    HistoryRecyclerAdapter(List<WeatherDbModel> objects, OnCardItemClickListener listener) {
        this.objects = objects;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_history, parent, false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        WeatherDbModel model = objects.get(position);
        holder.textViewDate.setText(getDate(model.getTimestamp()));
        holder.textViewRegion.setText(model.getRegion());
        holder.textViewLocation.setText(prepareLocation(model.getLon(), model.getLat()));
        holder.id = model.getId();
    }

    private String getDate(long timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        Date date = new Date(timeStamp);
        return simpleDateFormat.format(date);
    }

    private String prepareLocation(String lon, String lat){
        return "lon: " + lon + " lat " + lat;
    }

    public interface OnCardItemClickListener {
        void onCarItemClicked(long id);
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewCardDate)
        TextView textViewDate;
        @BindView(R.id.textViewCardTime)
        TextView textViewTime;
        @BindView(R.id.textViewCardRegion)
        TextView textViewRegion;
        @BindView(R.id.textViewCardLocation)
        TextView textViewLocation;
        private long id;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                listener.onCarItemClicked(id);
                Log.e(this.getClass().getSimpleName(), " id " + id);
            });
        }
    }
}
