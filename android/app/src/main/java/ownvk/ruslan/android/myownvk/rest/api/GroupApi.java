package ownvk.ruslan.android.myownvk.rest.api;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import ownvk.ruslan.android.myownvk.model.Group;
import ownvk.ruslan.android.myownvk.rest.model.response.Full;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GroupApi {
	@GET(ApiMethods.GROUPS_GET_BY_ID)
	Observable<Full<List<Group>>> getById(@QueryMap Map<String, String> map);
}
