package com.example.inyourshoes.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.inyourshoes.Interfaces.IEmoji;
import com.example.inyourshoes.Model.Emoji;
import com.example.inyourshoes.Views.R;


import java.util.List;


public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {

    List<Emoji> emojiList;
    IEmoji eListener;

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
    }


    @Override
    public int getItemCount() {
        return emojiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView emojiName, emojiImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            emojiName = this.itemView.findViewById(R.id.emojiName);
            emojiImage = this.itemView.findViewById(R.id.emojiImage);
        }
    }

    public String getEmoji(int uni){
        return new String(Character.toChars(uni));
    }
}




