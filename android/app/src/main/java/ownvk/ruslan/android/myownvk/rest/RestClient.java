package ownvk.ruslan.android.myownvk.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
	private static final String VK_BASE_URL = "https://api.vk.com/method/";

	private Retrofit mRetrofit;

	public RestClient() {

		mRetrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(VK_BASE_URL)
				.build();
	}

	public <T> T createService(Class<T> serviceClass) {
		return mRetrofit.create(serviceClass);
	}
}
