package ownvk.ruslan.android.myownvk.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.mvp.presenter.BaseFeedPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.BoardPresenter;

public class BoardFragment extends BaseFeedFragment {
	@InjectPresenter
	BoardPresenter mPresenter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
	}

	@Override
	protected BaseFeedPresenter onCreateFeedPresenter() {
		return mPresenter;
	}

	@Override
	public int onCreateToolbarTitle() {
		return R.string.screen_name_topics;
	}
}
