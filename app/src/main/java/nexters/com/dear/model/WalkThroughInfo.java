package nexters.com.dear.model;

import android.graphics.drawable.Drawable;

public class WalkThroughInfo {
    private String description, page;
    private int resid;

    public WalkThroughInfo() {
    }

    public WalkThroughInfo(String description, String page, int res) {
        this.description = description;
        this.page = page;
        this.resid = res;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }
}
