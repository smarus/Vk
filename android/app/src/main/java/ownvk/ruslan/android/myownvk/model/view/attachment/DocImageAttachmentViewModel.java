package ownvk.ruslan.android.myownvk.model.view.attachment;

import android.view.View;

import java.util.List;

import ownvk.ruslan.android.myownvk.common.utils.Utils;
import ownvk.ruslan.android.myownvk.model.attachment.doc.Doc;
import ownvk.ruslan.android.myownvk.model.attachment.doc.Size;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.ui.view.attachment.DocImageAttachmentHolder;
import ownvk.ruslan.android.myownvk.ui.view.holder.BaseViewHolder;

public class DocImageAttachmentViewModel extends BaseViewModel {


	private String mTitle;
	private String mSize;
	private String mExt;

	private String mImage;

	private String mUrl;


	public DocImageAttachmentViewModel(Doc doc) {
		if (doc.getTitle().equals("")) {
			mTitle = "Document";
		} else {
			mTitle = Utils.removeExtFromText(doc.getTitle());
		}

		mUrl = doc.getUrl();

		mSize = Utils.formatSize(doc.getSize());

		mExt = "." + doc.getExt();

		List<Size> sizes = doc.getPreview().getPhoto().getSizes();
		mImage = sizes.get(sizes.size() - 1).getSrc();
	}




	@Override
	public LayoutTypes getType() {
		return LayoutTypes.AttachmentDocImage;
	}

	@Override
	public DocImageAttachmentHolder onCreateViewHolder(View view) {
		return new DocImageAttachmentHolder(view);
	}


	public String getmUrl() {
		return mUrl;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getSize() {
		return mSize;
	}

	public String getExt() {
		return mExt;
	}

	public String getImage() {
		return mImage;
	}
}
