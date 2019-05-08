package ownvk.ruslan.android.myownvk.model.view;

import android.view.View;

import ownvk.ruslan.android.myownvk.model.WallItem;
import ownvk.ruslan.android.myownvk.ui.view.holder.BaseViewHolder;
import ownvk.ruslan.android.myownvk.ui.view.holder.NewsItemHeaderHolder;

public class NewsItemHeaderViewModel extends BaseViewModel{

	private int mId;

	private String mProfilePhoto;
	private String mProfileName;

	private boolean mIsRepost;
	private String mRepostProfileName;

	public NewsItemHeaderViewModel(WallItem wallItem) {
		this.mId = wallItem.getId();

		this.mProfilePhoto = wallItem.getSenderPhoto();
		this.mProfileName = wallItem.getSenderName();

		this.mIsRepost = wallItem.haveSharedRepost();

		if (mIsRepost) {
			this.mRepostProfileName = wallItem.getSharedRepost().getSenderName();
		}

	}

	@Override
	public LayoutTypes getType() {
		return LayoutTypes.NewsFeedItemHeader;
	}

	@Override
	protected BaseViewHolder onCreateViewHolder(View view) {
		return new NewsItemHeaderHolder(view);
	}

	public int getId() {
		return mId;
	}

	public String getProfilePhoto() {
		return mProfilePhoto;
	}

	public String getProfileName() {
		return mProfileName;
	}

	public boolean isRepost() {
		return mIsRepost;
	}

	public String getRepostProfileName() {
		return mRepostProfileName;
	}

	@Override
	public boolean isItemDecorator() {
		return true;
	}
}