package ownvk.ruslan.android.myownvk.model.view;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.model.Group;
import ownvk.ruslan.android.myownvk.ui.view.holder.BaseViewHolder;

public class InfoStatusViewModel extends BaseViewModel {
	private String mStatus;

	private String mDescription;

	private String mSite;


	public InfoStatusViewModel(Group group) {
		this.mStatus = group.getStatus();

		this.mDescription = group.getDescription();

		this.mSite = group.getSite();

	}


	@Override
	public LayoutTypes getType() {
		return LayoutTypes.InfoStatus;
	}

	@Override
	public InfoStatusViewHolder onCreateViewHolder(View view) {
		return new InfoStatusViewHolder(view);
	}


	public String getStatus() {
		return mStatus;
	}

	public String getDescription() {
		return mDescription;
	}

	public String getSite() {
		return mSite;
	}

	static class InfoStatusViewHolder extends BaseViewHolder<InfoStatusViewModel> {

		@BindView(R.id.tv_status_text)
		public TextView tvStatusText;

		@BindView(R.id.tv_description_text)
		TextView tvDescriptionText;

		@BindView(R.id.tv_site_text)
		TextView tvSiteText;


		public InfoStatusViewHolder(View itemView) {
			super(itemView);

			ButterKnife.bind(this, itemView);
		}

		@Override
		public void bindViewHolder(InfoStatusViewModel infoStatusViewModel) {
			tvStatusText.setText(infoStatusViewModel.getStatus());
			tvDescriptionText.setText(infoStatusViewModel.getDescription());
			tvSiteText.setText(infoStatusViewModel.getSite());
		}

		@Override
		public void unbindViewHolder() {
			tvStatusText.setText(null);
			tvDescriptionText.setText(null);
			tvSiteText.setText(null);
		}
	}
}