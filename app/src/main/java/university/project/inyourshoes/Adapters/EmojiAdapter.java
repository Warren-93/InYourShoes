package university.project.inyourshoes.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import university.project.inyourshoes.Interfaces.IEmoji;


import university.project.inyourshoes.Views.R;

import java.util.List;
import java.util.Random;

import university.project.inyourshoes.Model.Emoji;


public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {

    List<Emoji> emojiList;
    IEmoji eListener;

    public String[] mColors = { "1B5E20", "311B92", "303F9F", "004D40",
            "388E3C", "1A237E", "512DA8", "00796B",
            "00897B", "4CAF50", "", "", "", "", "", "",};

    public EmojiAdapter(List<Emoji> emojiList, IEmoji eListener){
        this.emojiList = emojiList;
        this.eListener = eListener;
    }

    @NonNull
    @Override
    public EmojiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.emojicard, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Emoji emoji = emojiList.get(position);
        String emojiCharString = emoji.getUnicode();
        int emojiCharInt = Integer.decode(emojiCharString);
        emoji.setUnicode(emojiCharInt);
        holder.emojiName.setText(emoji.getName());
        holder.emojiImage.setText(getEmoji(emojiCharInt));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             eListener.onClick(holder.emojiImage, position);
            }
        });

        holder.emojiCard.setBackgroundResource(R.drawable.emoji_gradient);

        //Loop for picking random colour from list for each emojicards background
/*        for(int i = 0; i < emojiList.size() ; i++) {
            String color="#"+mColors[position];
            holder.emojiCard.setCardBackgroundColor(Color.parseColor(color));

        }*/
    }

    @Override
    public int getItemCount() {
        return emojiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView emojiName, emojiImage;
        CardView emojiCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            emojiName = this.itemView.findViewById(R.id.emojiName);
            emojiImage = this.itemView.findViewById(R.id.emojiImage);
            emojiCard = this.itemView.findViewById(R.id.emojiButtonCard);
        }
    }

    public String getEmoji(int uni){
        return new String(Character.toChars(uni));
    }


    //Random Color List picker
/*    public int getRandomColor(){
        Random random = new Random();
        int index = random.nextInt(mColors.length);
        return index;

    }*/
}




