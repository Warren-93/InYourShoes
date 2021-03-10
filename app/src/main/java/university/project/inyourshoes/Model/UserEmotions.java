package university.project.inyourshoes.Model;

public class UserEmotions {

    String userId;
    String unicode;
    String unicodeName;
    String date;

    public UserEmotions() {
        this.userId = userId;
        this.unicode = unicode;
        this.unicodeName = unicodeName;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public void setUnicodeName(String unicodeName) {
        this.unicodeName = unicodeName;
    }

    public String getUnicodeName() {
        return unicodeName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

}
