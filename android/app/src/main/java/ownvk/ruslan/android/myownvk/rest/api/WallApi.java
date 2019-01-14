package ownvk.ruslan.android.myownvk.rest.api;

import java.util.Map;

import ownvk.ruslan.android.myownvk.rest.model.response.GetWallResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WallApi {

	@GET(ApiMethods.WALL_GET)
	Call<GetWallResponse> get(@QueryMap Map<String,String> map);
}
