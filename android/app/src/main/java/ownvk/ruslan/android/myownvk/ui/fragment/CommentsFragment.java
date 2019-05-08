package ownvk.ruslan.android.myownvk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.model.Place;
import ownvk.ruslan.android.myownvk.mvp.presenter.BaseFeedPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.CommentsPresenter;

public class CommentsFragment extends BaseFeedFragment {

	public static CommentsFragment newInstance(Place place) {

		Bundle args = new Bundle();
		args.putAll(place.toBundle());

		CommentsFragment fragment = new CommentsFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@InjectPresenter
	CommentsPresenter mPresenter;

	Place mPlace;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		VkApplication.getApplicationComponent().inject(this);

		mPlace = new Place(getArguments());
	}



	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

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