package ownvk.ruslan.android.myownvk.model.view;

import android.view.View;

import ownvk.ruslan.android.myownvk.model.WallItem;
import ownvk.ruslan.android.myownvk.ui.holder.NewsItemBodyHolder;

public class NewsItemBodyViewModel extends BaseViewModel {
	private int mId;

	private String mText;

	public NewsItemBodyViewModel(WallItem wallItem) {
		this.mId = wallItem.getId();
		this.mText = wallItem.getText();
	}


	@Override
	public LayoutTypes getType() {
		return LayoutTypes.NewsFeedItemBody;
	}

	@Override
	public NewsItemBodyHolder onCreateViewHolder(View view) {
		return new NewsItemBodyHolder(view);
	}


	public String getText() {
		return mText;
	}

	public int getId() {
		return mId;
	}
}
