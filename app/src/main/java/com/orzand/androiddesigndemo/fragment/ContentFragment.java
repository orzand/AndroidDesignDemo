package com.orzand.androiddesigndemo.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orzand.androiddesigndemo.R;

public class ContentFragment extends BaseFragment {
	private TabLayout tbl;
	private ViewPager vp;

	public static ContentFragment getInstance(String title) {
		ContentFragment fragment = new ContentFragment();

		Bundle bundle = new Bundle();
		bundle.putString(TITLE, title);
		fragment.setArguments(bundle);

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.content_fragment, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		tbl = (TabLayout) view.findViewById(R.id.tbl);
		vp = (ViewPager) view.findViewById(R.id.vp);
	}
}
