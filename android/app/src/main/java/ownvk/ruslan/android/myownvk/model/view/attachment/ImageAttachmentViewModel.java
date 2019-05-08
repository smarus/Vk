package ownvk.ruslan.android.myownvk.model.view.attachment;

import android.view.View;

import ownvk.ruslan.android.myownvk.model.attachment.Photo;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.ui.view.attachment.ImageAttachmentHolder;
import ownvk.ruslan.android.myownvk.ui.view.holder.BaseViewHolder;

public class ImageAttachmentViewModel extends BaseViewModel {

	private String mPhotoUrl;
	public boolean needClick = true;

	public ImageAttachmentViewModel(String mPhotoUrl) {
		this.mPhotoUrl = mPhotoUrl;
		needClick = false;
	}

	public ImageAttachmentViewModel(Photo photo) {
		mPhotoUrl = photo.getPhoto604();
	}

	@Override
	public LayoutTypes getType() {
		return LayoutTypes.AttachmentImage;
	}

	@Override
	protected ImageAttachmentHolder onCreateViewHolder(View view) {
		return new ImageAttachmentHolder(view);
	}

	public String getPhotoUrl() {
		return mPhotoUrl;
	}
}
