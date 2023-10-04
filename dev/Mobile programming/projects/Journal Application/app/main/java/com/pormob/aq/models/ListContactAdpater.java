package com.pormob.aq.models;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.pormob.aq.R;

import java.util.ArrayList;

public class ListContactAdpater extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<User> userArrayList;

    public ListContactAdpater(Activity context, ArrayList<User> userArrayList) {
        super(context, R.layout.my_list_view, userArrayList);
        this.context = context;
        this.userArrayList = userArrayList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.my_list_view_contact, null, true);
        TextView titleText = rowView.findViewById(R.id.title);
        ImageView imageView = rowView.findViewById(R.id.image);
        if (userArrayList.get(position) != null) {
            String tempBytes = userArrayList.get(position).getBytes();
            byte[] bytes1 = Base64.decode(tempBytes, 1);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes1, 0, bytes1.length);
            RoundedBitmapDrawable bitmapDrawable = RoundedBitmapDrawableFactory.create(null, bitmap);
            bitmapDrawable.setCircular(true);
            imageView.setImageDrawable(bitmapDrawable);
            titleText.setText(userArrayList.get(position).getUsername());
        }
        return rowView;
    }
}