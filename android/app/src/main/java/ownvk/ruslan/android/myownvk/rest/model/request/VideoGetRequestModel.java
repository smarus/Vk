package ownvk.ruslan.android.myownvk.rest.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

import ownvk.ruslan.android.myownvk.consts.ApiConstants;

public class VideoGetRequestModel extends BaseRequestModel {

	@SerializedName(ApiConstants.VIDEOS)
	String videos;

	public VideoGetRequestModel() {

	}

	public VideoGetRequestModel(String ownerId, String videoId) {
		this.videos = ownerId + "_" + videoId;
	}

	public VideoGetRequestModel(int ownerId, int videoId) {
		this.videos = ownerId + "_" + videoId;
	}

	public VideoGetRequestModel(String videos) {
		this.videos = videos;
	}


	public String getVideos() {
		return videos;
	}

	public void setVideos(String videos) {
		this.videos = videos;
	}


	@Override
	public void onMapCreate(Map<String, String> map) {
		map.put(ApiConstants.VIDEOS, getVideos());
	}
}
