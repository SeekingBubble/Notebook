package com.example.ligan.notebook;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteDetailActivity extends AppCompatActivity {

    public static final String NEW_NOTE_EXTRA = "New Note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        createAndAddFragment();
    }
    private void createAndAddFragment(){

        //grab intent and fragment from main activity
        Intent intent =getIntent();
        MainActivity.FragmentToLaunch fragmentToLaunch =
                (MainActivity.FragmentToLaunch) intent.getSerializableExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA);

        //greab frament mangaer and our fragment transaction so that we can
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(fragmentToLaunch)
        {
            case EDIT:
                //create and add note edit fragment to note detail activit if we that's
                NoteEditFragment noteEditFragment = new NoteEditFragment();
                setTitle(R.string.editFragmentTitle);
                fragmentTransaction.add(R.id.note_container,noteEditFragment,"NOTE_EDIT_FRAGMENT");
                break;
            case VIEW:
                NoteVIewFragment noteVIewFragment = new NoteVIewFragment();
                setTitle(R.string.viewFragmentTitle);
                fragmentTransaction.add(R.id.note_container,noteVIewFragment,"NOTE_VIEW_FRAGMENT");
                break;

            case CREATE:
                NoteEditFragment noteCreateFrament = new NoteEditFragment();
                setTitle(R.string.create_fragment_title);
                // For fatal crash
                Bundle bundle = new Bundle();
                bundle.putBoolean(NEW_NOTE_EXTRA,true);
                noteCreateFrament.setArguments(bundle);

                fragmentTransaction.add(R.id.note_container,noteCreateFrament,"NOTE_CREATE_FRAGMENT");
                break;
        }
        NoteVIewFragment noteVIewFragment = new NoteVIewFragment();
        setTitle(R.string.viewFragmentTitle);
        fragmentTransaction.add(R.id.note_container,noteVIewFragment,"NOTE_VIEW_FRAGMENT");
        //commit our change
        fragmentTransaction.commit();
    }
}
