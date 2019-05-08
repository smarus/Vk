package ownvk.ruslan.android.myownvk.mvp.view;

import com.arellomobile.mvp.MvpView;

import ownvk.ruslan.android.myownvk.model.WallItem;
import ownvk.ruslan.android.myownvk.model.view.counter.LikeCounterViewModel;

public interface PostFooterView extends MvpView {
	void like(LikeCounterViewModel likes);
	void openComments(WallItem wallItem);
}
