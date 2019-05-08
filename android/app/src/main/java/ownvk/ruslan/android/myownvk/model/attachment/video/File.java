package ownvk.ruslan.android.myownvk.model.attachment.video;

import io.realm.RealmObject;

public class File extends RealmObject {

	public String external;

	public String getExternal() {
		return external;
	}

	public void setExternal(String external) {
		this.external = external;
	}
}
