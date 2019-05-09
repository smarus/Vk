package ownvk.ruslan.android.myownvk.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.utils.VkListHelper;
import ownvk.ruslan.android.myownvk.model.WallItem;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.model.view.NewsItemBodyViewModel;
import ownvk.ruslan.android.myownvk.model.view.NewsItemFooterViewModel;
import ownvk.ruslan.android.myownvk.model.view.NewsItemHeaderViewModel;
import ownvk.ruslan.android.myownvk.mvp.presenter.BaseFeedPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.NewsFeedPresenter;
import ownvk.ruslan.android.myownvk.rest.api.WallApi;
import ownvk.ruslan.android.myownvk.rest.model.request.WallGetRequestModel;
import ownvk.ruslan.android.myownvk.rest.model.response.GetWallResponse;
import ownvk.ruslan.android.myownvk.ui.activity.CreatePostActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFeedFragment {

	@Inject
	WallApi mWallApi;

	@InjectPresenter
	NewsFeedPresenter mPresenter;



	public NewsFeedFragment() {
		// Required empty public constructor
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		VkApplication.getApplicationComponent().inject(this);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onResume() {
		super.onResume();
		getBaseActivity().mFab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent = new Intent(getActivity(), CreatePostActivity.class);
				startActivityForResult(intent, 0);
			}
		});
	}

	@Override
	public boolean needFab() {
		return true;
	}





	@Override
	public int onCreateToolbarTitle() {
		return R.string.screen_name_news;
	}

	@Override
	protected BaseFeedPresenter onCreateFeedPresenter() {
		return mPresenter;
	}
}