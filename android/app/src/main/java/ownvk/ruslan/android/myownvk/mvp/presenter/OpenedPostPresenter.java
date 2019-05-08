package ownvk.ruslan.android.myownvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.utils.VkListHelper;
import ownvk.ruslan.android.myownvk.consts.ApiConstants;
import ownvk.ruslan.android.myownvk.model.WallItem;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.model.view.NewsItemFooterViewModel;
import ownvk.ruslan.android.myownvk.model.view.OpenedPostHeaderViewModel;
import ownvk.ruslan.android.myownvk.model.view.OpenedPostRepostHeaderViewModel;
import ownvk.ruslan.android.myownvk.mvp.view.OpenedPostView;
import ownvk.ruslan.android.myownvk.rest.api.WallApi;
import ownvk.ruslan.android.myownvk.rest.model.request.WallGetByIdRequestModel;

@InjectViewState
public class OpenedPostPresenter extends BaseFeedPresenter<OpenedPostView> {

	private int id;

	@Inject
	WallApi mWallApi;

	public OpenedPostPresenter() {
		VkApplication.getApplicationComponent().inject(this);

	}

	@Override
	public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
		return mWallApi.getById(new WallGetByIdRequestModel(ApiConstants.MY_GROUP_ID, id).toMap())
				.flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
				.observeOn(AndroidSchedulers.mainThread())
				.doOnNext(wallItem -> {
					NewsItemFooterViewModel newsItemFooterViewModel = new NewsItemFooterViewModel(wallItem);

					getViewState().setFooter(newsItemFooterViewModel);
				})

				.observeOn(Schedulers.io())
				.doOnNext(this::saveToDb)
				.flatMap(wallItem -> {
					List<BaseViewModel> list = new ArrayList<>();
					List<BaseViewModel> forwardedList = new ArrayList<>();

					list.add(new OpenedPostHeaderViewModel(wallItem));

					list.addAll(VkListHelper.getAttachmentVhItems(wallItem.getAttachments()));
					if (wallItem.haveSharedRepost()) {

						forwardedList.add(new OpenedPostRepostHeaderViewModel(wallItem.getSharedRepost()));
						forwardedList.addAll(VkListHelper.getAttachmentVhItems(wallItem.getSharedRepost().getAttachments()));
					}
					return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
				});

	}

	@Override
	public Observable<BaseViewModel> onCreateRestoreDataObservable() {
		return Observable.fromCallable(getListFromRealmCallable())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnNext(wallItem -> {
					NewsItemFooterViewModel newsItemFooterViewModel = new NewsItemFooterViewModel(wallItem);

					getViewState().setFooter(newsItemFooterViewModel);
				})

				.observeOn(Schedulers.io())
				.flatMap(wallItem -> {
					List<BaseViewModel> list = new ArrayList<>();
					List<BaseViewModel> forwardedList = new ArrayList<>();

					list.add(new OpenedPostHeaderViewModel(wallItem));

					list.addAll(VkListHelper.getAttachmentVhItems(wallItem.getAttachments()));
					if (wallItem.haveSharedRepost()) {

						forwardedList.add(new OpenedPostRepostHeaderViewModel(wallItem.getSharedRepost()));
						forwardedList.addAll(VkListHelper.getAttachmentVhItems(wallItem.getSharedRepost().getAttachments()));
					}
					return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
				});
	}






	private Callable<WallItem> getListFromRealmCallable() {
		return () -> {
			Realm realm = Realm.getDefaultInstance();
			WallItem wallItem = realm.where(WallItem.class).equalTo("id", id).findFirst();

			return realm.copyFromRealm(wallItem);
		};
	}

	public void setId(int id) {
		this.id = id;
	}
}