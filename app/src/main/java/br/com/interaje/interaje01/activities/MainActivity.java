package br.com.interaje.interaje01.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.interaje.interaje01.R;
import br.com.interaje.interaje01.model.Car;
import br.com.interaje.interaje01.util.ListCarDB;

public class MainActivity extends Activity
        implements View.OnClickListener {

    private EditText carName, carYear, carPrice;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Inicia a View (Button)
        btnAdd = (Button) findViewById(R.id.btn_addCar);
        btnAdd.setOnClickListener(this);

        //Inicia a View (EditText)
        carName = (EditText) findViewById(R.id.car_name);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addCar:
                Car car = new Car();
                car.setName(carName.getText().toString());
                car.setYear("");
                car.setPrice(1.1);

                ListCarDB.cars.add(car);
                startActivity(new Intent(this, ListCarActivity.class));
                break;
        }
    }
}
