package com.example.geolocation.ui.fragment.history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.geolocation.R;
import com.example.geolocation.data.db.model.WeatherDbModel;
import com.example.geolocation.ui.activity.main.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoryFragment extends MvpAppCompatFragment implements HistoryMvpView{

    private static final String TAG = HistoryFragment.class.getSimpleName();
    @InjectPresenter
    HistoryPresenter historyPresenter;
    @BindView(R.id.historyRecyclerView)
    RecyclerView historyRecyclerView;
    private MainActivity mainActivity;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        unbinder = ButterKnife.bind(this, view);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
        historyPresenter.onCreated();
        return view;
    }

    @Override
    public void showMessage(String message) {
        mainActivity.showMessage(message);
    }

    @Override
    public void setAdapter(List<WeatherDbModel> list) {
        historyRecyclerView.setAdapter(new HistoryRecyclerAdapter(list, historyPresenter::elementChecked));
        historyRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void startActivity(Class activity) {
        startActivity(new Intent(mainActivity, activity));
    }
}
