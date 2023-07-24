package com.samples.phoneverification.apimodel;

public interface OnRecyclerItemClickListener<T> {
    void onItemClicked(T item, int position, int action);
}
