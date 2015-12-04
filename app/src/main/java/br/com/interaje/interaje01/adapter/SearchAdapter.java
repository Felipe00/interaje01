package br.com.interaje.interaje01.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.interaje.interaje01.R;

/**
 * Created by lacroiix on 04/12/15.
 */
public class SearchAdapter extends CursorAdapter {

    private List<String> items;

    private TextView text;

    public SearchAdapter(Context context, Cursor cursor, List<String> items) {
        super(context, cursor, false);

        this.items = items;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        text.setText(items.get(cursor.getPosition()));

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_list_search, parent, false);

        text = (TextView) view.findViewById(R.id.tv_search_name);

        return view;

    }
}
