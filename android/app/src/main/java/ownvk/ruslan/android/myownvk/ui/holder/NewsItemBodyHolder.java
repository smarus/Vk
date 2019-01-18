package ownvk.ruslan.android.myownvk.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.model.view.NewsItemBodyViewModel;

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

	@BindView(R.id.tv_text)
	public TextView tvText;

	@BindView(R.id.tv_attachments)
	public TextView tvAttachments;


	@Inject
	protected Typeface mFontGoogle;

	public NewsItemBodyHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		VkApplication.getApplicationComponent().inject(this);

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
