package ru.yap.mobile_old;

import java.util.Locale;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
//import android.widget.ArrayAdapter;
//import android.content.Context;
import android.content.Intent;

public class MainActivity extends FragmentActivity implements ForumFragment.Callbacks {

	SectionsPagerAdapter sectionsPagerAdapter;
	ViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(sectionsPagerAdapter);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onItemSelected(int forum_id, int position, long id) {
		Intent intent = new Intent(this, TopicActivity.class);
		intent.putExtra("forum_id", forum_id);
		intent.putExtra("position", position);
		intent.putExtra("id", id);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_dark, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_forum:
			
			AlertDialog.Builder b = new Builder(this);
			b.setTitle(getResources().getString(R.string.menu_forum));
			b.setItems(getResources().getStringArray(R.array.forum), new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int index) {
					dialog.dismiss();
					if (index != 0) {
						Intent intent = new Intent(getBaseContext(), ForumActivity.class);
						intent.putExtra("menu_id", index);
						startActivity(intent);
					}
				}
			}).show();
			
			return true;
		/*
		case R.id.menu_preferences:
			startActivity(new Intent(this, SettingsActivity.class));
			return true;
		case R.id.menu_create:
			startActivity(new Intent(this, CreateActivity.class));
			return true;
		*/
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int index) {
			Fragment fragment = new ForumFragment();
	        Bundle args = new Bundle();
	        args.putInt("selected", 1);
	        args.putInt("forum_id", getResources().getIntArray(R.array.lenta_id)[index]);
	        fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return getResources().getIntArray(R.array.lenta_id).length;
		}

		@Override
		public CharSequence getPageTitle(int index) {
			return getResources().getStringArray(R.array.lenta)[index];
		}

	}
	
	public void onPreviewClick(View v) {
		String[] url = v.getTag().toString().split("\\|");
		if (url[0].equals("img")) {
			if (url[1].substring(url[1].lastIndexOf(".")).toLowerCase(Locale.ENGLISH).equals(".gif")
					|| android.os.Build.VERSION.SDK_INT < 8) {
				Intent intent = new Intent(this, GifActivity.class);
				intent.putExtra("url", url[1]);
				startActivity(intent);
			} else {
				Intent intent = new Intent(this, ImageActivity.class);
				intent.putExtra("url", url[1]);
				startActivity(intent);
			}
		}
		if (url[0].equals("video")) {
			/*
			Intent intent = new Intent(this, VideoActivity.class);
			intent.putExtra("url", url[1]);
			startActivity(intent);
			*/			
		}
	}
	
}

//EOF

/*
package ru.yap.mobile;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;

public class MainActivity extends FragmentActivity implements ForumFragment.Callbacks {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		if (savedInstanceState == null) {
			LentaFragment fragment = new LentaFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, fragment).commit();
		}
	}

	@Override
	public void onItemSelected(int select) {
		Intent intent = new Intent(this, TopicActivity.class);
		intent.putExtra("id", select);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_preferences:
			Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menu_new:
			Toast.makeText(this, "New", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menu_forum:
			showMenu();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void showMenu() {
		AlertDialog.Builder b = new Builder(this);
		b.setTitle(getResources().getString(R.string.menu_forum));
		b.setItems(getResources().getStringArray(R.array.forum), new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int index) {
				dialog.dismiss();
				setTitle("ID: " + index);
				setFragment(index);
			}
		}).show();
	}
	
	public void setFragment(int position) {
    	if (position == 0) {
    		LentaFragment fragment = new LentaFragment();
    		getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    	} else {
    		ForumFragment fragment = new ForumFragment();
    		getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    	}
	}

}
*/
//EOF