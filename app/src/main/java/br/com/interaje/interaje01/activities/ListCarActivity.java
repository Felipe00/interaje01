package br.com.interaje.interaje01.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import br.com.interaje.interaje01.R;
import br.com.interaje.interaje01.adapter.CarAdapter;
import br.com.interaje.interaje01.util.ListCarDB;

public class ListCarActivity extends Activity {

    private ListView listViewCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_car);

        listViewCar = (ListView) findViewById(R.id.list);
        CarAdapter adapter = new CarAdapter(ListCarDB.cars, this);
        listViewCar.setAdapter(adapter);
    }

}
