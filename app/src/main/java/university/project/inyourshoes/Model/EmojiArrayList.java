package university.project.inyourshoes.Model;

import java.util.ArrayList;

public class EmojiArrayList extends ArrayList<Emoji> {

    //Extended by emoji object as an array list of the emoji object functionality
    // extends the ArrayList object with Emoji as object within the arraylist

    public ArrayList emojiList;

    public EmojiArrayList() {
        super();
    }

    //setter and getter
    public ArrayList getEmojiList(){
        return emojiList;
    }

    public ArrayList setEmojiList(ArrayList emojiList) {
        this.emojiList = emojiList;
        return this;
    }
}
