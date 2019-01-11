
package ownvk.ruslan.android.myownvk.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("wallItems")
    @Expose
    private List<WallItem> wallItems = null;
    @SerializedName("profiles")
    @Expose
    private List<Object> profiles = null;
    @SerializedName("groups")
    @Expose
    private List<Group> groups = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<WallItem> getWallItems() {
        return wallItems;
    }

    public void setWallItems(List<WallItem> wallItems) {
        this.wallItems = wallItems;
    }

    public List<Object> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Object> profiles) {
        this.profiles = profiles;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
