package br.com.interaje.interaje01.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.interaje.interaje01.database.CarDatabaseHelper;
import br.com.interaje.interaje01.database.Database;
import br.com.interaje.interaje01.model.Car;
import br.com.interaje.interaje01.repositories.CarDAO;

/**
 * Created by lacroiix on 18/11/15.
 */
public class CarDAOImpl implements CarDAO {

    /**
     * Inserir
     */
    @Override
    public void insert(Car car, Database database) {
        ContentValues cv = new ContentValues();
        database.open();
        try {
            if (car != null) {
                cv.put(CarDatabaseHelper.COLUMN_ID, car.getId());
                cv.put(CarDatabaseHelper.COLUMN_NAME, car.getName());
                cv.put(CarDatabaseHelper.COLUMN_YEAR, car.getYear());
                cv.put(CarDatabaseHelper.COLUMN_PRICE, car.getPrice());
                cv.put(CarDatabaseHelper.COLUMN_PHOTO, car.getPhoto());
                database.get().insert("car", null, cv);
            }
        } catch (Exception e) {
            Log.d("CarDAOImpl",
                    "Method: insert().\nErro ao inserir dados do banco. Causa: "
                            + e.getCause());
        } finally {
            database.close();
        }

    }

    /**
     * Listar todos
     */
    @Override
    public List<Car> findAll(Context context, Database database) {
        Cursor cursor = null;
        database.open();

        try {
            cursor = database.get().rawQuery("select * from car", null);
            //Exemplo Usando Like
                /*cursor = database.get().rawQuery(
                        "select * from car where name like '%" + name
                                + "%' limit 15;", null);*/
        } catch (Exception e) {
            Log.d("CarDAOImpl",
                    "Method: findall().\nErro ao recuperar dados do banco. Causa: "
                            + e.getMessage());
        }


        List<Car> listEntity = null;
        Car entity;
        cursor.moveToFirst();

        if (cursor != null && !cursor.isClosed()) {
            listEntity = new ArrayList<Car>();

            for (int  i = 0; i < cursor.getCount(); i++) {

            }
            while (cursor.moveToNext()) {
                entity = new Car();
                entity.setId(cursor.getLong(cursor.getColumnIndex(CarDatabaseHelper.COLUMN_ID)));
                entity.setName(cursor.getString(cursor.getColumnIndex(CarDatabaseHelper.COLUMN_NAME)));
                entity.setYear(cursor.getString(cursor.getColumnIndex(CarDatabaseHelper.COLUMN_YEAR)));
                entity.setPrice(cursor.getDouble(cursor.getColumnIndex(CarDatabaseHelper.COLUMN_PRICE)));
                entity.setPhoto(cursor.getBlob(cursor.getColumnIndex(CarDatabaseHelper.COLUMN_PHOTO)));
                listEntity.add(entity);
            }
            cursor.close();
        }

        database.close();
        return listEntity;
    }

    /**
     * Remover
     */
    @Override
    public void remove(Long id, Context context, Database database) {
        try {
            database.open();
            // (TABELA, COLUNA, WHERE CLAUSE)
            database.get().delete("car", "id",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d("CarDAOImpl",
                    "Method: remove().\nErro ao remover dados do banco. Causa: "
                            + e.getMessage());
        } finally {
            database.close();
        }
    }
}
