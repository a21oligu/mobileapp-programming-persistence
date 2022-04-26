package com.example.persistence;

public class DatabaseTables {
    public class Candy {
        static final String COLUMN_NAME = "name"; // name = primary key
        static final String COLUMN_TASTE = "taste";
        static final String COLUMN_COLOR = "color";
        static final String TABLE_NAME = "Candy";
    }

    public static final String SQL_CREATE_TABLE_CANDIES = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT)",
            Candy.TABLE_NAME,
            Candy.COLUMN_NAME,
            Candy.COLUMN_TASTE,
            Candy.COLUMN_COLOR);

    public static final String SQL_DELETE_TABLE_CANDY_IF_EXISTS = String.format(
            "DROP TABLE IF EXISTS %s",
            Candy.TABLE_NAME);
}
