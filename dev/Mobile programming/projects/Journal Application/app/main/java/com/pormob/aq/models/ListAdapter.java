package com.pormob.aq.models;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pormob.aq.R;

public class ListAdapter extends ArrayAdapter {
    private final Activity context;
    private final String[] maintitle;
    private final String[] auteur;

    public ListAdapter(Activity context, String[] maintitle, String[] auteur) {
        super(context, R.layout.my_list_view, maintitle);
        this.context = context;
        this.maintitle = maintitle;
        this.auteur = auteur;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.my_list_view, null, true);
        TextView titleText = rowView.findViewById(R.id.title);
        TextView subtitleText = rowView.findViewById(R.id.auteur);
        titleText.setText(maintitle[position]);
        subtitleText.setText(auteur[position]);
        return rowView;
    }
}