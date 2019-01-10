package ownvk.ruslan.android.myownvk.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import javax.inject.Inject;

import ownvk.ruslan.android.myownvk.CurrentUser;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.rest.api.WallApi;
import ownvk.ruslan.android.myownvk.rest.model.BaseItemResponse;
import ownvk.ruslan.android.myownvk.rest.model.Full;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFragment {

	@Inject
	WallApi mWallApi;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		VkApplication.getApplicationComponent().inject(this);

	}

	public NewsFeedFragment() {
		// Required empty public constructor
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mWallApi.get("-86529522", CurrentUser.getAccessToken(), 1, "5.67").enqueue(new Callback<Full<BaseItemResponse>>() {
			@Override
			public void onResponse(Call<Full<BaseItemResponse>> call, Response<Full<BaseItemResponse>> response) {
				Toast.makeText(getActivity(), "Count: " + response.body().response.getCount(), Toast.LENGTH_LONG).show();
			}

			@Override
			public void onFailure(Call<Full<BaseItemResponse>> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}

	@Override
	protected int getMainContentLayout() {
		return R.layout.fragment_feed;
	}



	@Override
	public int onCreateToolbarTitle() {
		return R.string.screen_name_news;
	}

}
