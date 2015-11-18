package br.com.interaje.interaje01.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lacroiix on 18/11/15.
 */
public class CarDatabaseHelper extends SQLiteOpenHelper {

    public static final String COLUMN_ID = "_id";
    public final static String COLUMN_NAME = "name";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_PHOTO = "photo";

    private static final String CREATE_TABLE_CAR = "create table car"
            + "("
            + "_id" + " integer primary key autoincrement, "
            + "name" + " text not null, "
            + "year" + " text, "
            + "price" + " real, "
            + "photo" + " blob " + ");";

    private static final String DATABASE_NAME = "car.db";
    private static final int DATABASE_VERSION = 1;

    public CarDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("atualize seu database aqui");
    }
}
