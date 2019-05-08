package ownvk.ruslan.android.myownvk.model.view.attachment;

import android.view.View;

import ownvk.ruslan.android.myownvk.model.attachment.Page;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.ui.view.holder.BaseViewHolder;
import ownvk.ruslan.android.myownvk.ui.view.holder.PageAttachmentHolder;


public class PageAttachmentViewModel extends BaseViewModel {

	private String mTitle;
	private String mUrl;

	public PageAttachmentViewModel(Page page) {
		mUrl = page.getUrl();
		mTitle = page.getTitle();
	}

	public String getTitle() {
		return mTitle;
	}


	@Override
	public LayoutTypes getType() {
		return LayoutTypes.AttachmentPage;
	}

	@Override
	public PageAttachmentHolder onCreateViewHolder(View view) {
		return new PageAttachmentHolder(view);
	}


	public String getmUrl() {
		return mUrl;
	}
}
