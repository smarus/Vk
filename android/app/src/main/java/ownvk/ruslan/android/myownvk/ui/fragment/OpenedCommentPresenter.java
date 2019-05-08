package ownvk.ruslan.android.myownvk.ui.fragment;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.realm.Realm;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.utils.VkListHelper;
import ownvk.ruslan.android.myownvk.model.CommentItem;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.model.view.CommentFooterViewModel;
import ownvk.ruslan.android.myownvk.model.view.OpenedPostHeaderViewModel;
import ownvk.ruslan.android.myownvk.mvp.presenter.BaseFeedPresenter;
import ownvk.ruslan.android.myownvk.mvp.view.BaseFeedView;

@InjectViewState
public class OpenedCommentPresenter extends BaseFeedPresenter<BaseFeedView> {

	int id;

	public OpenedCommentPresenter() {
		VkApplication.getApplicationComponent().inject(this);

	}

	@Override
	public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
		return createObservable();

	}

	@Override
	public Observable<BaseViewModel> onCreateRestoreDataObservable() {
		return createObservable();
	}


	private Observable<BaseViewModel> createObservable() {
		return Observable.fromCallable(getListFromRealmCallable())
				.retry(2)
				.flatMap(commentItem -> {
					List<BaseViewModel> list = new ArrayList<>();
					List<BaseViewModel> forwardedList = new ArrayList<>();

					list.add(new OpenedPostHeaderViewModel(commentItem));

					list.addAll(VkListHelper.getAttachmentVhItems(commentItem.getAttachments()));

					list.add(new CommentFooterViewModel(commentItem));

					return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
				});
	}



	public Callable<CommentItem> getListFromRealmCallable() {
		return () -> {
			Realm realm = Realm.getDefaultInstance();
			CommentItem commentItem = realm.where(CommentItem.class).equalTo("id", id).findFirst();

			return realm.copyFromRealm(commentItem);
		};
	}

	public void setId(int id) {
		this.id = id;
	}
}