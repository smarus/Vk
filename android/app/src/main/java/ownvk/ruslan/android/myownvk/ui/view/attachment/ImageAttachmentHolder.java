package ownvk.ruslan.android.myownvk.ui.view.attachment;

import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.manager.MyFragmentManager;
import ownvk.ruslan.android.myownvk.model.view.attachment.ImageAttachmentViewModel;
import ownvk.ruslan.android.myownvk.ui.view.holder.BaseViewHolder;


public class ImageAttachmentHolder extends BaseViewHolder<ImageAttachmentViewModel> {

	@BindView(R.id.iv_attachment_image)
	public ImageView image;

	@Inject
	MyFragmentManager mFragmentManager;

	public ImageAttachmentHolder(View view) {
		super(view);

		VkApplication.getApplicationComponent().inject(this);
		ButterKnife.bind(this, view);
	}

	@Override
	public void bindViewHolder(ImageAttachmentViewModel imageAttachmentViewModel) {
		Glide.with(itemView.getContext()).load(imageAttachmentViewModel.getPhotoUrl()).into(image);

	}

	@Override
	public void unbindViewHolder() {

		itemView.setOnClickListener(null);
		image.setImageBitmap(null);
	}


}
