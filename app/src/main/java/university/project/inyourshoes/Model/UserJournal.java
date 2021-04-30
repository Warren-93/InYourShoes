package university.project.inyourshoes.Model;

public class UserJournal {

    //User Journal Object cxreated for the collection of data entered into journal
    // which will be stored in firebase database
    String userId;
    String date;
    String entry;

    public UserJournal(){}

    //Getter and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

}
