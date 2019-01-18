package ownvk.ruslan.android.myownvk.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ownvk.ruslan.android.myownvk.rest.RestClient;
import ownvk.ruslan.android.myownvk.rest.api.GroupsApi;
import ownvk.ruslan.android.myownvk.rest.api.UserApi;
import ownvk.ruslan.android.myownvk.rest.api.WallApi;

@Module
public class RestModule {
	private RestClient mRestClient;


	public RestModule() {
		mRestClient = new RestClient();
	}


	@Provides
	@Singleton
	public RestClient provideRestClient() {
		return mRestClient;
	}


	@Provides
	@Singleton
	public WallApi provideWallApi() {
		return mRestClient.createService(WallApi.class);

	}
	@Provides
	@Singleton
	public UserApi provideUsersApi() {
		return mRestClient.createService(UserApi.class);
	}

	@Provides
	@Singleton
	public GroupsApi provideGroupsApi() {
		return mRestClient.createService(GroupsApi.class);
	}

}
