package com.nicuz.mvvmtest.ui.WordList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicuz.mvvmtest.R;
import com.nicuz.mvvmtest.db.entity.Word;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private final WordListViewModel mWordListViewModel;
    private List<Word> mWords;

    WordListAdapter(Context context, WordListViewModel wordListViewModel) {
        mInflater = LayoutInflater.from(context);
        mWordListViewModel = wordListViewModel;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView wordItemView;
        private TextView descriptionItemView;
        private ImageView deleteBtn;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.wordlist_recyclerview_item_name);
            descriptionItemView = itemView.findViewById(R.id.wordlist_recyclerview_item_description);
            deleteBtn = itemView.findViewById(R.id.wordlist_recyclerview_item_deleteBtn);
        }
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.wordlist_recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final WordViewHolder holder, final int position) {
        holder.wordItemView.setText(mWords.get(position).getWord());
        holder.descriptionItemView.setText("Rowid: " + mWordListViewModel.getRowid(mWords.get(position).getWord()));
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word(mWords.get(position).getWord());
                mWordListViewModel.deleteWord(word);
            }
        });
    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}
