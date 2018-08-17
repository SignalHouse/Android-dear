package nexters.com.dear.model;

import java.util.Date;

public class LetterItem {
    private String title, sender;
    private boolean isNew, isCheckVisible;
    private int letterID;
    private Date date;
    public LetterItem(){
        isCheckVisible = false;
    }
    public LetterItem(String title, String sender, Date date) {
        this.title = title;
        this.sender = sender;
        this.date = date;
        isNew = true;
        isCheckVisible = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public int getLetterID() {
        return letterID;
    }

    public void setLetterID(int letterID) {
        this.letterID = letterID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCheckVisible() {
        return isCheckVisible;
    }

    public void setCheckVisible(boolean checkVisible) {
        isCheckVisible = checkVisible;
    }
}
