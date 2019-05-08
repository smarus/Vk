package ownvk.ruslan.android.myownvk.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import ownvk.ruslan.android.myownvk.model.CommentItem;
import ownvk.ruslan.android.myownvk.rest.model.response.Full;
import ownvk.ruslan.android.myownvk.rest.model.response.GetWallByIdResponse;
import ownvk.ruslan.android.myownvk.rest.model.response.GetWallResponse;
import ownvk.ruslan.android.myownvk.rest.model.response.ItemWithSendersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WallApi {

	@GET(ApiMethods.WALL_GET)
	Observable<GetWallResponse> get(@QueryMap Map<String, String> map);

	@GET(ApiMethods.WALL_GET_BY_ID)
	Observable<GetWallByIdResponse> getById(@QueryMap Map<String, String> map);


	@GET(ApiMethods.WALL_GET_COMMENTS)
	Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
