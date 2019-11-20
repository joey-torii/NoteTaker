package com.example.notetaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.provider.Settings.NameValueTable.NAME;

public class NoteOpenHelper extends SQLiteOpenHelper {

    static final String TAG = "Tag";

    // define some fields for our database
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
        // where we create tables in our database
        // construct a SQL statement to create a table to store contacts
        // CREATE TABLE tableContacts(_id INTEGER PRIMARY KEY AUTOINCREMENT,
        // name TEXT,
        // phoneNumber TEXT,
        // imageResource INTEGER)

        // create a string that represents our SQL statement
        // structured query language
        String sqlCreate = "CREATE TABLE " + TABLE_NOTES +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITLE + " TEXT, " +
                CONTENT + " TEXT, " +
                TYPE + " TEXT)";
//        Log.d(TAG, "onCreate: " + sqlCreate);
        // execute this sql statement
        sqLiteDatabase.execSQL(sqlCreate);
        // onCreate() only executes one time
        // and that is after the first call to getWritableDatabase()
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertNote(Note note) {
        // INSERT INTO tableContacts VALUES(null, 'Spike the Bulldog',
        // '509-509-5095', -1)
        String sqlInsert = "INSERT INTO " + TABLE_NOTES + " VALUES(null, '" +
                note.getTitle() + "', '" +
                note.getContent() + "', '" +
                note.getType() + "'" + ")";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlInsert);
        db.close(); // good practice to close database open for writing
    }

    public Cursor getSelectAllNotesCursor() {
        // cursor used to navigate through results from a query
        // think of the cursor like a file cursor
        // SELECT * FROM tableContacts
        String sqlSelect = "SELECT * FROM " + TABLE_NOTES;
        SQLiteDatabase db = getReadableDatabase();
        // use db.rawQuery() because its returs a Cursor
        Cursor cursor = db.rawQuery(sqlSelect, null);
        // don't close the database, the cursor needs it open

        return cursor;
    }

    // for debug purposes only!!
    // for PA7 use SimpleCursorAdapter to wire up the database to the listview
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

    public void updateContactById(int id, Note newNote) {
        String sqlUpdate = "UPDATE " + TABLE_NOTES + " SET " + TITLE + "='" +
                newNote.getTitle() + "', " + CONTENT + " '" +
                newNote.getContent() + "' WHERE " + ID + "=" + id;
//        Log.d(TAG, "updateContactById: " + sqlUpdate);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }

    public void deleteAllNotes() {
        String sqlDelete = "DELETE FROM " + TABLE_NOTES;
//        Log.d(TAG, "deleteAllContacts: " + sqlDelete);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }
}
