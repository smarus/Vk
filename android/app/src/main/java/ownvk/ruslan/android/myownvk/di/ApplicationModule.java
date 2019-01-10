package ownvk.ruslan.android.myownvk.di;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
	private Application mApplication;

	public ApplicationModule(Application application){
		mApplication = application;
	}


	@Provides
	@Singleton
	public Context provideContext(){
		return mApplication;
	}

	@Provides
	@Singleton
	LayoutInflater providelayoutInflater(){
		return (LayoutInflater) mApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
}
