package ownvk.ruslan.android.myownvk.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ownvk.ruslan.android.myownvk.common.manager.MyFragmentManager;
import ownvk.ruslan.android.myownvk.common.manager.NetworkManager;

@Module
public class ManagerModule {
	@Provides
	@Singleton
	MyFragmentManager provideMyFragmentManager(){
		return new MyFragmentManager();
	}

	@Provides
	@Singleton
	NetworkManager provideNetworkManager() {
		return new NetworkManager();
	}
}
