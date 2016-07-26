package com.example.ligan.notebook;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityListFragment extends ListFragment {
    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         /*
        String[] values = new String[]{"Android","Brookly","Couch",
                "December","England","Frankf","Gorge","Henry","Iliana",
                "Junk food","Kilograme","Linkingpark","Mamamiya"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,values);
        setListAdapter(adapter);
        */
        notes = new ArrayList<Note>();
        notes.add(new Note("1 This is a new note title", "1 body This is the body of note", Note.Category.PERSONAL));
        notes.add(new Note("2 This is a new note title", "2 body This is the body of note", Note.Category.QUOTE));
        notes.add(new Note("3 This is a new note title", "3 body This is the body of note", Note.Category.FINANCE));
        notes.add(new Note("4 This is a new note title", "4 body This is the body of note", Note.Category.TECHNICAL));
        notes.add(new Note("5 This is a new note title", "5 body This is the body of note", Note.Category.TECHNICAL));
        notes.add(new Note("6 This is a new note title", "6 body This is the body of note", Note.Category.TECHNICAL));
        notes.add(new Note("7 This is a new note title", "7 body This is the body of note", Note.Category.PERSONAL));
        notes.add(new Note("8 This is a new note title", "8 body This is the body of note", Note.Category.FINANCE));
        notes.add(new Note("9 This is a new note title", "9 body This is the body of note", Note.Category.PERSONAL));
        notes.add(new Note("10 This is a new note title", "10 body This is the body of note", Note.Category.FINANCE));
        notes.add(new Note("11 This is a new note title", "11 body This is the body of note", Note.Category.PERSONAL));

        noteAdapter = new NoteAdapter(getActivity(),notes);
        setListAdapter(noteAdapter);

        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v,int position, long id){
        super.onListItemClick(l,v,position,id);
        launchNoteDetailActivity(MainActivity.FragmentToLaunch.VIEW,position);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
       // give me the position of whatever note I long pressed on
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        // return to us id of whtaever menu item selceted
        switch(item.getItemId())
        {
            case R.id.edit:
                // do something here
                launchNoteDetailActivity(MainActivity.FragmentToLaunch.EDIT,rowPosition);
                Log.d("Menu clicks","We pressed eidt menu!");
                return true;

        }
        return super.onContextItemSelected(item);
    }
    private void launchNoteDetailActivity(MainActivity.FragmentToLaunch ftl,int position){
        Note note = (Note) getListAdapter().getItem(position);
                //ctreat a new intent that lanuch our note detail activity
                Intent intent = new Intent(getActivity(),NoteDetailActivity.class);
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA,note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGRE_EXTRA,note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA,note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA,note.getId());
        switch(ftl)
        {
            case VIEW:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, MainActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA,MainActivity.FragmentToLaunch.EDIT);
                break;
        }
        startActivity(intent);
    }
    /* Blocked by hlg 20130719 for *****
    public MainActivityListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }
*/
}
