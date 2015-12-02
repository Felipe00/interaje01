package br.com.interaje.interaje01.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.interaje.interaje01.R;
import br.com.interaje.interaje01.util.PrefsManager;
import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_send = (Button) findViewById(R.id.btn_send);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View v) {
        final ProgressDialog d = dialogLogin();
        d.show();

        //servidorEduardo(d);
        //chamaThread(d);
        chamaTask(d);

    }


    private void servidorEduardo(final ProgressDialog d) {
        RequestParams params = new RequestParams();
        params.add("valor", "Felipe");

        AsyncHttpClient client = new AsyncHttpClient();
        final Context context = LoginActivity.this;
        client.post("http://192.168.1.138:8080/GuiaShop/Vendedor/ValidarAcesso", params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String decoded = new String(responseBody);
                        Toast.makeText(context, "Resposta: " + decoded, Toast.LENGTH_SHORT).show();
                        d.dismiss();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(context, "Falhou, deu esse erro: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        d.dismiss();
                    }

                });
    }

    private void chamaTask(final ProgressDialog d) {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    // Faça a chamada aqui!
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                PrefsManager session = new PrefsManager(LoginActivity.this);
                session.addSessionParam("username","Felipe Costa");
                session.addSessionParam("email","email@email.com");
                session.addSessionParam("personalMessage","Tenha um ótimo dia!");
                d.dismiss();
                startActivity(new Intent(LoginActivity.this, ListCarActivity.class));
            }
        };
        task.execute();
    }

    private void chamaThread(final ProgressDialog d) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                d.dismiss();
                startActivity(new Intent(LoginActivity.this, ListCarActivity.class));
            }
        }).start();
    }

    public ProgressDialog dialogLogin() {
        ProgressDialog dialog =
                ProgressDialog.show(
                        LoginActivity.this, "Aguarde..", "Conectando ao servidor.", true);
        dialog.setCancelable(true);
        return dialog;
    }
}
