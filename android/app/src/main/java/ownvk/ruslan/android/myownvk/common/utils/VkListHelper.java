package ownvk.ruslan.android.myownvk.common.utils;



import java.util.List;

import ownvk.ruslan.android.myownvk.model.Owner;
import ownvk.ruslan.android.myownvk.model.WallItem;
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
						wallItem.getSharedRepost().getApiAttachments()));
			}
		}
		return wallItems;
	}
}