package ownvk.ruslan.android.myownvk.model.view;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.common.utils.UiHelper;
import ownvk.ruslan.android.myownvk.model.CommentItem;
import ownvk.ruslan.android.myownvk.model.WallItem;
import ownvk.ruslan.android.myownvk.ui.view.holder.BaseViewHolder;

public class OpenedPostHeaderViewModel extends BaseViewModel {

	private int mId;

	private String mProfileName;
	private String mProfilePhoto;

	private String mText;


	public OpenedPostHeaderViewModel(WallItem wallItem) {
		this.mId = wallItem.getId();

		this.mProfileName = wallItem.getSenderName();
		this.mProfilePhoto = wallItem.getSenderPhoto();

		this.mText = wallItem.getText();
	}


	public OpenedPostHeaderViewModel(CommentItem commentItem) {
		this.mId = commentItem.getId();

		this.mProfileName = commentItem.getSenderName();
		this.mProfilePhoto = commentItem.getSenderPhoto();

		this.mText = commentItem.getDisplayText();
	}


	@Override
	public LayoutTypes getType() {
		return LayoutTypes.OpenedPostHeader;
	}


	@Override
	public OpenedPostHeaderHolder onCreateViewHolder(View view) {
		return new OpenedPostHeaderHolder(view);
	}


	public int getId() {
		return mId;
	}

	private String getProfileName() {
		return mProfileName;
	}

	private String getProfilePhoto() {
		return mProfilePhoto;
	}

	public String getText() {
		return mText;
	}

	static class OpenedPostHeaderHolder extends BaseViewHolder<OpenedPostHeaderViewModel> {

		@BindView(R.id.civ_profile_image)
		public CircleImageView profilePhoto;

		@BindView(R.id.tv_profile_name)
		public TextView profileName;

		@BindView(R.id.tv_text)
		public TextView text;


		private OpenedPostHeaderHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}


		@Override
		public void bindViewHolder(OpenedPostHeaderViewModel openedPostHeaderViewModel) {
			Glide.with(itemView.getContext()).load(openedPostHeaderViewModel.getProfilePhoto()).into(profilePhoto);
			profileName.setText(openedPostHeaderViewModel.getProfileName());

			UiHelper.getInstance().setUpTextViewWithVisibility(text, openedPostHeaderViewModel.getText());
		}

		@Override
		public void unbindViewHolder() {
			profilePhoto.setImageBitmap(null);
			profileName.setText(null);

			text.setText(null);
		}

	}
}