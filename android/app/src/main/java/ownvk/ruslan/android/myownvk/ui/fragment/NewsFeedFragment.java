package ownvk.ruslan.android.myownvk.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.BaseAdapter;
import ownvk.ruslan.android.myownvk.common.utils.VkListHelper;
import ownvk.ruslan.android.myownvk.model.WallItem;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.model.view.NewsItemBodyViewModel;
import ownvk.ruslan.android.myownvk.model.view.NewsItemHeaderViewModel;
import ownvk.ruslan.android.myownvk.rest.api.WallApi;
import ownvk.ruslan.android.myownvk.rest.model.request.WallGetRequestModel;
import ownvk.ruslan.android.myownvk.rest.model.response.GetWallResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFragment {

	@Inject
	WallApi mWallApi;

	RecyclerView mRecyclerView;

	BaseAdapter mBaseAdapter;


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
		mWallApi.get(new WallGetRequestModel(-86529522).toMap()).enqueue(new Callback<GetWallResponse>() {
			@Override
			public void onResponse(Call<GetWallResponse> call, Response<GetWallResponse> response) {
				Toast.makeText(getContext(),""+ response.body().response.items.get(0).getLikes().getCount(),Toast.LENGTH_SHORT).show();
				List<WallItem> wallItems = VkListHelper.getWallList(response.body().response);
				List<BaseViewModel> list = new ArrayList<>();
				for (WallItem item : wallItems) {
					list.add(new NewsItemHeaderViewModel(item));
					list.add(new NewsItemBodyViewModel(item));
				}
				mBaseAdapter.addItems(list);
			}

			@Override
			public void onFailure(Call<GetWallResponse> call, Throwable t) {

			}
		});
	}

	@Override
	protected int getMainContentLayout() {
		return R.layout.fragment_feed;
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setUpRecyclerView(view);
		setUpAdapter(mRecyclerView);

	}

	@Override
	public int onCreateToolbarTitle() {
		return R.string.screen_name_news;
	}

	private void setUpRecyclerView(View rootView){
		mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_list);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
	}

	private void setUpAdapter(RecyclerView recyclerView){
		mBaseAdapter = new BaseAdapter();
		recyclerView.setAdapter(mBaseAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();
	}
}
