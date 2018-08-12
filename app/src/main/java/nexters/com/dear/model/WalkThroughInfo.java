package nexters.com.dear.model;

import android.graphics.drawable.Drawable;

public class WalkThroughInfo {
    private String description, page;
    private Drawable res;

    public WalkThroughInfo() {
    }

    public WalkThroughInfo(String description, String page, Drawable res) {
        this.description = description;
        this.page = page;
        this.res = res;
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

    public Drawable getRes() {
        return res;
    }

    public void setRes(Drawable res) {
        this.res = res;
    }
}
