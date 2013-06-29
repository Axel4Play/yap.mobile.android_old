package ru.yap.mobile_old;

import java.util.Locale;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ForumActivity extends FragmentActivity implements ForumFragment.Callbacks {

	SharedPreferences sharedPreferences;
	
	private int MENU_ID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.forum_activity);		
		
		if (savedInstanceState == null) {
			MENU_ID = getIntent().getIntExtra("menu_id", 1);
			setFragment();
		} else {
			MENU_ID = savedInstanceState.getInt("MENU_ID");
		}
		
		setTitle(getResources().getStringArray(R.array.forum)[MENU_ID]);
	}

	@Override
    public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
        outState.putInt("MENU_ID", MENU_ID);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//if(sharedPreferences.getString("theme", "Theme_Holo_Light").equals("Theme_Holo_Light")) {
		//	getMenuInflater().inflate(R.menu.forum_light, menu);
		//} else {
			getMenuInflater().inflate(R.menu.forum_dark, menu);
		//}
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
						MENU_ID = index;
						setFragment();
					} else {
						Intent intent = new Intent(getBaseContext(), MainActivity.class);
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
	
	@Override
	public void onItemSelected(int forum_id, int position, long id) {
		Intent intent = new Intent(this, TopicActivity.class);
		intent.putExtra("forum_id", forum_id);
		intent.putExtra("position", position);
		intent.putExtra("id", id);
		startActivity(intent);
	}
	
	public void setFragment() {
		
		setTitle(getResources().getStringArray(R.array.forum)[MENU_ID]);
		
		ForumFragment fragment = new ForumFragment();
        Bundle args = new Bundle();
        args.putInt("selected", 1);
        args.putInt("forum_id", getResources().getIntArray(R.array.forum_id)[MENU_ID]);
        fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction().replace(
			R.id.frame_layout, 
			fragment, 
			"forum_activity_tag_" + getResources().getIntArray(R.array.forum_id)[MENU_ID]
		).commit();
	}
	
	public void onPreviewClick(View v) {
		String[] url = v.getTag().toString().split("\\|");		
		if (url[0].equals("img")) {
			if (url[1].substring(url[1].lastIndexOf(".")).toLowerCase(Locale.ENGLISH).equals(".gif")) {
				Intent intent = new Intent(this, GifActivity.class);
				intent.putExtra("url", url[1]);
				startActivity(intent);
			} else {
				Intent intent = new Intent(this, ImageActivity.class);
				intent.putExtra("url", url[1]);
				startActivity(intent);
			}
		}
		/*
		if (url[0].equals("video")) {
			Intent intent = new Intent(this, VideoActivity.class);
			intent.putExtra("url", url[1]);
			startActivity(intent);			
		}
		*/
	}
}

//EOF
