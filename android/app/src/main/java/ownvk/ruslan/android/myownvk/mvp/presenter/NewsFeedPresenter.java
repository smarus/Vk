package ownvk.ruslan.android.myownvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import ownvk.ruslan.android.myownvk.CurrentUser;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.utils.VkListHelper;
import ownvk.ruslan.android.myownvk.consts.ApiConstants;
import ownvk.ruslan.android.myownvk.model.WallItem;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.model.view.NewsItemBodyViewModel;
import ownvk.ruslan.android.myownvk.model.view.NewsItemFooterViewModel;
import ownvk.ruslan.android.myownvk.model.view.NewsItemHeaderViewModel;
import ownvk.ruslan.android.myownvk.mvp.view.BaseFeedView;
import ownvk.ruslan.android.myownvk.rest.api.WallApi;
import ownvk.ruslan.android.myownvk.rest.model.request.WallGetRequestModel;

@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView> {

	@Inject
	WallApi mWallApi;

	private boolean enableIdFiltering = false;


	public NewsFeedPresenter() {
		VkApplication.getApplicationComponent().inject(this);
	}

	@Override
	public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
		return mWallApi.get(new WallGetRequestModel(ApiConstants.MY_GROUP_ID, count, offset).toMap())
				.flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
				.compose(applyFilter())
				.doOnNext(this::saveToDb)
				.flatMap(wallItem -> {
					List<BaseViewModel> baseItems = new ArrayList<>();
					baseItems.add(new NewsItemHeaderViewModel(wallItem));
					baseItems.add(new NewsItemBodyViewModel(wallItem));
					baseItems.add(new NewsItemFooterViewModel(wallItem));
					return Observable.fromIterable(baseItems);
				});
	}

	@Override
	public Observable<BaseViewModel> onCreateRestoreDataObservable() {
		return Observable.fromCallable(getListFromRealmCallable())
				.flatMap(Observable::fromIterable)
				.compose(applyFilter())
				.flatMap(wallItem -> Observable.fromIterable(parsePojoModel(wallItem)));
	}

	private List<BaseViewModel> parsePojoModel(WallItem wallItem) {
		List<BaseViewModel> baseItems = new ArrayList<>();
		baseItems.add(new NewsItemHeaderViewModel(wallItem));
		baseItems.add(new NewsItemBodyViewModel(wallItem));
		baseItems.add(new NewsItemFooterViewModel(wallItem));
		return baseItems;
	}

	public Callable<List<WallItem>> getListFromRealmCallable() {
		return () -> {
			String[] sortFields = {"date"};
			Sort[] sortOrder = {Sort.DESCENDING};
			Realm realm = Realm.getDefaultInstance();
			RealmResults<WallItem> realmResults = realm.where(WallItem.class)
					.findAllSorted(sortFields, sortOrder);
			return realm.copyFromRealm(realmResults);
		};
	}

	public void setEnableIdFiltering(boolean enableIdFiltering) {
		this.enableIdFiltering = enableIdFiltering;
	}

	protected ObservableTransformer<WallItem, WallItem> applyFilter() {
		if (enableIdFiltering && CurrentUser.getId() != null) {
			return baseItemObservable -> baseItemObservable.
					filter(wallItem -> CurrentUser.getId().equals(String.valueOf(wallItem.getFromId())));
		} else {
			return baseItemObservable -> baseItemObservable;
		}
	}
}
