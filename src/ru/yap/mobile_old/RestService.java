package ru.yap.mobile_old;

import ru.yap.mobile_old.ForumOperation;
import ru.yap.mobile_old.RequestFactory;

import com.foxykeep.datadroid.service.RequestService;

public class RestService extends RequestService {

	@Override
	public Operation getOperationForType(int requestType) {
		switch (requestType) {
		case RequestFactory.REQUEST_FORUM:
			return new ForumOperation();
		default:
			return null;
		}
	}

}

//EOF