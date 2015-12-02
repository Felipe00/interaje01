package br.com.interaje.interaje01.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.interaje.interaje01.R;
import br.com.interaje.interaje01.adapter.PhotoAdapter;

public class PhotoActivity extends AppCompatActivity {

    private ListView lv_photos;
    private PhotoAdapter adapter;
    private List<Bitmap> listPhotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        lv_photos = (ListView) findViewById(R.id.lv_photos);

        adapter = new PhotoAdapter(listPhotos, this);
        lv_photos.setAdapter(adapter);
    }

    public void takePhoto(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");

            listPhotos.add(photo);
            adapter.updateReceiptsList(listPhotos);
        }

    }

    public static void coolDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pesquisa!")
                .setMessage("Abacaxis vao dominar o mundo?")
                .setPositiveButton("Sim?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Corram para as colinas!", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Nao mesmo!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, ">:) Vamos detonar uns abacaxis!", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }
}
