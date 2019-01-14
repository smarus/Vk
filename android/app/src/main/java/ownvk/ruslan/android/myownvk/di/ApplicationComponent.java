package ownvk.ruslan.android.myownvk.di;


import javax.inject.Singleton;

import dagger.Component;
import ownvk.ruslan.android.myownvk.ui.activity.BaseActivity;
import ownvk.ruslan.android.myownvk.ui.activity.MainActivity;
import ownvk.ruslan.android.myownvk.ui.fragment.NewsFeedFragment;
import ownvk.ruslan.android.myownvk.ui.holder.NewsItemBodyHolder;

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {
	void inject(BaseActivity baseActivity);
	void inject(MainActivity mainActivity);
	void inject(NewsFeedFragment fragment);
	void inject(NewsItemBodyHolder holder);
}
