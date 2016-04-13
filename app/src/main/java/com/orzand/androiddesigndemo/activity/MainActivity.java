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
import com.orzand.androiddesigndemo.fragment.ContentFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	private Toolbar toolbar;
	private FloatingActionButton fab;
	private DrawerLayout drawer;
	private NavigationView navigationView;

	private FragmentManager fragmentManager;
	private Fragment fragment;
	private ContentFragment contentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	private void initView() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		fab = (FloatingActionButton) findViewById(R.id.fab);
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

		navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		// 处理NavigationView中的HeaderView的监听事件
		navigationView.getHeaderView(0).findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "imageView clicked", Toast.LENGTH_SHORT).show();
			}
		});

		// TODO init Fragments
		fragmentManager = getSupportFragmentManager();
		contentFragment = ContentFragment.getInstance("内容 1");
		switchContentFragment(fragment, contentFragment);
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
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO
		switch (item.getItemId()) {
			case R.id.action_search:
				Toast.makeText(this, R.string.action_search, Toast.LENGTH_SHORT).show();
				break;
			case R.id.action_share:
				Toast.makeText(this, R.string.action_share, Toast.LENGTH_SHORT).show();
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// TODO Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_camera) {
			switchContentFragment(fragment, contentFragment);
			fragment = contentFragment;
		} else if (id == R.id.nav_gallery) {

		} else if (id == R.id.nav_slideshow) {

		} else if (id == R.id.nav_manage) {

		} else if (id == R.id.nav_share) {

		} else if (id == R.id.nav_send) {

		}

		//		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	private void switchContentFragment(Fragment from, Fragment to) {
		if (from == null && to == null) {
			return;
		}

		if (from != to) {
			FragmentTransaction transaction = fragmentManager.beginTransaction();

			if (from != null) {
				if (!to.isAdded()) {
					transaction.hide(from).add(R.id.content_main, to).commit();
				} else {
					transaction.hide(from).show(to).commit();
				}
			} else {
				if (!to.isAdded()) {
					transaction.add(R.id.content_main, to).commit();
				} else {
					transaction.show(to).commit();
				}
			}
		}
	}
}
