package com.example.naveen.wifidirect.adapters;

import android.support.v7.widget.RecyclerView;


import com.example.naveen.wifidirect.adapters.viewholder.BaseViewHolder;
import com.example.naveen.wifidirect.utils.CodeSnippet;

import java.util.ArrayList;
import java.util.List;


abstract class BaseRecyclerAdapter<T, V extends BaseViewHolder> extends RecyclerView.Adapter<V> {

    private List<T> data;

    BaseRecyclerAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
        holder.setData(getItem(position));
    }

    @Override
    public int getItemCount() {
        return CodeSnippet.getListSize(data);
    }

    public T getItem(int position) throws IndexOutOfBoundsException {
        return data.get(position);
    }

    public void addItem(T object) {
        data.add(object);

        notifyItemInserted(data.indexOf(object));
    }

    public void removeItem(int position) throws IndexOutOfBoundsException {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void resetItems(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addItems(List<T> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void setFilter(List<T> data) {
        this.data = new ArrayList<>();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
    public List<T> getList(){
        return data;
    }

    public void clearData(){
        data.clear();
        notifyDataSetChanged();
    }

    public void removeItem(T d){
        data.remove(d);
        notifyDataSetChanged();
    }

}
