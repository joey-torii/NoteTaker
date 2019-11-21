package com.example.notetaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.provider.Settings.NameValueTable.NAME;

public class NoteOpenHelper extends SQLiteOpenHelper {

    static final String TAG = "Tag";

    static final String DATABASE_NAME = "noteDatabase";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_NOTES = "tableNotes";
    static final String ID = "_id"; // _id is for use with adapters later
    static final String TITLE = "title";
    static final String CONTENT = "content";
    static final String TYPE = "type";

    public NoteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreate = "CREATE TABLE " + TABLE_NOTES +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITLE + " TEXT, " +
                CONTENT + " TEXT, " +
                TYPE + " TEXT)";
        Log.d(TAG, "onCreate: " + sqlCreate);
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Pre: Note exists
    //Post: Inserts a note
    //Function: Inserts a note
    public void insertNote(Note note) {
        String sqlInsert = "INSERT INTO " + TABLE_NOTES + " VALUES(null, '" +
                note.getTitle() + "', '" +
                note.getContent() + "', '" +
                note.getType() + "'" + ")";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlInsert);
        db.close();
    }

    public Cursor getSelectAllNotesCursor() {
        String sqlSelect = "SELECT * FROM " + TABLE_NOTES;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        return cursor;
    }

    public List<Note> getSelectAllNotesList() {
        List<Note> noteList = new ArrayList<>();

        Cursor cursor = getSelectAllNotesCursor();
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            String type = cursor.getString(3);
            Note note = new Note(id, title, content, type);
            noteList.add(note);
        }
        return noteList;
    }

    public void updateNoteById(int id, Note newNote) {
        String sqlUpdate = "UPDATE " + TABLE_NOTES + " SET " + TITLE + " ='" +
                newNote.getTitle() + "', " + CONTENT + " = '" +
                newNote.getContent() + "', " + TYPE + " = '" +
                newNote.getType() + "' WHERE " + ID + " = " + id;
        Log.d(TAG, "updatenoteById: " + sqlUpdate);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }

    public void deleteIndividualNote(int id)
    {
        String sqlUpdate = "DELETE FROM " + TABLE_NOTES + " WHERE " + ID + " = " + id;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }

    public Cursor getAllNotes()
    {
        String sqlSelect = "SELECT * FROM " + TABLE_NOTES;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        return cursor;
    }

    public Note getNoteById(int id) {
        Note note = new Note();
        String sqlSelect = "SELECT * FROM " + TABLE_NOTES + " WHERE _id = "
                + id;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);

        if (cursor.moveToNext()) {
            note.setId(cursor.getInt(0));
            note.setTitle(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setType(cursor.getString(3));
            db.close();
            return note;
        }

        db.close();
        return null;
    }

    public void deleteAllNotes() {
        String sqlDelete = "DELETE FROM " + TABLE_NOTES;
        Log.d(TAG, "deleteAllContacts: " + sqlDelete);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }
}
