package ownvk.ruslan.android.myownvk.model.view.attachment;

import android.view.View;

import ownvk.ruslan.android.myownvk.model.attachment.Link;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.ui.view.holder.BaseViewHolder;

public class LinkExternalViewModel extends BaseViewModel {

	private String mTitle;
	private String mUrl;

	private String mImageUrl;

	public LinkExternalViewModel(Link link) {

		if (link.getTitle() == null || link.getTitle().equals("")) {
			if (link.getName() != null) {
				mTitle = link.getName();
			} else {
				mTitle = "Link";
			}
		} else {
			mTitle = link.getTitle();
		}

		mUrl = link.getUrl();

		mImageUrl = link.getPhoto().getPhoto604();
	}



	@Override
	public LayoutTypes getType() {
		return LayoutTypes.AttachmentLinkExternal;
	}

	@Override
	protected BaseViewHolder onCreateViewHolder(View view) {
		return null;
	}


	public String getTitle() {
		return mTitle;
	}

	public String getUrl() {
		return mUrl;
	}

	public String getImageUrl() {
		return mImageUrl;
	}
}