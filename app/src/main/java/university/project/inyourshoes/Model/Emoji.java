package university.project.inyourshoes.Model;

public class Emoji extends EmojiArrayList {


    String name;
    String unicode;

    public Emoji(){

    }

    public Emoji(String unicode, String name) {
        this.name = name;
        this.unicode = unicode;

    }

    public String getUnicode() {
        return unicode;
    }

    public int setUnicode(int unicode) {
        return unicode;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }
}
