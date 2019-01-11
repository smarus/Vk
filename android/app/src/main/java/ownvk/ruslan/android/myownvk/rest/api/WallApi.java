package ownvk.ruslan.android.myownvk.rest.api;

import java.util.Map;

import ownvk.ruslan.android.myownvk.rest.model.response.WallGetResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WallApi {

	@GET(ApiMethods.WALL_GET)
	Call<WallGetResponse> get(@QueryMap Map<String,String> map);
}
