package ownvk.ruslan.android.myownvk.di;


import javax.inject.Singleton;

import dagger.Component;
import ownvk.ruslan.android.myownvk.common.manager.NetworkManager;
import ownvk.ruslan.android.myownvk.mvp.presenter.BoardPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.InfoPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.MainPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.MembersPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.NewsFeedPresenter;
import ownvk.ruslan.android.myownvk.ui.activity.BaseActivity;
import ownvk.ruslan.android.myownvk.ui.activity.MainActivity;
import ownvk.ruslan.android.myownvk.ui.fragment.NewsFeedFragment;
import ownvk.ruslan.android.myownvk.ui.holder.NewsItemBodyHolder;
import ownvk.ruslan.android.myownvk.ui.holder.NewsItemFooterHolder;

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {
	void inject(BaseActivity baseActivity);
	void inject(MainActivity mainActivity);
	void inject(NewsFeedFragment fragment);
	void inject(NewsItemBodyHolder holder);
	void inject(NewsItemFooterHolder holder);
	void inject(NewsFeedPresenter presenter);
	void inject(NetworkManager manager);
	void inject(MainPresenter presenter);
	void inject(MembersPresenter presenter);
	void inject(BoardPresenter presenter);
	void inject(InfoPresenter presenter);


}
