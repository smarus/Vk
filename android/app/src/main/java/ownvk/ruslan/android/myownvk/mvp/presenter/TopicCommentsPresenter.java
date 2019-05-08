package ownvk.ruslan.android.myownvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.utils.VkListHelper;
import ownvk.ruslan.android.myownvk.model.CommentItem;
import ownvk.ruslan.android.myownvk.model.Place;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.model.view.CommentBodyViewModel;
import ownvk.ruslan.android.myownvk.model.view.CommentFooterViewModel;
import ownvk.ruslan.android.myownvk.model.view.CommentHeaderViewModel;
import ownvk.ruslan.android.myownvk.mvp.view.BaseFeedView;
import ownvk.ruslan.android.myownvk.rest.api.BoardApi;
import ownvk.ruslan.android.myownvk.rest.model.request.BoardGetCommentsRequestModel;

@InjectViewState
public class TopicCommentsPresenter extends BaseFeedPresenter<BaseFeedView> {
	private Place mPlace;

	@Inject
	BoardApi mBoardApi;


	public TopicCommentsPresenter() {
		VkApplication.getApplicationComponent().inject(this);
	}

	@Override
	public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
		return mBoardApi.getComments(new BoardGetCommentsRequestModel(
				Integer.parseInt(mPlace.getOwnerId()), Integer.parseInt(mPlace.getPostId()), offset).toMap())
				.flatMap(full -> Observable.fromIterable(VkListHelper.getCommentsList(full.response, true)))
				.doOnNext(commentItem -> commentItem.setPlace(mPlace))
				.doOnNext(this::saveToDb)
				.flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
	}

	@Override
	public Observable<BaseViewModel> onCreateRestoreDataObservable() {
		return Observable.fromCallable(getListFromRealmCallable())
				.flatMap(Observable::fromIterable)
				.filter(commentItem -> commentItem.getPlace().equals(this.mPlace) && commentItem.isFromTopic)
				.flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
	}


	private List<BaseViewModel> parsePojoModel(CommentItem commentItem) {
		List<BaseViewModel> baseItems = new ArrayList<>();
		baseItems.add(new CommentHeaderViewModel(commentItem));
		baseItems.add(new CommentBodyViewModel(commentItem));
		baseItems.add(new CommentFooterViewModel(commentItem));
		return baseItems;
	}


	public Callable<List<CommentItem>> getListFromRealmCallable() {
		return () -> {
			String[] sortFields = {"id"};
			Sort[] sortOrder = {Sort.ASCENDING};

			Realm realm = Realm.getDefaultInstance();
			RealmResults<CommentItem> results = realm.where(CommentItem.class)
					.findAllSorted(sortFields, sortOrder);
			return realm.copyFromRealm(results);
		};
	}


	public void setPlace(Place place) {
		this.mPlace = place;
	}
}