package ownvk.ruslan.android.myownvk.ui.view.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.manager.MyFragmentManager;
import ownvk.ruslan.android.myownvk.common.utils.UiHelper;
import ownvk.ruslan.android.myownvk.model.view.NewsItemBodyViewModel;
import ownvk.ruslan.android.myownvk.ui.activity.BaseActivity;
import ownvk.ruslan.android.myownvk.ui.fragment.OpenedPostFragment;

public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

	@BindView(R.id.tv_text)
	public TextView tvText;

	@BindView(R.id.tv_attachments)
	public TextView tvAttachments;

	@Inject
	protected Typeface mFontGoogle;

	@Inject
	MyFragmentManager myFragmentManager;


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
		tvAttachments.setText(item.getAttachmentString());

		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				myFragmentManager.addFragment((BaseActivity) view.getContext(),
						OpenedPostFragment.newInstance(item.getId()),
						R.id.main_wrapper);

			}
		});
		UiHelper.getInstance().setUpTextViewWithVisibility(tvText, item.getText());
		UiHelper.getInstance().setUpTextViewWithVisibility(tvAttachments, item.getAttachmentString());
	}

	@Override
	public void unbindViewHolder() {
		tvText.setText(null);
		tvAttachments.setText(null);
		itemView.setOnClickListener(null);
	}
}