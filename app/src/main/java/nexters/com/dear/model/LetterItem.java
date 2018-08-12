package nexters.com.dear.model;

public class LetterItem {
    private String title, sender;
    private boolean isNew;
    private int letterID;

    public LetterItem(){

    }
    public LetterItem(String title, String sender) {
        this.title = title;
        this.sender = sender;
        isNew = true;
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
}
