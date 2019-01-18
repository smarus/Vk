package ownvk.ruslan.android.myownvk.rest.api;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import ownvk.ruslan.android.myownvk.model.Profile;
import ownvk.ruslan.android.myownvk.rest.model.response.Full;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface UserApi {
	@GET(ApiMethods.USERS_GET)
	Observable<Full<List<Profile>>> get(@QueryMap Map<String, String> map);
}
