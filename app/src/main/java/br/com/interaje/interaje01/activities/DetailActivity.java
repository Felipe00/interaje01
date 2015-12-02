package br.com.interaje.interaje01.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import br.com.interaje.interaje01.R;
import br.com.interaje.interaje01.util.PrefsManager;

public class DetailActivity extends Activity {

    private PrefsManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        session = new PrefsManager(this);
        session.addSessionParam("testParam", "Meu Valor");
    }

    public void editCar(View view) {
        Toast.makeText(this, session.getSessionParam("personalMessage"), Toast.LENGTH_LONG).show();
    }

}
