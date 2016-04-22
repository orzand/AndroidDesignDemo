package com.orzand.androiddesigndemo.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orzand.androiddesigndemo.R;
import com.orzand.androiddesigndemo.adapter.MyViewPagerAdapter;

import java.util.ArrayList;

public class HomePageFragment extends BaseFragment {
	private static final String[] VIEW_PAGER_TITLES = {"推荐", "热点", "北京", "科技", "财经", "汽车"};

	public static HomePageFragment getInstance(String title) {
		HomePageFragment fragment = new HomePageFragment();

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

		TabLayout tbl = (TabLayout) view.findViewById(R.id.tbl);
		ViewPager vp = (ViewPager) view.findViewById(R.id.vp);

		ArrayList<Fragment> fragments = new ArrayList<>();
		for (String title : VIEW_PAGER_TITLES) {
			fragments.add(PageContentFragment.getInstance(title));
		}

		MyViewPagerAdapter adapter = new MyViewPagerAdapter(getChildFragmentManager(), fragments);
		vp.setAdapter(adapter);
		vp.setOffscreenPageLimit(3);

		tbl.setupWithViewPager(vp);
	}
}
