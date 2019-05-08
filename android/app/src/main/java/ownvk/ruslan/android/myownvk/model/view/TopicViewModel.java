package ownvk.ruslan.android.myownvk.model.view;

import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.manager.MyFragmentManager;
import ownvk.ruslan.android.myownvk.model.Place;
import ownvk.ruslan.android.myownvk.model.Topic;
import ownvk.ruslan.android.myownvk.ui.activity.BaseActivity;
import ownvk.ruslan.android.myownvk.ui.fragment.TopicCommentsFragment;
import ownvk.ruslan.android.myownvk.ui.view.holder.BaseViewHolder;

public class TopicViewModel extends BaseViewModel {
	private int mId;
	private int mGroupId;
	private String mTitle;

	private String mCommentsCount;



	public TopicViewModel() {

	}

	public TopicViewModel(Topic topic) {
		this.mId = topic.getId();
		this.mGroupId = topic.getGroupId();

		this.mTitle = topic.getTitle();

		this.mCommentsCount = topic.getComments() + " сообщений";
	}


	@Override
	public LayoutTypes getType() {
		return LayoutTypes.Topic;
	}

	@Override
	protected BaseViewHolder onCreateViewHolder(View view) {
		return new TopicViewHolder(view);
	}

	public int getId() {
		return mId;
	}

	public int getGroupId() {
		return mGroupId;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getCommentsCount() {
		return mCommentsCount;
	}



	public static class TopicViewHolder extends BaseViewHolder<TopicViewModel> {

		@BindView(R.id.tv_title)
		public TextView tvTitle;

		@BindView(R.id.tv_comments_count)
		public TextView tvCommentsCount;

		@Inject
		MyFragmentManager mFragmentManager;


		public TopicViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			VkApplication.getApplicationComponent().inject(this);

		}

		@Override
		public void bindViewHolder(TopicViewModel topicViewModel) {
			tvTitle.setText(topicViewModel.getTitle());
			tvCommentsCount.setText(topicViewModel.getCommentsCount());



			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					mFragmentManager.addFragment((BaseActivity) view.getContext(),
							TopicCommentsFragment.newInstance(new Place(String.valueOf(topicViewModel.getGroupId()), String.valueOf(topicViewModel.getId()))),
							R.id.main_wrapper);
				}
			});
		}

		@Override
		public void unbindViewHolder() {
			tvTitle.setText(null);
			tvCommentsCount.setText(null);
		}
	}
}
