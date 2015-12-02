package br.com.interaje.interaje01.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.interaje.interaje01.R;
import br.com.interaje.interaje01.adapter.CarAdapter;
import br.com.interaje.interaje01.database.CarDatabaseHelper;
import br.com.interaje.interaje01.database.Database;
import br.com.interaje.interaje01.model.Car;
import br.com.interaje.interaje01.repositories.CarDAO;
import br.com.interaje.interaje01.repositories.impl.CarDAOImpl;

public class ListCarActivity extends Activity {

    private ListView listViewCar;
    private CarDAO dao;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car);

        listViewCar = (ListView) findViewById(R.id.list);
        CarAdapter adapter = new CarAdapter(getCars(), this);
        listViewCar.setAdapter(adapter);
        listViewCar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(ListCarActivity.this, DetailActivity.class);
                intent.putExtra("","");*/
                startActivity(new Intent(ListCarActivity.this, DetailActivity.class));
            }
        });
    }

    /**
     * Retorna uma lista de carros do Bando de dados
     *
     * @return List<Car>
     */
    public List<Car> getCars() {
        database = new Database(new CarDatabaseHelper(this));
        dao = new CarDAOImpl();

        return dao.findAll(this, database);
    }

    public void goAddView(View v) {
        //PhotoActivity.coolDialog(this);
        startActivity(new Intent(this, MainActivity.class));
    }

}
