package ownvk.ruslan.android.myownvk.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ownvk.ruslan.android.myownvk.CurrentUser;
import ownvk.ruslan.android.myownvk.mvp.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

	public void checkAuth(){
		if (!CurrentUser.isAuthorized()){
			getViewState().startSignIn();
		}else {
			getViewState().signedId();
		}
	}

}
