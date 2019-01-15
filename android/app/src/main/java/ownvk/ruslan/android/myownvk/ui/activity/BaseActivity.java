package ownvk.ruslan.android.myownvk.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ProgressBar;


import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.manager.MyFragmentManager;
import ownvk.ruslan.android.myownvk.ui.fragment.BaseFragment;

public abstract class BaseActivity extends MvpAppCompatActivity {


	@Inject
	MyFragmentManager myFragmentManager;

	Toolbar toolbar;

	protected ProgressBar mProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);

		VkApplication.getApplicationComponent().inject(this);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mProgressBar = (ProgressBar) findViewById(R.id.progress);
		FrameLayout parent = (FrameLayout) findViewById(R.id.main_wrapper);
		getLayoutInflater().inflate(getMainContentLayout(), parent);
	}


	@LayoutRes
	protected abstract int getMainContentLayout();


	public void fragmentOnScreen(BaseFragment baseFragment) {
		setToolbarTitle(baseFragment.createToolbarTitle(this));
	}


	private void setToolbarTitle(String title) {
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(title);
		}
	}


	public void setContent(BaseFragment fragment) {
		myFragmentManager.setFragment(this, fragment, R.id.main_wrapper);
	}

	public void addContent(BaseFragment fragment) {
		myFragmentManager.addFragment(this, fragment, R.id.main_wrapper);
	}

	public boolean removeCurrentFragment() {
		return myFragmentManager.removeCurrentFragment(this);
	}

	public boolean removeFragment(BaseFragment fragment) {
		return myFragmentManager.removeFragment(this, fragment);
	}


	@Override
	public void onBackPressed() {
		removeCurrentFragment();
	}

	public ProgressBar getProgressBar() {
		return mProgressBar;
	}
}