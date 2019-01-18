package ownvk.ruslan.android.myownvk.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import ownvk.ruslan.android.myownvk.rest.model.Member;
import ownvk.ruslan.android.myownvk.rest.model.response.BaseItemResponse;
import ownvk.ruslan.android.myownvk.rest.model.response.Full;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GroupsApi {

	@GET(ApiMethods.GROUPS_GET_MEMBERS)
	Observable<Full<BaseItemResponse<Member>>> getMembers(@QueryMap Map<String, String> map);

}
