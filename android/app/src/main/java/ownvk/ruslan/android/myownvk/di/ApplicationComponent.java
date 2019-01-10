package ownvk.ruslan.android.myownvk.di;


import javax.inject.Singleton;

import dagger.Component;
import ownvk.ruslan.android.myownvk.ui.activity.BaseActivity;
import ownvk.ruslan.android.myownvk.ui.activity.MainActivity;

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {
	void inject(BaseActivity baseActivity);
	void inject(MainActivity mainActivity);
}
