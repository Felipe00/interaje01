package br.com.interaje.interaje01.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by lacroiix on 02/12/15.
 */
public class DialogPopUp {

    private AlertDialog.Builder dialog;
    private final Context context;

    public DialogPopUp(Context context) {
        this.dialog = new AlertDialog.Builder(context);
        this.context = context;
    }

    public void createDialog(String title, String msg) {
        dialog.setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                context, "Positive button", Toast.LENGTH_LONG
                        ).show();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                context, "Negative button", Toast.LENGTH_LONG
                        ).show();
                    }
                })
                .show();
    }
}
