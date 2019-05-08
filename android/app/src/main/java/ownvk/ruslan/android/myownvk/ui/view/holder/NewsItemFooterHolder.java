package ownvk.ruslan.android.myownvk.ui.view.holder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.manager.MyFragmentManager;
import ownvk.ruslan.android.myownvk.common.utils.Utils;
import ownvk.ruslan.android.myownvk.common.utils.VkListHelper;
import ownvk.ruslan.android.myownvk.model.Likes;
import ownvk.ruslan.android.myownvk.model.Place;
import ownvk.ruslan.android.myownvk.model.WallItem;
import ownvk.ruslan.android.myownvk.model.view.NewsItemFooterViewModel;
import ownvk.ruslan.android.myownvk.model.view.counter.CommentCounterViewModel;
import ownvk.ruslan.android.myownvk.model.view.counter.LikeCounterViewModel;
import ownvk.ruslan.android.myownvk.model.view.counter.RepostCounterViewModel;
import ownvk.ruslan.android.myownvk.rest.api.LikeEventOnSubscribe;
import ownvk.ruslan.android.myownvk.rest.api.WallApi;
import ownvk.ruslan.android.myownvk.rest.model.request.WallGetByIdRequestModel;
import ownvk.ruslan.android.myownvk.ui.activity.BaseActivity;
import ownvk.ruslan.android.myownvk.ui.fragment.CommentsFragment;

public class NewsItemFooterHolder extends BaseViewHolder<NewsItemFooterViewModel> {

	public static final String POST = "post";

	@BindView(R.id.tv_date)
	public TextView tvDate;

	@BindView(R.id.tv_likes_count)
	public TextView tvLikesCount;
	@BindView(R.id.tv_likes_icon)
	public TextView tvLikesIcon;

	@BindView(R.id.tv_comments_icon)
	public TextView tvCommentIcon;
	@BindView(R.id.tv_comments_count)
	public TextView tvCommentsCount;

	@BindView(R.id.tv_reposts_icon)
	public TextView tvRepostIcon;
	@BindView(R.id.tv_reposts_count)


	public TextView tvRepostsCount;

	@BindView(R.id.rl_comments)
	public View rlComments;


	@BindView(R.id.rl_likes)
	public View rlLikes;

	@Inject
	Typeface mGoogleFontTypeface;

	@Inject
	MyFragmentManager mFragmentManager;

	@Inject
	WallApi mWallApi;


	private Resources mResources;

	private Context mContext;


	public NewsItemFooterHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
		VkApplication.getApplicationComponent().inject(this);

		mContext = itemView.getContext();
		mResources = mContext.getResources();

		tvLikesIcon.setTypeface(mGoogleFontTypeface);
		tvCommentIcon.setTypeface(mGoogleFontTypeface);
		tvRepostIcon.setTypeface(mGoogleFontTypeface);
	}


	@Override
	public void bindViewHolder(NewsItemFooterViewModel item) {
		tvDate.setText(Utils.parseDate(item.getDateLong(), mContext));

		bindLikes(item.getLikes());
		bindComments(item.getComments());
		bindReposts(item.getReposts());

		rlComments.setOnClickListener(view -> mFragmentManager.addFragment((BaseActivity) view.getContext(),
				CommentsFragment.newInstance(new Place(String.valueOf(item.getOwnerId()), String.valueOf(item.getId()))),
				R.id.main_wrapper));
	}

	private void bindLikes(LikeCounterViewModel likes) {
		tvLikesCount.setText(String.valueOf(likes.getCount()));
		tvLikesCount.setTextColor(mResources.getColor(likes.getTextColor()));
		tvLikesIcon.setTextColor(mResources.getColor(likes.getIconColor()));
	}

	private void bindComments(CommentCounterViewModel comments) {
		tvCommentsCount.setText(String.valueOf(comments.getCount()));
		tvCommentsCount.setTextColor(mResources.getColor(comments.getTextColor()));
		tvCommentIcon.setTextColor(mResources.getColor(comments.getIconColor()));
	}

	private void bindReposts(RepostCounterViewModel reposts) {
		tvRepostsCount.setText(String.valueOf(reposts.getCount()));
		tvRepostsCount.setTextColor(mResources.getColor(reposts.getTextColor()));
		tvRepostIcon.setTextColor(mResources.getColor(reposts.getIconColor()));
	}


	@Override
	public void unbindViewHolder() {
		tvDate.setText(null);
		tvLikesCount.setText(null);
		tvCommentsCount.setText(null);
		tvRepostsCount.setText(null);
	}


	public void saveToDb(RealmObject item) {
		Realm realm = Realm.getDefaultInstance();
		realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
	}

	public Observable<LikeCounterViewModel> likeObservable(int ownerId, int postId, Likes likes) {
		return Observable.create(new LikeEventOnSubscribe(POST, ownerId, postId, likes))

				.observeOn(Schedulers.io())
				.flatMap(count -> {

					return mWallApi.getById(new WallGetByIdRequestModel(ownerId, postId).toMap());
				})
				.flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
				.doOnNext(this::saveToDb)
				.map(wallItem -> new LikeCounterViewModel(wallItem.getLikes()));
	}

	public void like(NewsItemFooterViewModel item) {
		WallItem wallItem = getWallItemFromRealm(item.getId());
		likeObservable(wallItem.getOwnerId(), wallItem.getId(), wallItem.getLikes())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(likes -> {
					item.setLikes(likes);
					bindLikes(likes);
				}, error -> {
					error.printStackTrace();
				});
	}

	public WallItem getWallItemFromRealm(int postId) {
		Realm realm = Realm.getDefaultInstance();
		WallItem wallItem = realm.where(WallItem.class)
				.equalTo("id", postId)
				.findFirst();

		return realm.copyFromRealm(wallItem);
	}
}