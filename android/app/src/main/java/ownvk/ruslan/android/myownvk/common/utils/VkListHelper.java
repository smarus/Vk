package ownvk.ruslan.android.myownvk.common.utils;



import com.vk.sdk.api.model.VKAttachments;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import ownvk.ruslan.android.myownvk.model.CommentItem;
import ownvk.ruslan.android.myownvk.model.Owner;
import ownvk.ruslan.android.myownvk.model.WallItem;
import ownvk.ruslan.android.myownvk.model.attachment.ApiAttachment;
import ownvk.ruslan.android.myownvk.model.view.BaseViewModel;
import ownvk.ruslan.android.myownvk.model.view.attachment.AudioAttachmentViewModel;
import ownvk.ruslan.android.myownvk.model.view.attachment.DocAttachmentViewModel;
import ownvk.ruslan.android.myownvk.model.view.attachment.DocImageAttachmentViewModel;
import ownvk.ruslan.android.myownvk.model.view.attachment.ImageAttachmentViewModel;
import ownvk.ruslan.android.myownvk.model.view.attachment.LinkAttachmentViewModel;
import ownvk.ruslan.android.myownvk.model.view.attachment.LinkExternalViewModel;
import ownvk.ruslan.android.myownvk.model.view.attachment.PageAttachmentViewModel;
import ownvk.ruslan.android.myownvk.model.view.attachment.VideoAttachmentViewModel;
import ownvk.ruslan.android.myownvk.rest.model.response.ItemWithSendersResponse;

public class VkListHelper {
	public static List<WallItem> getWallList(ItemWithSendersResponse<WallItem> response) {
		List<WallItem> wallItems = response.items;

		for (WallItem wallItem : wallItems) {
			Owner sender = response.getSender(wallItem.getFromId());
			wallItem.setSenderName(sender.getFullName());
			wallItem.setSenderPhoto(sender.getPhoto());



			if (wallItem.haveSharedRepost()) {
				Owner repostSender = response.getSender(wallItem.getSharedRepost().getFromId());
				wallItem.getSharedRepost().setSenderName(repostSender.getFullName());
				wallItem.getSharedRepost().setSenderPhoto(repostSender.getPhoto());


				wallItem.getSharedRepost().setAttachmentsString(Utils.convertAttachmentsToFontIcons(
						wallItem.getSharedRepost().getAttachments()));
			}
		}
		return wallItems;
	}

	public static List<BaseViewModel> getAttachmentVhItems(List<ApiAttachment> attachments) {

		List<BaseViewModel> attachmentVhItems = new ArrayList<>();
		for (ApiAttachment attachment : attachments) {

			switch (attachment.getType()) {
				case VKAttachments.TYPE_PHOTO:
					attachmentVhItems.add(new ImageAttachmentViewModel(attachment.getPhoto()));
					break;

				case VKAttachments.TYPE_AUDIO:
					attachmentVhItems.add(new AudioAttachmentViewModel(attachment.getAudio()));
					break;

				case VKAttachments.TYPE_VIDEO:
					attachmentVhItems.add(new VideoAttachmentViewModel(attachment.getVideo()));
					break;

				case VKAttachments.TYPE_DOC:
					if (attachment.getDoc().getPreview() != null) {
						attachmentVhItems.add(new DocImageAttachmentViewModel(attachment.getDoc()));
					} else {
						attachmentVhItems.add(new DocAttachmentViewModel(attachment.getDoc()));
					}
					break;

				case VKAttachments.TYPE_LINK:
					if (attachment.getLink().getIsExternal() == 1) {
						attachmentVhItems.add(new LinkExternalViewModel(attachment.getLink()));
					} else {
						attachmentVhItems.add(new LinkAttachmentViewModel(attachment.getLink()));
					}
					break;

				case "page":
					attachmentVhItems.add(new PageAttachmentViewModel(attachment.getPage()));
					break;

				default:
					throw new NoSuchElementException("Attachment type " + attachment.getType() + " is not supported.");
			}
		}
		return attachmentVhItems;
	}

	public static List<CommentItem> getCommentsList(ItemWithSendersResponse<CommentItem> response, boolean isFromTopic) {
		List<CommentItem> commentItems = response.items;

		for (CommentItem commentItem : commentItems) {
			Owner sender = response.getSender(commentItem.getFromId());
			commentItem.setSenderName(sender.getFullName());
			commentItem.setSenderPhoto(sender.getPhoto());

			commentItem.setIsFromTopic(isFromTopic);

			commentItem.setAttachmentsString(Utils
					.convertAttachmentsToFontIcons(commentItem.getAttachments()));
		}
		return commentItems;
	}
}