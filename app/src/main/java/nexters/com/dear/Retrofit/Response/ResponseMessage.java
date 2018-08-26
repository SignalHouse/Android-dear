package nexters.com.dear.Retrofit.Response;

import java.util.Date;

public class ResponseMessage {
    public final int status;
    public final int id;
    public final String description;
    public final int from;
    public final int to;
    public final Date updated;
    public final Date created;

    public ResponseMessage(int status, int id, String description, int from, int to, Date updated, Date created) {
        this.status = status;
        this.id = id;
        this.description = description;
        this.from = from;
        this.to = to;
        this.updated = updated;
        this.created = created;
    }
}
