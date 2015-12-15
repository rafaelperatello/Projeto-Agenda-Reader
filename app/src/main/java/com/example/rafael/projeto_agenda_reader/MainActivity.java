package com.example.rafael.projeto_agenda_reader;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String AUTHORITY = "br.edu.ifspsaocarlos.agenda.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/contatos");
    public static final String KEY_NAME = "nome";
    public static final String KEY_FONE = "fone";
    public static final String KEY_FONE2 = "fone2";
    public static final String KEY_BIRTHDAY = "aniversario";
    public static final String KEY_EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Insert
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, "Reader");
        values.put(KEY_FONE, "123456");
        values.put(KEY_FONE2, "654321");
        values.put(KEY_BIRTHDAY, "01/03/05");
        values.put(KEY_EMAIL, "reader@reader.com.br");

        Uri ins = getContentResolver().insert(CONTENT_URI, values);
        Log.d("READER:", "Insert return: " + ins);

        //Query all
        Cursor cursor=getContentResolver().query(CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.d("READER:", cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5));
        }

        //Delete
        int result = getContentResolver().delete(ins, null, null);
        Log.d("READER:", "Delete last insert: " + result);

        //Update
        ContentValues valuesUpdate = new ContentValues();
        valuesUpdate.put(KEY_NAME, "João da Silva");

        int resultUpdate = getContentResolver().update(CONTENT_URI, valuesUpdate ,"id=4", null);
        Log.d("READER:", "Update result: " + result);
    }
}
