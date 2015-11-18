package br.com.interaje.interaje01.repositories;

import android.content.Context;

import java.util.List;

import br.com.interaje.interaje01.database.Database;
import br.com.interaje.interaje01.model.Car;

/**
 * Created by lacroiix on 18/11/15.
 */
public interface CarDAO {

    void insert(Car car, Database database);

    List<Car> findAll(Context context, Database database);

    void remove(Long id, Context context, Database database);
}
