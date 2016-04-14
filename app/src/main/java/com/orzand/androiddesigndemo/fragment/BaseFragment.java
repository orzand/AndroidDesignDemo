package com.orzand.androiddesigndemo.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {
	protected static final String TITLE = "title";
	protected Context context;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.context = context;
	}

	public final String getTitle() {
		if (getArguments() != null) {
			return getArguments().getString(TITLE);
		}

		return null;
	}
}
