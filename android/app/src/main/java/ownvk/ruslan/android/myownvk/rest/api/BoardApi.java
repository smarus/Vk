package ownvk.ruslan.android.myownvk.rest.api;

import java.util.Map;

import io.reactivex.Observable;
import ownvk.ruslan.android.myownvk.model.CommentItem;
import ownvk.ruslan.android.myownvk.model.Topic;
import ownvk.ruslan.android.myownvk.rest.model.response.BaseItemResponse;
import ownvk.ruslan.android.myownvk.rest.model.response.Full;
import ownvk.ruslan.android.myownvk.rest.model.response.ItemWithSendersResponse;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface BoardApi {
	@GET(ApiMethods.BOARD_GET_TOPICS)
	Observable<Full<BaseItemResponse<Topic>>> getTopics(@QueryMap Map<String, String> map);


	@GET(ApiMethods.BOARD_GET_COMMENTS)
	Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
