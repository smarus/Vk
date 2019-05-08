package ownvk.ruslan.android.myownvk.di;


import javax.inject.Singleton;

import dagger.Component;
import ownvk.ruslan.android.myownvk.common.manager.NetworkManager;
import ownvk.ruslan.android.myownvk.model.view.CommentBodyViewModel;
import ownvk.ruslan.android.myownvk.model.view.CommentFooterViewModel;
import ownvk.ruslan.android.myownvk.model.view.TopicViewModel;
import ownvk.ruslan.android.myownvk.mvp.presenter.BoardPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.CommentsPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.InfoPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.MainPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.MembersPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.NewsFeedPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.OpenedPostPresenter;
import ownvk.ruslan.android.myownvk.mvp.presenter.TopicCommentsPresenter;
import ownvk.ruslan.android.myownvk.ui.activity.BaseActivity;
import ownvk.ruslan.android.myownvk.ui.activity.MainActivity;
import ownvk.ruslan.android.myownvk.ui.fragment.CommentsFragment;
import ownvk.ruslan.android.myownvk.ui.fragment.NewsFeedFragment;
import ownvk.ruslan.android.myownvk.ui.fragment.OpenedCommentFragment;
import ownvk.ruslan.android.myownvk.ui.fragment.OpenedCommentPresenter;
import ownvk.ruslan.android.myownvk.ui.fragment.OpenedPostFragment;
import ownvk.ruslan.android.myownvk.ui.fragment.TopicCommentsFragment;
import ownvk.ruslan.android.myownvk.ui.view.attachment.DocImageAttachmentHolder;
import ownvk.ruslan.android.myownvk.ui.view.attachment.ImageAttachmentHolder;
import ownvk.ruslan.android.myownvk.ui.view.attachment.VideoAttachmentHolder;
import ownvk.ruslan.android.myownvk.ui.view.holder.NewsItemBodyHolder;
import ownvk.ruslan.android.myownvk.ui.view.holder.NewsItemFooterHolder;

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {
	void inject(BaseActivity baseActivity);
	void inject(MainActivity mainActivity);
	void inject(NewsFeedFragment fragment);
	void inject(NewsItemBodyHolder holder);
	void inject(NewsItemFooterHolder holder);
	void inject(NewsFeedPresenter presenter);
	void inject(NetworkManager manager);
	void inject(MainPresenter presenter);
	void inject(MembersPresenter presenter);
	void inject(BoardPresenter presenter);
	void inject(InfoPresenter presenter);
	void inject(ImageAttachmentHolder holder);
	void inject(VideoAttachmentHolder holder);
	void inject(DocImageAttachmentHolder holder);
	void inject(OpenedPostPresenter presenter);
	void inject(OpenedPostFragment fragment);
	void inject(CommentBodyViewModel.CommentBodyViewHolder holder);
	void inject(CommentFooterViewModel.CommentFooterHolder holder);
	void inject(CommentsPresenter presenter);
	void inject(CommentsFragment fragment);
	void inject(OpenedCommentFragment fragment);
	void inject(OpenedCommentPresenter presenter);
	void inject(TopicCommentsPresenter presenter);
	void inject(TopicCommentsFragment fragment);
	void inject(TopicViewModel.TopicViewHolder holder);



}
