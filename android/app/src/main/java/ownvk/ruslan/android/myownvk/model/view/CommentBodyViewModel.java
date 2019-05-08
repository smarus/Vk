package ownvk.ruslan.android.myownvk.model.view;

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
import ownvk.ruslan.android.myownvk.model.CommentItem;
import ownvk.ruslan.android.myownvk.ui.activity.BaseActivity;
import ownvk.ruslan.android.myownvk.ui.fragment.OpenedCommentFragment;
import ownvk.ruslan.android.myownvk.ui.view.holder.BaseViewHolder;


public class CommentBodyViewModel extends BaseViewModel {

	private int mId;
	private String mText;
	private String mAttachmentsString;

	public CommentBodyViewModel(CommentItem commentItem) {
		this.mId = commentItem.getId();
		this.mText = commentItem.getDisplayText();
		this.mAttachmentsString = commentItem.getDisplayAttachmentsString();
	}

	@Override
	public boolean isItemDecorator() {
		return true;
	}

	@Override
	public LayoutTypes getType() {
		return LayoutTypes.CommentBody;
	}

	@Override
	protected BaseViewHolder onCreateViewHolder(View view) {
		return new CommentBodyViewHolder(view);
	}

	public int getId() {
		return mId;
	}

	public String getText() {
		return mText;
	}

	public String getAttachmentsString() {
		return mAttachmentsString;
	}

	public static class CommentBodyViewHolder extends BaseViewHolder<CommentBodyViewModel> {

		@Inject
		Typeface mGoogleFont;

		@Inject
		MyFragmentManager mFragmentManager;

		@BindView(R.id.tv_text)
		public TextView tvText;

		@BindView(R.id.tv_attachments)
		public TextView tvAttachments;

		public CommentBodyViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			VkApplication.getApplicationComponent().inject(this);
			tvAttachments.setTypeface(mGoogleFont);


		}

		@Override
		public void bindViewHolder(CommentBodyViewModel commentBodyViewModel) {

			UiHelper.getInstance().setUpTextViewWithMessage(tvText, commentBodyViewModel.getText(), "");
			UiHelper.getInstance().setUpTextViewWithVisibility(tvAttachments, commentBodyViewModel.getAttachmentsString());

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					mFragmentManager.addFragment((BaseActivity) itemView.getContext(),
							OpenedCommentFragment.newInstance(commentBodyViewModel.getId()), R.id.main_wrapper);
				}
			});
		}

		@Override
		public void unbindViewHolder() {
			tvText.setText(null);
			tvAttachments.setText(null);
		}
	}
}
