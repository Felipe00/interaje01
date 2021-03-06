package br.com.interaje.interaje01.util;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.interaje.interaje01.model.Car;

/**
 * Created by lacroiix on 18/11/15.
 */
public class PrefsManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor para Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;

    // Nome do aqruivo Sharedpref
    private static final String PREF_NAME = "Interaje01Prefs";


    // Constructor
    public PrefsManager(Context c) {
        this.context = c;
        pref = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = pref.edit();
    }

    /**
     * Add BOOLEAN params to session.
     *
     * @param param
     * @param value
     */
    public void addSessionParam(String param, String value) {
        editor.putString(param, value);
        editor.commit();
    }

    public String getSessionParam(String keyParam) {
        return pref.getString(keyParam, "valor padrão");
    }

}
