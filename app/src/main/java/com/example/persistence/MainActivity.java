package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    private Intent intent;

    private TextView textName;
    private TextView textTaste;
    private TextView textColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.databaseHelper = new DatabaseHelper(this);
        this.database = this.databaseHelper.getWritableDatabase();
        this.textName = findViewById(R.id.edit_name);
        this.textTaste = findViewById(R.id.edit_taste);
        this.textColor = findViewById(R.id.edit_color);

        this.intent = new Intent(MainActivity.this, ReadActivity.class);
        this.intent.setAction(Intent.ACTION_SEND);
    }

    public void writeToDatabase(View view) {
        boolean success = addCandy();

        if (success) {
            textName.setText("");
            textTaste.setText("");
            textColor.setText("");

            hideKeyboard();
        }

    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void readFromDatabase(View view) {
        // Read from database

        List<CandyItem> candies = getAllCandies();

        ArrayList<String> candyStrings = new ArrayList<String>();

        for (CandyItem candy : candies) {
            candyStrings.add(candy.toString());
        }

        intent.putStringArrayListExtra("data", candyStrings);
        startActivity(intent);
    }

    private boolean addCandy() {
        if (textName.getText().toString().equals("")) {
            Log.e("Write", "Primary key can not be empty string or null");
            return false;
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseTables.Candy.COLUMN_NAME, textName.getText().toString());
        values.put(DatabaseTables.Candy.COLUMN_TASTE, textTaste.getText().toString());
        values.put(DatabaseTables.Candy.COLUMN_COLOR, textColor.getText().toString());

        database.insert(DatabaseTables.Candy.TABLE_NAME, null, values);

        return true;
    }

    private List<CandyItem> getAllCandies() {
        Cursor cursor = database.query(DatabaseTables.Candy.TABLE_NAME, null, null, null, null, null, null);
        List<CandyItem> candies = new ArrayList<>();

        while(cursor.moveToNext()) {
            CandyItem candy = new CandyItem(
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Candy.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Candy.COLUMN_TASTE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Candy.COLUMN_COLOR))
            );
            candies.add(candy);
        }
        cursor.close();
        return candies;
    }

    public void removeAllRows(View view) {
        try {
            database.execSQL(DatabaseTables.SQL_DELETE_TABLE_CANDY_IF_EXISTS);
            database.execSQL(DatabaseTables.SQL_CREATE_TABLE_CANDY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
