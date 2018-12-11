package com.example.acer.timeworker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Day> objects;

    BoxAdapter(Context context, ArrayList<Day> Day) {
        ctx = context;
        objects = Day;
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
            view = lInflater.inflate(R.layout.weeklist, parent, false);
        }

        Day p = getDay(position);


        ((TextView) view.findViewById(R.id.tDay)).setText(p.day);
        ((TextView) view.findViewById(R.id.tBegin)).setText(p.begin);
        ((TextView) view.findViewById(R.id.tEnd)).setText(p.finish);
        ((TextView) view.findViewById(R.id.tDela)).setText(p.dela);
        return view;
    }

    Day getDay(int position) {
        return ((Day) getItem(position));
    }

}
