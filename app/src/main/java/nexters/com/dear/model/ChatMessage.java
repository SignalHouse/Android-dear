package nexters.com.dear.model;

import java.util.Date;

public class ChatMessage {
    private String name, contents;
    private Date date;
    private Boolean isDateDivider;

    public ChatMessage() {
    }

    public ChatMessage(String name, String contents, Date date) {
        this.name = name;
        this.contents = contents;
        this.date = date;
    }

    public Boolean getDateDivider() {
        return isDateDivider;
    }

    public void setDateDivider(Boolean dateDivider) {
        isDateDivider = dateDivider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
