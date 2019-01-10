package ownvk.ruslan.android.myownvk.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ownvk.ruslan.android.myownvk.common.MyFragmentManager;

@Module
public class ManagerModule {
	@Provides
	@Singleton
	MyFragmentManager provideMyFragmentManager(){
		return new MyFragmentManager();
	}
}
