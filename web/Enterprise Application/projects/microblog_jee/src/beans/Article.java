package beans;

public class Article {
    private long id;
    private long idUser;
    private String titre;
    private String value;
    private String image;
    private String date;

    public Article(long id, long idUser, String titre2, String value2, String image2, String format) {
        this.id = id;
        this.idUser = idUser;
        this.titre = titre2;
        this.value = value2;
        this.image = image2;
        this.date = format;
    }

    public String getValue() {
        return value;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return "id : " + this.id + ", idUser : " + this.idUser + ", titre : " + this.titre + ", value : " + this.value + ", image : " + this.image + ", date : " + this.date;
    }
}