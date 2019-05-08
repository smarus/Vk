package ownvk.ruslan.android.myownvk.ui.view.holder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.common.utils.Utils;
import ownvk.ruslan.android.myownvk.model.view.attachment.LinkAttachmentViewModel;

public class LinkAttachmentViewHolder extends BaseViewHolder<LinkAttachmentViewModel> {

	@BindView(R.id.tv_title)
	public TextView title;

	@BindView(R.id.tv_attachment_url)
	public TextView url;

	public LinkAttachmentViewHolder(View itemView) {
		super(itemView);

		ButterKnife.bind(this, itemView);
	}

	@Override
	public void bindViewHolder(LinkAttachmentViewModel linkAttachmentViewModel) {
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Utils.openUrlInActionView(linkAttachmentViewModel.getUrl(), view.getContext());
			}
		});
		title.setText(linkAttachmentViewModel.getTitle());
		url.setText(linkAttachmentViewModel.getUrl());
	}

	@Override
	public void unbindViewHolder() {
		itemView.setOnClickListener(null);
		title.setText(null);
		title.setText(null);
	}
}
