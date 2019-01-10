package ownvk.ruslan.android.myownvk;

import android.app.Application;

import com.vk.sdk.VKSdk;

import ownvk.ruslan.android.myownvk.di.ApplicationComponent;
import ownvk.ruslan.android.myownvk.di.ApplicationModule;
import ownvk.ruslan.android.myownvk.di.DaggerApplicationComponent;

public class VkApplication extends Application {

	private static ApplicationComponent sApplicationComponent;

	@Override
	public void onCreate() {
		super.onCreate();

		VKSdk.initialize(this);
		initComponent();
	}

	private void initComponent() {

		sApplicationComponent = DaggerApplicationComponent.builder()

				.applicationModule(new ApplicationModule(this)).build();

	}

	public static ApplicationComponent getApplicationComponent() {

		return sApplicationComponent;

	}
}
