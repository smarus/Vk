package ownvk.ruslan.android.myownvk.mvp.view;

import com.arellomobile.mvp.MvpView;

import ownvk.ruslan.android.myownvk.model.Profile;
import ownvk.ruslan.android.myownvk.ui.fragment.BaseFragment;

public interface MainView extends MvpView {
	void startSignIn();
	void signedId();
	void showCurrentUser(Profile profile);
	void showFragmentFromDrawer(BaseFragment baseFragment);
}
