package ownvk.ruslan.android.myownvk.model.view.attachment;

import android.view.View;

import ownvk.ruslan.android.myownvk.common.utils.Utils;
import ownvk.ruslan.android.myownvk.model.attachment.Audio;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.ui.view.holder.AudioAttachmentHolder;

public class AudioAttachmentViewModel extends BaseViewModel {

	private String mTitle;
	private String mArtist;

	private String mDuration;


	public AudioAttachmentViewModel(Audio audio) {
		if (audio.getTitle().equals("")) {
			mTitle = "Title";
		} else {
			mTitle = audio.getTitle();
		}

		if (audio.getArtist().equals("")) {
			mArtist = "Various Artist";
		} else {
			mArtist = audio.getArtist();
		}

		mDuration = Utils.parseDuration(audio.getDuration());
	}

	@Override
	public LayoutTypes getType() {
		return LayoutTypes.AttachmentAudio;
	}

	@Override
	public AudioAttachmentHolder onCreateViewHolder(View view) {
		return new AudioAttachmentHolder(view);
	}


	public String getTitle() {
		return mTitle;
	}

	public String getArtist() {
		return mArtist;
	}

	public String getDuration() {
		return mDuration;
	}
}