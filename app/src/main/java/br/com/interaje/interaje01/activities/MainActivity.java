package br.com.interaje.interaje01.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import br.com.interaje.interaje01.R;
import br.com.interaje.interaje01.database.CarDatabaseHelper;
import br.com.interaje.interaje01.database.Database;
import br.com.interaje.interaje01.model.Car;
import br.com.interaje.interaje01.repositories.CarDAO;
import br.com.interaje.interaje01.repositories.impl.CarDAOImpl;
import br.com.interaje.interaje01.util.ListCarDB;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText carName, carYear, carPrice;
    private ImageButton takePhoto;
    private Button btnAdd;
    private CarDAO dao;
    private Database database;
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takePhoto = (ImageButton) findViewById(R.id.btn_camera);
        takePhoto.setOnClickListener(this);

        //Inicia a View (Button)
        btnAdd = (Button) findViewById(R.id.btn_addCar);
        btnAdd.setOnClickListener(this);

        //Inicia a View (EditText)
        carName = (EditText) findViewById(R.id.car_name);
        carYear = (EditText) findViewById(R.id.car_year);
        carPrice = (EditText) findViewById(R.id.car_price);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            carName.setHint("Carro Maluxo..");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //startActivity(new Intent(MainActivity.this, ListCarActivity.class));
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addCar:
                car = new Car();
                car.setName(carName.getText().toString());
                car.setYear(carYear.getText().toString());
                //Atenção! O que vem da View é uma String, por isso temos que
                // fazer a conversão para Double!
                car.setPrice(Double.parseDouble(carPrice.getText().toString()));
                //car.setPhoto(carPhoto);

                Handler handler = new Handler();
                //handler.post(runnable);

                //insertCar(car);
                break;

            case R.id.btn_camera:
                startActivity(new Intent(this, PhotoActivity.class));
                break;
        }
    }

    private void insertCar(Car car) {
        database = new Database(new CarDatabaseHelper(this));
        dao = new CarDAOImpl();

        dao.insert(car, database);
    }


}
