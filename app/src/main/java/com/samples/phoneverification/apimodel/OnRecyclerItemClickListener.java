package com.samples.phoneverification.apimodel;

public interface OnRecyclerItemClickListener<T> {
    public void onItemClicked(T item, int position, int action);
}
