package com.example.ligan.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ligan on 2016-07-20.
 */
public class NoteAdapter extends ArrayAdapter<Note>{
    public static class ViewHolder{
        TextView title;
        TextView note;
        ImageView noteIcon;
    }
    public NoteAdapter(Context context, ArrayList<Note> notes){
        super(context,0,notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Get the data item from this postion
        Note note = getItem(position);

        ViewHolder viewHolder;

    //check if an exsiting view is being reused, otherwise inflate a new view from custim row layout
    if(convertView == null){

        viewHolder = new ViewHolder();
        //set our views to our view holder, so that no longer have to go bakck and use find view
        // by id every time we have a new view
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row,parent,false);

        viewHolder.title = (TextView)convertView.findViewById(R.id.lisItemNoteTitle);
        viewHolder.note = (TextView)convertView.findViewById(R.id.lisItemNoteBody);
        viewHolder.noteIcon = (ImageView) convertView.findViewById(R.id.listItemNoteImg);
        convertView.setTag(viewHolder);
    } else{
        viewHolder = (ViewHolder)convertView.getTag();
    }
    //Grab reference of view so we can populate them with specific note row data

        //
        viewHolder.title.setText(note.getTitle());
        viewHolder.note.setText(note.getMessage());
        viewHolder.noteIcon.setImageResource(note.getAssociatedDrawable());

        return convertView;
    }
}
