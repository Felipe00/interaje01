package br.com.interaje.interaje01.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.interaje.interaje01.R;
import br.com.interaje.interaje01.model.Car;
import br.com.interaje.interaje01.util.ImageHelper;

/**
 * Created by lacroiix on 13/11/15.
 */
public class CarAdapter extends BaseAdapter {

    private List<Car> list;
    private LayoutInflater mLayout;

    public CarAdapter(List<Car> carList, Context c) {
        list = carList;
        mLayout = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mLayout.inflate(R.layout.item_list_car, parent, false);

        TextView carName = (TextView) rowView.findViewById(R.id.tv_carName);
        TextView carYear = (TextView) rowView.findViewById(R.id.tv_carYear);
        TextView carPrice = (TextView) rowView.findViewById(R.id.tv_carPrice);
        ImageView carPhoto = (ImageView) rowView.findViewById(R.id.imv_photo);

        carName.setText(list.get(position).getName());
        carYear.setText(list.get(position).getYear());
        carPrice.setText(String.valueOf(list.get(position).getPrice()));

        byte[] photo = list.get(position).getPhoto();

        Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
        carPhoto.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitmap));

        return rowView;
    }
}
