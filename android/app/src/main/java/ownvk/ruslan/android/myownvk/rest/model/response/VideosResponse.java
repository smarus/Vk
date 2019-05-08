package ownvk.ruslan.android.myownvk.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ownvk.ruslan.android.myownvk.model.attachment.video.Video;

public class VideosResponse {
	public int count;
	@SerializedName("items")
	@Expose
	public List<Video> items;
}