package ownvk.ruslan.android.myownvk.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;


import com.arellomobile.mvp.MvpAppCompatActivity;

import javax.inject.Inject;

import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.MyFragmentManager;
import ownvk.ruslan.android.myownvk.ui.fragment.BaseFragment;

public abstract class BaseActivity extends MvpAppCompatActivity {

	@Inject
	MyFragmentManager myFragmentManager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		VkApplication.getApplicationComponent().inject(this);

		setContentView(R.layout.activity_base);


		Toolbar toolbar  = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FrameLayout parent =(FrameLayout)findViewById(R.id.main_wrapper);
		getLayoutInflater().inflate(getMainContentLayout(),parent);

	}

	public void setToolbarTitle(String title){
		if (getSupportActionBar() != null){
			getSupportActionBar().setTitle(title);
		}
	}


	@LayoutRes
	protected abstract int getMainContentLayout();

	public void fragmentOnScreen(BaseFragment fragment){
		setToolbarTitle(fragment.createToolbarTitle(this));

	}

	public void setContent(BaseFragment fragment){
		myFragmentManager.setFragment(this,fragment,R.id.main_wrapper);
	}

	public void addContent(BaseFragment fragment){
		myFragmentManager.addFragment(this,fragment,R.id.main_wrapper);
	}

	public boolean removeCurrentFragment(){
		return myFragmentManager.removeCurrentFragment(this);
	}

	public void  removeFragment(BaseFragment fragment){
		myFragmentManager.removeFragment(this,fragment);
	}

	@Override
	public void onBackPressed() {
		removeCurrentFragment();
	}
}
