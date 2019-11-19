package com.example.notetaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static android.provider.Settings.NameValueTable.NAME;

public class NoteOpenHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "notesDatabase";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_NOTES = "tableNotes";
    static final String ID = "_id";
    static final String TITLE = "title";
    static final String CONTENT = "content";
    static final String TYPE = "type";

    public NoteOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreate = "CREATE TABLE " + TABLE_NOTES + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITLE + " TEXT, " +
                CONTENT + " TEXT, " +
                TYPE + " TEXT)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertNote(Note note){
        String sqlInsert = "INSERT INTO" + TABLE_NOTES + " VALUES(null, '" +
                note.getTitle() + "', '" + note.getType() + "', '" +
                note.getContent() + ")";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlInsert);
        db.close();
    }

    public Cursor getSelectAllContactsCursor(){

        String sqlSelect = "SELECT * FROM " + TABLE_NOTES;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(sqlSelect, null);

        return cursor;
    }

    public List<Note> getSelectAllNotesList(){
        List<Note> noteList = new ArrayList<>();

        Cursor cursor = getSelectAllContactsCursor();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String type = cursor.getString(2);
            String content = cursor.getString(3);
            Note note = new Note(id, title, type, content);
            noteList.add(note);
        }
        return noteList;
    }

    public void updateContactById(int id, Note newNote){

        String sqlUpate = "UPDATE " + TABLE_NOTES + " SET " + TITLE + "='" +
                newNote.getTitle() + "', " + TYPE + "='" +
                newNote.getType() + "', " + CONTENT + "' WHERE " +
                ID + "=" + id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpate);

        db.close();
    }

    public void deleteAllContacts(){
        String sqlDelete = "DELETE FROM " + TABLE_NOTES;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);

        db.close();
    }


}
