package ownvk.ruslan.android.myownvk.model.view.counter;

import ownvk.ruslan.android.myownvk.model.Likes;

public class LikeCounterViewModel extends CounterViewModel{

	private Likes mLikes;

	public LikeCounterViewModel(Likes likes) {
		super(likes.getCount());

		this.mLikes = likes;

		if (mLikes.getUserLikes() == 1) {
			setAccentColor();
		}
	}

	public Likes getLikes() {
		return mLikes;
	}
}
