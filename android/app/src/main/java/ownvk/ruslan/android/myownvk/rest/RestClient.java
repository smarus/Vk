package ownvk.ruslan.android.myownvk.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
	private static final String VK_BASE_URL = "https://api.vk.com/method/";

	private Retrofit mRetrofit;

	public RestClient() {

		mRetrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(VK_BASE_URL)
				.client(getLogging())
				.build();
	}

	public <T> T createService(Class<T> serviceClass) {
		return mRetrofit.create(serviceClass);
	}


	public OkHttpClient getLogging(){
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(logging)
				.build();
		return client;
	}
}
