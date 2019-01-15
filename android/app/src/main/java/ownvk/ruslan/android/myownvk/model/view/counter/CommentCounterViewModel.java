package ownvk.ruslan.android.myownvk.model.view.counter;


import ownvk.ruslan.android.myownvk.model.Comments;

public class CommentCounterViewModel extends CounterViewModel{

	private Comments mComments;

	public CommentCounterViewModel(Comments comments) {
		super(comments.getCount());

		this.mComments = comments;
	}

	public Comments getComments() {
		return mComments;
	}
}