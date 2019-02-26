package com.nicuz.mvvmtest.ui.AddWord;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.nicuz.mvvmtest.R;
import com.nicuz.mvvmtest.db.entity.Word;

public class AddWordFragment extends Fragment {

    private AddWordViewModel mAddWordViewModel;
    private TextInputLayout mTextInputLayout;
    private TextInputEditText wordField;

    public AddWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.AddWord_actionbar_title);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_add_word, container, false);

        mAddWordViewModel = ViewModelProviders.of(this).get(AddWordViewModel.class);

        mTextInputLayout = view.findViewById(R.id.AddWord_word_TextInputLayout);
        wordField = view.findViewById(R.id.AddWord_word_edittext);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.addword_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.AddWord_saveBtn) {
            if (wordField.getText().toString().trim().length() == 0) {
                mTextInputLayout.setError(getContext().getString(R.string.AddWord_textinputedittext_error));
            } else {
                Word word = new Word(wordField.getText().toString().trim());
                mAddWordViewModel.insert(word);
                hideKeyboard(getActivity());
                getActivity().onBackPressed();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            imm.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
