package com.orzand.androiddesigndemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orzand.androiddesigndemo.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {
	private Context context;
	private List<String> datas;
	private OnItemClickLitener mOnItemClickLitener;

	public MyRecyclerViewAdapter(Context context, List<String> datas) {
		this.context = context;
		this.datas = datas;
	}

	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
		this.mOnItemClickLitener = mOnItemClickLitener;
	}

	@Override
	public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new MyRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent,
				false));
	}

	@Override
	public void onBindViewHolder(final MyRecyclerViewHolder holder, int position) {
		holder.tv.setText(datas.get(position));

		if (mOnItemClickLitener != null) {
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemClick(holder.itemView, pos);
				}
			});
		}

	}

	@Override
	public int getItemCount() {
		return datas.size();
	}

//	public void setDatas(List<String> datas) {
//		this.datas = datas;
//		notifyDataSetChanged();
//	}

	public interface OnItemClickLitener {
		void onItemClick(View view, int position);
	}
}
