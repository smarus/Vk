package ownvk.ruslan.android.myownvk.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import ownvk.ruslan.android.myownvk.rest.model.response.Full;
import ownvk.ruslan.android.myownvk.rest.model.response.VideosResponse;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface VideoApi {
	@GET(ApiMethods.VIDEO_GET)
	Observable<Full<VideosResponse>> get(@QueryMap Map<String, String> map);
}