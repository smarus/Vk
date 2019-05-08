package ownvk.ruslan.android.myownvk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.manager.MyFragmentManager;
import ownvk.ruslan.android.myownvk.model.Place;
import ownvk.ruslan.android.myownvk.mvp.presenter.BaseFeedPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.TopicCommentsPresenter;

public class TopicCommentsFragment extends BaseFeedFragment {

	public static TopicCommentsFragment newInstance(Place place) {

		Bundle args = new Bundle();
		args.putAll(place.toBundle());

		TopicCommentsFragment fragment = new TopicCommentsFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@InjectPresenter
	TopicCommentsPresenter mPresenter;

	Place mPlace;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		VkApplication.getApplicationComponent().inject(this);
		setWithEndlessList(true);

		mPlace = new Place(getArguments());
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
	}


	@Override
	protected BaseFeedPresenter onCreateFeedPresenter() {
		mPresenter.setPlace(mPlace);
		return mPresenter;
	}

	@Override
	public int onCreateToolbarTitle() {
		return R.string.screen_name_comments;
	}
}