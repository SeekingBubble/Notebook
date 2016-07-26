package com.example.ligan.notebook;

import android.icu.util.ULocale;

/**
 * Created by ligan on 2016-07-20.
 */
public class Note {
    private String title, message;
    private long noteId,dateCreateMilli;
    //private Category category;
    private Category category;
    public enum Category{PERSONAL, TECHNICAL,QUOTE,FINANCE};
    public Note(String title, String message, Category category){
        this.title = title;
        this.message = message;
        this.category = category;
        this.noteId = 0;
        this.dateCreateMilli = 0;
    }
    public Note(String title, String message, Category category,long noteId,long dateCreateMilli){
        this.title = title;
        this.message = message;
        this.category = category;
        this.noteId = noteId;
        this.dateCreateMilli = dateCreateMilli;
    }

    public String getTitle(){
        return title;
    }
    public String getMessage(){
        return message;
    }
    public Category getCategory(){
        return category;
    }
    public long getDate(){
        return dateCreateMilli;
    }
    public long getId(){
        return noteId;
    }
    public String toString(){
        return "ID"+ noteId + "Title" + title + "Message:" + message +"IconID:" + category.name()
                + "Date: ";
    }
    public int getAssociatedDrawable(){
        return categoryToDrawable(category);
    }
    public static int categoryToDrawable(Category noteCategory){
        switch (noteCategory){
            case PERSONAL:
                return R.drawable.p;
            case TECHNICAL:
                return R.drawable.b;
            case FINANCE:
                return R.drawable.f;
            case QUOTE:
                return R.drawable.q;
        }
        return R.drawable.p;
    }
}
