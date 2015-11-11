package br.com.interaje.interaje01.activities;

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

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private EditText carName;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Segunda maneira de receber o clique do Botão
         */
        //Inicia a View (Button)
        btnAdd = (Button) findViewById(R.id.btn_addCar);
        btnAdd.setOnClickListener(this); // THIS = refere-se a interface que implementamos: View.OnClickListener

        //Inicia a View (EditText)
        carName = (EditText) findViewById(R.id.car_name);

    }

    /**
     * Primeira maneira de receber o clique do Botão
     *
     * @param v
     */
    public void registerCar(View v) {
        String mensagem = "";
        mensagem = carName.getText().toString();
        Toast.makeText(MainActivity.this,
                "Nome do carro: " + mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addCar:
                // Coloque aqui o Toast! E retire o ONCLICK do arquivo de Layout (activity_main.xml).
                break;

        }
    }
}
