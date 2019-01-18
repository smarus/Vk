package ownvk.ruslan.android.myownvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;
import ownvk.ruslan.android.myownvk.CurrentUser;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.manager.MyFragmentManager;
import ownvk.ruslan.android.myownvk.common.manager.NetworkManager;
import ownvk.ruslan.android.myownvk.model.Profile;
import ownvk.ruslan.android.myownvk.mvp.view.MainView;
import ownvk.ruslan.android.myownvk.rest.api.UserApi;
import ownvk.ruslan.android.myownvk.rest.model.request.UsersGetRequestModel;
import ownvk.ruslan.android.myownvk.ui.fragment.BaseFragment;
import ownvk.ruslan.android.myownvk.ui.fragment.MembersFragment;
import ownvk.ruslan.android.myownvk.ui.fragment.MyPostsFragment;
import ownvk.ruslan.android.myownvk.ui.fragment.NewsFeedFragment;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

	@Inject
	UserApi mUserApi;

	@Inject
	NetworkManager mNetworkManager;

	@Inject
	MyFragmentManager myFragmentManager;


	public MainPresenter(){
		VkApplication.getApplicationComponent().inject(this);

	}

	public void checkAuth() {
		if (!CurrentUser.isAuthorized()) {
			getViewState().startSignIn();
		} else {
			getCurrentUser();
			getViewState().signedId();
		}
	}

	public Observable<Profile> getProfileFromNetwork() {
		return mUserApi.get(new UsersGetRequestModel(CurrentUser.getId()).toMap())
				.flatMap(listFull -> Observable.fromIterable(listFull.response))
				.doOnNext(this::saveToDb);
	}

	private Observable<Profile> getProfileFromDb() {
		return Observable.fromCallable(getListFromRealmCallable());
	}

	public void saveToDb(RealmObject item) {
		Realm realm = Realm.getDefaultInstance();
		realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
	}

	public Callable<Profile> getListFromRealmCallable() {
		return () -> {
			Realm realm = Realm.getDefaultInstance();
			Profile realmResults = realm.where(Profile.class)
					.equalTo("id", Integer.parseInt(CurrentUser.getId()))
					.findFirst();
			return realm.copyFromRealm(realmResults);
		};
	}

	private void getCurrentUser() {
		mNetworkManager.getNetworkObservable()
				.flatMap(aBoolean -> {
					if (!CurrentUser.isAuthorized()) {
						getViewState().startSignIn();
					}

					return aBoolean
							? getProfileFromNetwork()
							: getProfileFromDb();
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(profile -> {
					getViewState().showCurrentUser(profile);
				}, error -> {
					error.printStackTrace();
				});
	}

	public void drawerItemClick(int id) {
		BaseFragment fragment = null;

		switch (id) {
			case 1:
				fragment = new NewsFeedFragment();
				break;
			case 2:
				fragment = new MyPostsFragment();
				break;
			case 4:
				fragment = new MembersFragment();
				break;
		}

		if (fragment != null && !myFragmentManager.isAlreadyContains(fragment)) {
			getViewState().showFragmentFromDrawer(fragment);
		}
	}



}
