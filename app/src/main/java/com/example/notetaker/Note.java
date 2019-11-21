package com.example.notetaker;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Note implements Serializable {

    private int id;
    private String title;
    private String content;
    private String type;

    //Pre: Note constuctor does not exist
    //Post: note object exists
    //Function: To create a note object
    public Note(){
        this.id = -1;
        this.title="";
        this.content="";
        this.type="";
    }

    //Pre: Note constuctor does not exist
    //Post: note object exists
    //Function: To create a note object
    public Note(String title, String content, String type) {
        this();
        this.title = title;
        this.content = content;
        this.type = type;
    }

    //Pre: Note constuctor does not exist
    //Post: note object exists
    //Function: To create a note object
    public Note(int id, String title, String content, String type){
        this.id = id;
        this.title = type;
        this.content = content;
        this.type = type;
    }


    //Pre: type exists
    //Post: gets the type
    //Function: type getter
    public String getType() {
        return type;
    }

    //Pre: type exists
    //Post: sets the type
    //Function: type setter
    public void setType(String type) {
        this.type = type;
    }

    //Pre: content exists
    //Post: gets the content
    //Function: content getter
    public String getContent() {
        return content;
    }

    //Pre: content exists
    //Post: sets the content
    //Function: content setter
    public void setContent(String content) {
        this.content = content;
    }

    //Pre: title exists
    //Post: gets the title
    //Function: title getter
    public String getTitle() {
        return title;
    }

    //Pre: id exists
    //Post: gets the id
    //Function: id getter
    public int getId() {
        return id;
    }

    //Pre: id exists
    //Post: sets the id
    //Function: id setter
    public void setId(int id) {
        this.id = id;
    }

    //Pre: title exists
    //Post: sets the title
    //Function: title setter
    public void setTitle(String title) {
        this.title = title;
    }

    //Pre: toString default
    //Post: overriden toString
    //Function: Override toString
    @NonNull
    @Override
    public String toString() {
        return title.length() > 0 ? this.title : "[empty title]";
    }
}