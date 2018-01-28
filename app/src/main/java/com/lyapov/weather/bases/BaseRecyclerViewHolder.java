package com.lyapov.weather.bases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public abstract class BaseRecyclerViewHolder<Z, T extends BaseRecyclerAdapter> extends RecyclerView.ViewHolder {

    public abstract void setData(Z data);

    private T adapter;

    public BaseRecyclerViewHolder(T adapter, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.adapter = adapter;
    }

    public Context getContext() {
        return itemView.getContext();
    }

    public T getAdapter() {
        return adapter;
    }
}