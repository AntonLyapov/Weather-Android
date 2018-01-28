package com.lyapov.weather.bases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/*
 *   ****************************************************************
 *   *                                                              *
 *   *                   Created by Anton Lyapov                    *
 *   *           Copyright (c) 2018. All rights reserved.           *
 *   *                                                              *
 *   ****************************************************************
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnRecycleItemClickListener {
        <T> void onRecycleItemClick(T object);
    }

    public interface  OnRecyclerItemClickListener {
        void onRecyclerItemClick(View view, int position);
    }

    private Context mContext;
    private List mObjects = new ArrayList<>();

    protected OnRecyclerItemClickListener mOnRecyclerItemClickListener;

    public BaseRecyclerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    public Object getItem(int position) {
        return mObjects.get(position);
    }


    /**
     * Adding single object in array without animation
     *
     * @param object
     */
    public void add(Object object) {
        mObjects.add(object);
        notifyDataSetChanged();
    }

    /**
     * Adding single object in array with animation
     *
     * @param object
     */
    public void addAnimated(Object object) {
        mObjects.add(object);
        notifyItemInserted(getItemCount());
    }


    /**
     * Adding objects in array without animation
     *
     * @param objects
     */
    public <T> void addAll(Collection<T> objects) {
        mObjects.addAll(objects);
        notifyDataSetChanged();
    }

    /**
     * Adding objects in array with animation
     *
     * @param objects
     */
    public <T> void addAllAnimated(List<T> objects) {
        mObjects.addAll(objects);
        notifyItemRangeInserted(0, getItemCount());
    }


    /**
     * Adding object in start on array without animation
     *
     * @param object
     */
    public void addFirst(Object object) {
        mObjects.add(0, object);
        notifyDataSetChanged();
    }

    /**
     * Adding object in start on array with animation
     *
     * @param object
     */
    public void addFirstAnimated(Object object) {
        mObjects.add(0, object);
        notifyItemRangeChanged(0, 1);
    }

    public void addAllFirst(List<Object> objects) {
        mObjects.addAll(0, objects);
        notifyDataSetChanged();
    }

    public <T> void addAllFirstAnimated(List<T> objects) {
        mObjects.addAll(0, objects);
        notifyItemRangeInserted(0, objects.size());
    }


    /**
     * Adding object in end on array without animation
     *
     * @param object
     */
    public void addLast(Object object) {
        mObjects.add(getItemCount(), object);
        notifyDataSetChanged();
    }

    /**
     * Adding object in end on array with animation
     *
     * @param object
     */
    public void addLastAnimated(Object object) {
        mObjects.add(getItemCount(), object);
        notifyItemRangeChanged(getItemCount(), 1);
    }


    /**
     * Removing object from array without animation
     *
     * @param object
     */
    public void remove(Object object) {
        mObjects.remove(object);
        notifyDataSetChanged();
    }

    /**
     * Removing object from array with animation
     *
     * @param object
     */
    public void removeAnimated(Object object) {
        mObjects.remove(object);
        notifyItemRangeRemoved(getItemCount(), 1);
    }


    /**
     * Set/Add item to specific position without animation
     *
     * @param index
     * @param object
     */
    public void setItem(int index, Object object) {
        if (mObjects.size() > index) {
            mObjects.set(index, object);
        } else {
            mObjects.add(object);
        }
        notifyDataSetChanged();
    }

    /**
     * Set/Add item to specific position with animation
     *
     * @param index
     * @param object
     */
    public void setItemAnimated(int index, Object object) {
        if (mObjects.size() > index) {
            mObjects.set(index, object);
        } else {
            mObjects.add(object);
        }
        notifyItemRangeChanged(0, getItemCount());
    }


    /**
     * Adding item to specific position without animation
     *
     * @param position
     * @param object
     */
    public void addAtPosition(int position, Object object) {
        mObjects.add(position, object);
        notifyDataSetChanged();
    }


    /**
     * Adding item to specific position without animation
     *
     * @param position
     * @param object
     */
    public void addAtPositionWithoudRefresh(int position, Object object) {
        mObjects.add(position, object);
    }


    /**
     * Adding item to specific position with animation
     *
     * @param position
     * @param object
     */
    public void addAtPositionAnimated(int position, Object object) {
        mObjects.add(position, object);
        notifyItemInserted(position);
    }


    /**
     * Get index of object
     * @param object
     * @return
     */
    public int getIndexOf(Object object) {
        return mObjects.indexOf(object);
    }


    /**
     * Clear array without animation
     */
    public void clear() {
        mObjects.clear();
        notifyDataSetChanged();
    }


    /**
     * Clear array with animation
     */
    public void clearAnimated() {
        mObjects.clear();
        notifyItemRangeChanged(0, getItemCount());
    }


    /**
     * Clear and add all
     * @param objects
     * @param <T>
     */
    public <T> void clearAndAddAll(List<T> objects){
        mObjects.clear();
        mObjects.addAll(objects);

        notifyDataSetChanged();
    }


    /**
     * Contains object
     * @param object
     * @return
     */
    public boolean isContains(Object object) {
        return mObjects.contains(object);
    }


    /**
     * Get objects
     * @param <T>
     * @return
     */
    public <T> ArrayList<T> getObjects() {
        return (ArrayList<T>) mObjects;
    }


    /**
     * Sort with comparator
     * @param comparator
     */
    public void sort(Comparator<Object> comparator) {
        Collections.sort(mObjects, comparator);
        notifyDataSetChanged();
    }


    /**
     * Get context
     * @return
     */
    public Context getContext() {
        return mContext;
    }


    /**
     * Set on recycler item click listener
     * @param listener
     */
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        mOnRecyclerItemClickListener = listener;
    }


    /**
     * Get view
     * @param layoutResId
     * @param parent
     * @return
     */
    public View getView(int layoutResId, ViewGroup parent) {
        return LayoutInflater.from(getContext()).inflate(layoutResId, parent, false);
    }

    public OnRecyclerItemClickListener getOnRecyclerItemClickListener() {
        return mOnRecyclerItemClickListener;
    }

    public boolean hasOnRecyclerItemClickListener() {
        return mOnRecyclerItemClickListener != null;
    }
}