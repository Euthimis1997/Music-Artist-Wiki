package myCode;
//CLASS THAT CREATES THE OBJECT THAT CONTAINS THE INFORMATION OF EACH REGISTRATION
public class artists {

    private String name;
    private String genre;
    private String description;
    private String discography;
    private String rating;

    public artists(String name, String genre, String description, String discography,String rating) {
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.discography = discography;
        this.rating = rating;
    }

    public artists() {
        this.name = "";
        this.genre = "";
        this.description = "";
        this.discography = "";
        this.rating = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscography() {
        return discography;
    }

    public void setDiscography(String discography) {
        this.discography = discography;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
