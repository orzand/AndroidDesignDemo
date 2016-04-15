package com.orzand.androiddesigndemo.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orzand.androiddesigndemo.R;
import com.orzand.androiddesigndemo.adapter.MyRecyclerViewAdapter;
import com.orzand.androiddesigndemo.support.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class PageContentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
	private static final String TITLE = "TITLE";

	private View view;
	private boolean viewCreated = false;

	private SwipeRefreshLayout swipeRefreshLayout;
	private MyRecyclerViewAdapter myRecyclerViewAdapter;
	private List<String> datas;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 0:
					myRecyclerViewAdapter.notifyDataSetChanged();
					swipeRefreshLayout.setRefreshing(false);
					break;
			}
		}
	};

	public static PageContentFragment getInstance(String title) {
		PageContentFragment fragment = new PageContentFragment();
		Bundle bundle = new Bundle();
		bundle.putString(TITLE, title);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.page_content_fragment, container, false);
		}
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		if (!viewCreated) {
			swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
			swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color
							.holo_green_light,
					android.R.color.holo_orange_light, android.R.color.holo_red_light);
			swipeRefreshLayout.setOnRefreshListener(this);

			RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
			recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
			recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration
					.VERTICAL_LIST));
			datas = new ArrayList<>();
			myRecyclerViewAdapter = new MyRecyclerViewAdapter(getContext(), datas);
			recyclerView.setAdapter(myRecyclerViewAdapter);

			// 模拟获取数据
			swipeRefreshLayout.post(new Runnable() {
				@Override
				public void run() {
					swipeRefreshLayout.setRefreshing(true);
					genData();
				}
			});

			viewCreated = true;
		}
	}

	@Override
	public void onRefresh() {
		genData();
	}

	private void genData() {
		new Thread() {
			@Override
			public void run() {
				try {
					sleep(2000);

					for (int i = 'A'; i <= 'Z'; i++) {
						datas.add(getArguments().getString(TITLE) + " " + (char) i);
					}

					mHandler.sendEmptyMessage(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
