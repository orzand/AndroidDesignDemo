package com.orzand.androiddesigndemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.orzand.androiddesigndemo.R;

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
	TextView tv;

	public MyRecyclerViewHolder(View itemView) {
		super(itemView);

		tv = (TextView) itemView.findViewById(R.id.tv);
	}
}
