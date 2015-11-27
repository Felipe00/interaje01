package br.com.interaje.interaje01.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import br.com.interaje.interaje01.R;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        servidorEduardo(d);
        //chamaThread(d);
        //chamaTask(d);
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
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                d.dismiss();
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
