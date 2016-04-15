package com.orzand.androiddesigndemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orzand.androiddesigndemo.R;

public class FocusFragment extends BaseFragment {

	public static FocusFragment getInstance(String title) {
		FocusFragment fragment = new FocusFragment();

		Bundle bundle = new Bundle();
		bundle.putString(TITLE, title);
		fragment.setArguments(bundle);

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.focus_fragment, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
}
