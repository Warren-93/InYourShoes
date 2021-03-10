package university.project.inyourshoes.Model;

import java.util.ArrayList;

public class EmojiArrayList extends ArrayList<Emoji> {

    public ArrayList emojiList;

    public EmojiArrayList() {
        super();
    }

    public ArrayList getEmojiList(){
        return emojiList;
    }

    public ArrayList setEmojiList(ArrayList emojiList) {
        this.emojiList = emojiList;
        return this;
    }
}
