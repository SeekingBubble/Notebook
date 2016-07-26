package com.example.ligan.notebook;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {
    private ImageButton noteCatButton;
    private EditText title,message;
    private Note.Category savedButtonCategory;
    private AlertDialog categoryDialogObject, confirmDialogObject;
    private static final String MODIFIED_CATEGORY = "Modified Category";
    private boolean newNote = false;
    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //check whether create a new note
        Bundle bundle = this.getArguments();
        if(bundle !=null)
         {
            newNote = bundle.getBoolean(NoteDetailActivity.NEW_NOTE_EXTRA,false);
         }

        if(savedInstanceState !=null)
        {
            savedButtonCategory = (Note.Category) savedInstanceState.get(MODIFIED_CATEGORY);
        }
        //inflate our fragment edit layout
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);
        //grab widget reference from layout
        title = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
        message = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);
        noteCatButton = (ImageButton) fragmentLayout.findViewById(R.id.editNoteButton);
        Button savedButton = (Button)  fragmentLayout.findViewById(R.id.saveNote);

        //populate widgets with note data
        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA,""));
        message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGRE_EXTRA,""));


        // IF We change a category  from our bundles than we know we  changed the orintation
        if(savedButtonCategory != null) {
            noteCatButton.setImageResource(Note.categoryToDrawable(savedButtonCategory));
        } else if(!newNote) {
            Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);
            savedButtonCategory = noteCat;
            noteCatButton.setImageResource(Note.categoryToDrawable(noteCat));
        }
        buildCategoryDialog();
        buildConfirmationDialog();

        noteCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDialogObject.show();
            }
        });

        savedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialogObject.show();
            }
        });

        return fragmentLayout;


    }
    @Override
    public void onSaveInstanceState(Bundle savedInstantceState){
        super.onSaveInstanceState(savedInstantceState);
        savedInstantceState.putSerializable(MODIFIED_CATEGORY,savedButtonCategory);

    }

    private void buildCategoryDialog(){
        final String[] categories = new String[]{"Personal","Technical","Quote","Finance"};
        AlertDialog.Builder cateGoryBuilder = new AlertDialog.Builder(getActivity());
        cateGoryBuilder.setTitle("Choose ote ype");

        cateGoryBuilder.setSingleChoiceItems(categories,0,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        //dismiss our dialog window
                        categoryDialogObject.cancel();

                        switch (item) {
                            case 0:
                                savedButtonCategory = Note.Category.PERSONAL;
                                noteCatButton.setImageResource(R.drawable.p);
                                break;
                            case 1:
                                savedButtonCategory = Note.Category.TECHNICAL;
                                noteCatButton.setImageResource(R.drawable.b);
                                break;
                            case 2:
                                savedButtonCategory = Note.Category.QUOTE;
                                noteCatButton.setImageResource(R.drawable.q);
                                break;

                            case 3:
                                savedButtonCategory = Note.Category.FINANCE;
                                noteCatButton.setImageResource(R.drawable.f);
                                break;
                        }
                    }
        });
        categoryDialogObject = cateGoryBuilder.create();
    }
    private void buildConfirmationDialog(){
        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getActivity());
        confirmBuilder.setTitle("Are you sure?");
        confirmBuilder.setMessage("Are you sure you want to save the notes");
        confirmBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Log.d("Save Note","Note title:" +title.getText() + "Note Message:" +
                        message.getText()+ "Note Category:" +savedButtonCategory);
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);

            }
        });
        confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // do nothing here
            }
        });
        confirmDialogObject = confirmBuilder.create();
    }
}
