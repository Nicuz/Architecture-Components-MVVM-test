package com.nicuz.mvvmtest.ui.WordList;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nicuz.mvvmtest.R;
import com.nicuz.mvvmtest.db.entity.Word;

import java.util.List;

public class WordListFragment extends Fragment {

    private WordListViewModel mWordListViewModel;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    public WordListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.WordList_actionbar_title);
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);

        mWordListViewModel = ViewModelProviders.of(this).get(WordListViewModel.class);

        mRecyclerView = view.findViewById(R.id.WordList_recyclerview);
        mAdapter = new WordListAdapter(getContext(), mWordListViewModel);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mWordListViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                mAdapter.setWords(words);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.WordList_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.toAddWordFragment);
            }
        });

        return view;
    }

}
