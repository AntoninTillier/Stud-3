package beans;

public class Commentaire {
    private long id;
    private long postId;
    private long userId;
    private String value;
    private String date;

    public Commentaire(long id, long postId, long userID, String value, String date) {
        this.id = id;
        this.postId = postId;
        this.userId = userID;
        this.value = value;
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}