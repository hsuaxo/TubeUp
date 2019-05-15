package com.hsuaxo.tubeup;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsuaxo.tubeup.rxtube.YTContent;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter {
    private final List<YTContent> mDataSet;
    private RecyclerViewClickListener mClickListener;

    public SearchAdapter(List<YTContent> dataSet, RecyclerViewClickListener clickListener) {
        mDataSet = dataSet;
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_youtube, parent, false);
        return new SearchViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final YTContent content = mDataSet.get(position);
        final SearchViewHolder holder = (SearchViewHolder) viewHolder;
        holder.bindData(content);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public YTContent getItem(final int index) {
        return mDataSet.get(index);
    }

    public void replaceData(List<YTContent> newData) {
        mDataSet.clear();
        mDataSet.addAll(newData);
        notifyDataSetChanged();
    }
}