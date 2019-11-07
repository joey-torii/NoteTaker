package com.example.notetaker;

import androidx.annotation.NonNull;

public class Note {

    private String title;   // String for title edit Text
    private String content; // String for content eit text

    /*
    The default constructor for NoteActivity
     */
    public Note(){
        this.title = "";
        this.content ="";
    }

    /*
    The EVC for NoteActivity
    *
    * @params: sets both the title and the content
     */
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /*
    getter for String title
    *
    * @return: title as a String
     */
    public String getTitle() {
        return title;
    }

    /*
    setter for String title
    *
    * @params: title as a String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /*
    getter for String content
    *
    * @return: content as a String
     */
    public String getContent() {
        return content;
    }

    /*
    setter for String content
    *
    * @params: content as a String
     */
    public void setContent(String content) {
        this.content = content;
    }

    /*
    toString() that just returns the title and the content
    *
    * @return: the title and content as a String
     */
    @NonNull
    @Override
    public String toString() {
        return title + content;
    }
}
