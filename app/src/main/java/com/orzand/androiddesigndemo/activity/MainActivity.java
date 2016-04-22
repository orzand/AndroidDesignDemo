package com.orzand.androiddesigndemo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.orzand.androiddesigndemo.R;
import com.orzand.androiddesigndemo.fragment.DiscoveryFragment;
import com.orzand.androiddesigndemo.fragment.FavoriteFragment;
import com.orzand.androiddesigndemo.fragment.FocusFragment;
import com.orzand.androiddesigndemo.fragment.HomePageFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	private DrawerLayout drawer;

	private FragmentManager fragmentManager;
	private Fragment fragment;
	private HomePageFragment homePageFragment;
	private DiscoveryFragment discoveryFragment;
	private FocusFragment focusFragment;
	private FavoriteFragment favoriteFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	private void initView() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		setTitle("主页");

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null)
						.show();
			}
		});

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string
				.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		// 处理NavigationView中的HeaderView的监听事件
		navigationView.getHeaderView(0).findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "imageView clicked", Toast.LENGTH_SHORT).show();
			}
		});

		// init Fragments
		fragmentManager = getSupportFragmentManager();
		homePageFragment = HomePageFragment.getInstance("主页");
		fragment = homePageFragment;
		fragmentManager.beginTransaction().add(R.id.content_main, homePageFragment).commit();
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// 根据标题动态加载menu
		menu.clear();
		String title = getTitle().toString();
		if ("收藏".equals(title)) {
			return true;
		} else if ("关注".equals(title)) {
			getMenuInflater().inflate(R.menu.focus, menu);
		} else {
			getMenuInflater().inflate(R.menu.main, menu);
		}

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_search:
				Toast.makeText(this, R.string.action_search, Toast.LENGTH_SHORT).show();
				break;
			case R.id.action_share:
				Toast.makeText(this, R.string.action_share, Toast.LENGTH_SHORT).show();
				break;
			case R.id.action_focus:
				Toast.makeText(this, R.string.action_focus, Toast.LENGTH_SHORT).show();
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// TODO Handle navigation view item clicks here.
		switch (item.getItemId()) {
			case R.id.nav_camera:
				if (homePageFragment == null) {
					homePageFragment = HomePageFragment.getInstance("主页");
				}

				switchContentFragment(fragment, homePageFragment);
				fragment = homePageFragment;

				setTitle(item.getTitle());
				break;

			case R.id.nav_gallery:
				if (discoveryFragment == null) {
					discoveryFragment = DiscoveryFragment.getInstance("发现");
				}

				switchContentFragment(fragment, discoveryFragment);
				fragment = discoveryFragment;

				setTitle(item.getTitle());
				break;

			case R.id.nav_slideshow:
				if (focusFragment == null) {
					focusFragment = FocusFragment.getInstance("关注");
				}

				switchContentFragment(fragment, focusFragment);
				fragment = focusFragment;

				setTitle(item.getTitle());

				break;

			case R.id.nav_manage:
				if (favoriteFragment == null) {
					favoriteFragment = FavoriteFragment.getInstance("收藏");
				}

				switchContentFragment(fragment, favoriteFragment);
				fragment = favoriteFragment;

				setTitle(item.getTitle());

				break;

			case R.id.nav_share:
				break;

			case R.id.nav_send:
				finish();
				break;
		}

		invalidateOptionsMenu();
		drawer.closeDrawer(GravityCompat.START);

		return true;
	}

	private void switchContentFragment(Fragment from, Fragment to) {
		if (from != to) {
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

			if (!to.isAdded()) {
				transaction.hide(from).add(R.id.content_main, to).commit();
			} else {
				transaction.hide(from).show(to).commit();
			}
		}
	}
}
