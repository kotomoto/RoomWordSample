package com.koto.roomwordsamplejava;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater inflater;
    private List<Word> words;

    public WordListAdapter(final Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final WordViewHolder holder, final int position) {
        if (words != null) {
            holder.wordItemView.setText(words.get(position).getWord());
        } else {
            holder.wordItemView.setText("No word");
        }
    }

    @Override
    public int getItemCount() {
        if (words != null) {
            return words.size();
        } else {
            return 0;
        }
    }

    void setWords(final List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordItemView;

        private WordViewHolder(final View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}
