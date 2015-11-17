package br.com.interaje.interaje01.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;

import br.com.interaje.interaje01.R;
import br.com.interaje.interaje01.model.Car;
import br.com.interaje.interaje01.util.ListCarDB;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText carName, carYear, carPrice;
    private ImageButton takePhoto;
    private Button btnAdd;
    private byte[] carPhoto;

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addCar:
                Car car = new Car();
                car.setName(carName.getText().toString());
                car.setYear(carYear.getText().toString());
                //Atenção! O que vem da View é uma String, por isso temos que
                // fazer a conversão para Double!
                car.setPrice(Double.parseDouble(carPrice.getText().toString()));
                car.setPhoto(carPhoto);

                ListCarDB.cars.add(car);
                startActivity(new Intent(this, ListCarActivity.class));
                break;

            case R.id.btn_camera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //TODO Colocar validaçao de Ok na tela ou um Negativo se ele nao tirou a foto
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
        carPhoto = stream.toByteArray();
    }
}
