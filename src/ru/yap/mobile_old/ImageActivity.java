package ru.yap.mobile_old;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
//import it.sephiroth.android.library.imagezoom.ImageViewTouch.OnImageViewTouchSingleTapListener;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase.DisplayType;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class ImageActivity extends FragmentActivity {
	
	//private boolean showBar = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		
		super.onCreate(savedInstanceState);

		setContentView(R.layout.image_activity);
		
		ImageViewTouch imageView = (ImageViewTouch) findViewById(R.id.image);

		imageView.setDisplayType(DisplayType.FIT_IF_BIGGER);

		ImageLoader imageLoader = ImageLoader.getInstance();
		if (!imageLoader.isInited()) {
			imageLoader.init(new ImageLoaderConfiguration.Builder(ImageActivity.this)
				.discCacheFileNameGenerator(new YapURLFileNameGenerator())
				//.enableLogging()
				.build()
			);			
		}
		DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.navigation_refresh_dark)
			.showImageOnFail(R.drawable.alerts_and_states_warning_dark)
			.resetViewBeforeLoading()
			.cacheOnDisc()
			.build();
		
		imageLoader.displayImage(getIntent().getStringExtra("url"), imageView, options);
	}
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image, menu);
        return true;
    }
    */

	public class YapURLFileNameGenerator implements FileNameGenerator {
		@Override
		public String generate(String imageUri) {
			if (imageUri.lastIndexOf(".") > 0) {
				return "yap" + String.valueOf(imageUri.hashCode()) + imageUri.substring(imageUri.lastIndexOf("."));
			} else {
				return "yap" + String.valueOf(imageUri.hashCode());
			}
		}
	}
}

//EOF