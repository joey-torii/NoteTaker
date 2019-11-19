package com.example.notetaker;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Note implements Serializable {

    private int id;
    private String title;
    private String content;
    private String type;


    public Note(){
        this.title="";
        this.content="";
        this.type="";
    }


    public Note(int id, String title, String content, String type){
        this.id = id;
        this.title = type;
        this.content = content;
        this.type = type;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return title.length() > 0 ? this.title : "[empty title]";
    }
}
