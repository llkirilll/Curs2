package com.example.acer.timeworker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapterE extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Events> objects;

    BoxAdapterE(Context context, ArrayList<Events> Events) {
        ctx = context;
        objects = Events;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.eventslist, parent, false);
        }

        Events p = getEvents(position);


        ((TextView) view.findViewById(R.id.tEvent)).setText(p.event);
        ((TextView) view.findViewById(R.id.tBegin)).setText(p.begin);
        return view;
    }

    Events getEvents(int position) {
        return ((Events) getItem(position));
    }

}
