package ownvk.ruslan.android.myownvk.ui.view.holder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.common.utils.Utils;
import ownvk.ruslan.android.myownvk.model.view.attachment.PageAttachmentViewModel;

public class PageAttachmentHolder extends BaseViewHolder<PageAttachmentViewModel> {
	@BindView(R.id.tv_title)
	public TextView title;

	public PageAttachmentHolder(View itemView) {
		super(itemView);

		ButterKnife.bind(this, itemView);
	}

	@Override
	public void bindViewHolder(PageAttachmentViewModel pageAttachmentViewModel) {
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Utils.openUrlInActionView(pageAttachmentViewModel.getmUrl(), view.getContext());
			}
		});
		title.setText(pageAttachmentViewModel.getTitle());
	}

	@Override
	public void unbindViewHolder() {
		itemView.setOnClickListener(null);
		title.setText(null);
	}


}