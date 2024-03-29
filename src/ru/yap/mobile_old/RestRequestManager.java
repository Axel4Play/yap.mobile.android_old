package ru.yap.mobile_old;

import ru.yap.mobile_old.RestService;

import com.foxykeep.datadroid.requestmanager.RequestManager;

import android.content.Context;

public final class RestRequestManager extends RequestManager {
	private RestRequestManager(Context context) {
		super(context, RestService.class);
	}

	private static RestRequestManager sInstance;

	public static RestRequestManager from(Context context) {
		if (sInstance == null) {
			sInstance = new RestRequestManager(context);
		}
		return sInstance;
	}
}

//EOF