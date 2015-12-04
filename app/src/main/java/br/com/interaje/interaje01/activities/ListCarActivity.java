package br.com.interaje.interaje01.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.interaje.interaje01.R;
import br.com.interaje.interaje01.adapter.CarAdapter;
import br.com.interaje.interaje01.database.CarDatabaseHelper;
import br.com.interaje.interaje01.database.Database;
import br.com.interaje.interaje01.model.Car;
import br.com.interaje.interaje01.repositories.CarDAO;
import br.com.interaje.interaje01.repositories.impl.CarDAOImpl;

public class ListCarActivity extends AppCompatActivity {

    private ListView listViewCar;
    private CarDAO dao;
    private Database database;

    //Menu Attributes
    private List<String> items;
    private Menu menu;

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
                startActivity(new Intent(ListCarActivity.this, DetailActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_list_car, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        try {

            SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
            searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    //Pesquisar algo aqui quando apertar o botão de enviar
                    loadHistory(new ArrayList<String>());
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    //Pesquisar algo aqui quando o texto mudar
                    return false;
                }
            });
        } catch (Exception e) {
            e.getCause();
            e.printStackTrace();
        }

        return true;
    }

    // History
    private void loadHistory(List<String> carNames) {
        //TODO criar a busca ao banco aqui
        CarAdapter adapter = new CarAdapter(getCars(), ListCarActivity.this);
    }

}
