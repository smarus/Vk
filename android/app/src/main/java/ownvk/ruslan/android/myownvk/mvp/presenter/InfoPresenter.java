package ownvk.ruslan.android.myownvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.consts.ApiConstants;
import ownvk.ruslan.android.myownvk.model.Group;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.model.view.InfoContactsViewModel;
import ownvk.ruslan.android.myownvk.model.view.InfoLinksViewModel;
import ownvk.ruslan.android.myownvk.model.view.InfoStatusViewModel;
import ownvk.ruslan.android.myownvk.mvp.view.BaseFeedView;
import ownvk.ruslan.android.myownvk.rest.api.GroupsApi;
import ownvk.ruslan.android.myownvk.rest.model.request.GroupsGetByIdRequestModel;

@InjectViewState
public class InfoPresenter extends BaseFeedPresenter<BaseFeedView> {

	@Inject
	GroupsApi mGroupApi;

	public InfoPresenter() {
		VkApplication.getApplicationComponent().inject(this);
	}

	@Override
	public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {

		return mGroupApi.getById(new GroupsGetByIdRequestModel(ApiConstants.MY_GROUP_ID).toMap())
				.flatMap(listFull -> Observable.fromIterable(listFull.response))
				.doOnNext(this::saveToDb)
				.flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
	}

	@Override
	public Observable<BaseViewModel> onCreateRestoreDataObservable() {
		return Observable.fromCallable(getListFromRealmCallable())
				.flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
	}


	private List<BaseViewModel> parsePojoModel(Group group) {
		List<BaseViewModel> items = new ArrayList<>();
		items.add(new InfoStatusViewModel(group));
		items.add(new InfoContactsViewModel());
		items.add(new InfoLinksViewModel());

		return items;
	}


	public Callable<Group> getListFromRealmCallable() {
		return () -> {
			Realm realm = Realm.getDefaultInstance();
			Group result = realm.where(Group.class)
					.equalTo("id", Math.abs(ApiConstants.MY_GROUP_ID))
					.findFirst();
			return realm.copyFromRealm(result);
		};
	}
}