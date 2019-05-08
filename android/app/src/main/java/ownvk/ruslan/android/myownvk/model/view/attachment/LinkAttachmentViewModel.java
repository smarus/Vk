package ownvk.ruslan.android.myownvk.model.view.attachment;

import android.view.View;

import ownvk.ruslan.android.myownvk.model.attachment.Link;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.ui.view.holder.BaseViewHolder;
import ownvk.ruslan.android.myownvk.ui.view.holder.LinkAttachmentViewHolder;

public class LinkAttachmentViewModel extends BaseViewModel {

	private String mTitle;
	private String mUrl;

	public LinkAttachmentViewModel(Link link) {

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
	}


	@Override
	public LayoutTypes getType() {
		return LayoutTypes.AttachmentLink;
	}

	@Override
	public LinkAttachmentViewHolder onCreateViewHolder(View view) {
		return new LinkAttachmentViewHolder(view);
	}


	public String getTitle() {
		return mTitle;
	}

	public String getUrl() {
		return mUrl;
	}
}