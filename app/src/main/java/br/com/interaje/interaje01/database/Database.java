package br.com.interaje.interaje01.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lacroiix on 18/11/15.
 */
public class Database {

    private CarDatabaseHelper databaseManager;
    private SQLiteDatabase sqld;

    public Database(CarDatabaseHelper DatabaseManager) {
        this.databaseManager = DatabaseManager;
    }

    public void open() {
        sqld = databaseManager.getWritableDatabase();
    }

    public SQLiteDatabase get() {
        if (sqld != null && sqld.isOpen()) {
            return sqld;
        }
        return null;
    }

    public void close() {
        databaseManager.close();
    }
}
