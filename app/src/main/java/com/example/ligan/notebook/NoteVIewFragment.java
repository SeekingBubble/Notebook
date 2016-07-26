package com.example.ligan.notebook;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteVIewFragment extends Fragment {


    public NoteVIewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Get curent view information
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_view,container,false);
        TextView title = (TextView) fragmentLayout.findViewById(R.id.viewNoteTitle);
        TextView message = (TextView) fragmentLayout.findViewById(R.id.viewNoteMessage);
        ImageView icon = (ImageView) fragmentLayout.findViewById(R.id.viewNoteIcon);
        // destructure the intent information
        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
        message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGRE_EXTRA));
        Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);

        icon.setImageResource(Note.categoryToDrawable(noteCat));

        // Inflate the layout for this fragment
        return fragmentLayout;
    }


}
