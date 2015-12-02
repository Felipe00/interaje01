package br.com.interaje.interaje01.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import br.com.interaje.interaje01.R;

/**
 * Created by lacroiix on 01/12/15.
 */
public class PhotoAdapter extends BaseAdapter {

    List<Bitmap> photos;
    LayoutInflater layoutInflater;

    public PhotoAdapter(List<Bitmap> photos, Context c) {
        this.photos = photos;
        layoutInflater = layoutInflater.from(c);
    }

    public void updateReceiptsList(List<Bitmap> newlist) {
        photos = newlist;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1l;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.item_list_photo, parent, false);

        ImageView iv_photo = (ImageView) rowView.findViewById(R.id.img_photo_photoActivity);

        iv_photo.setImageBitmap(photos.get(position));
        return rowView;
    }
}
