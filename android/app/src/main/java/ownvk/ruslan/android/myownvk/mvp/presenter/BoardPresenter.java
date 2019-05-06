package ownvk.ruslan.android.myownvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.consts.ApiConstants;
import ownvk.ruslan.android.myownvk.model.Topic;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.model.view.TopicViewModel;
import ownvk.ruslan.android.myownvk.mvp.view.BaseFeedView;
import ownvk.ruslan.android.myownvk.rest.api.BoardApi;
import ownvk.ruslan.android.myownvk.rest.model.Member;
import ownvk.ruslan.android.myownvk.rest.model.request.BoardGetTopicsRequestModel;

@InjectViewState
public class BoardPresenter extends BaseFeedPresenter<BaseFeedView> {
	@Inject
	BoardApi mBoardApi;

	public BoardPresenter() {
		VkApplication.getApplicationComponent().inject(this);
	}

	@Override
	public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
		return mBoardApi.getTopics(new BoardGetTopicsRequestModel(
				ApiConstants.MY_GROUP_ID, count, offset).toMap())
				.flatMap(baseItemResponseFull -> Observable.fromIterable(baseItemResponseFull.response.getItems()))
				.doOnNext(topic -> topic.setGroupId(ApiConstants.MY_GROUP_ID))
				.doOnNext(this::saveToDb)
				.map(TopicViewModel::new);
	}

	@Override
	public Observable<BaseViewModel> onCreateRestoreDataObservable() {
		return Observable.fromCallable(getListFromRealmCallable())
				.flatMap(Observable::fromIterable)
				.map(TopicViewModel::new);
	}


	public Callable<List<Topic>> getListFromRealmCallable() {
		return () -> {
			String[] sortFields = {Member.ID};
			Sort[] sortOrder = {Sort.DESCENDING};

			Realm realm = Realm.getDefaultInstance();
			RealmResults<Topic> results = realm.where(Topic.class)
					.equalTo("groupId", ApiConstants.MY_GROUP_ID)
					.findAllSorted(sortFields, sortOrder);

			return realm.copyFromRealm(results);
		};
	}
}
