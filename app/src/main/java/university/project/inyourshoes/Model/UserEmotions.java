package university.project.inyourshoes.Model;

public class UserEmotions {

    //User Emotions Object cxreated for the collection of data for the clicked emoticon data
    // which will be stored in firebase database
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


    //Getter and setters
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
