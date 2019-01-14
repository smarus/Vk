package ownvk.ruslan.android.myownvk.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.model.view.NewsItemBodyViewModel;

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

	public TextView tvText;
	private TextView tvAttachments;

	@Inject
	protected Typeface mFontGoogle;


	public NewsItemBodyHolder(View itemView) {
		super(itemView);

		VkApplication.getApplicationComponent().inject(this);

		tvText = (TextView) itemView.findViewById(R.id.tv_text);

		tvAttachments = (TextView) itemView.findViewById(R.id.tv_attachments);

		if (tvAttachments != null) {
			tvAttachments.setTypeface(mFontGoogle);
		}
	}

	@Override
	public void bindViewHolder(NewsItemBodyViewModel item) {
		tvText.setText(item.getText());
		tvAttachments.setText(item.getmAttachmentString());

	}

	@Override
	public void unbindViewHolder() {
		tvText.setText(null);
		tvAttachments.setText(null);

	}
}
