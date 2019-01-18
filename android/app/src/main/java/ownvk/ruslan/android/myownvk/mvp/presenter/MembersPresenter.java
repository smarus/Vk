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
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.model.view.MemberViewModel;
import ownvk.ruslan.android.myownvk.mvp.view.BaseFeedView;
import ownvk.ruslan.android.myownvk.rest.api.GroupsApi;
import ownvk.ruslan.android.myownvk.rest.model.Member;
import ownvk.ruslan.android.myownvk.rest.model.request.GroupsGetMembersRequestModel;

@InjectViewState
public class MembersPresenter extends BaseFeedPresenter<BaseFeedView> {
	@Inject
	GroupsApi mGroupApi;

	public MembersPresenter() {
		VkApplication.getApplicationComponent().inject(this);
	}

	@Override
	public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
		return mGroupApi.getMembers(new GroupsGetMembersRequestModel(
				ApiConstants.MY_GROUP_ID, count, offset).toMap())
				.flatMap(baseItemResponseFull -> {
					return Observable.fromIterable(baseItemResponseFull.response.getItems());
				})
				.doOnNext(member -> saveToDb(member))
				.map(member -> new MemberViewModel(member));
	}

	@Override
	public Observable<BaseViewModel> onCreateRestoreDataObservable() {
		return Observable.fromCallable(getListFromRealmCallable())
				.flatMap(Observable::fromIterable)
				.map(member -> new MemberViewModel(member));
	}



	public Callable<List<Member>> getListFromRealmCallable() {
		return () -> {
			String[] sortFields = {Member.ID};
			Sort[] sortOrder = {Sort.ASCENDING};

			Realm realm = Realm.getDefaultInstance();
			RealmResults<Member> results = realm.where(Member.class)
					.findAllSorted(sortFields, sortOrder);
			return realm.copyFromRealm(results);
		};
	}
}