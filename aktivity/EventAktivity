package com.example.acer.timeworker;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

public class EventActivity extends AppCompatActivity {

    SharedPreferences fWeek;

    ArrayList<Events> events = new ArrayList<Events>();

    BoxAdapterE Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        fWeek = getSharedPreferences("Week",MODE_PRIVATE);

        listred();
        addlistiner();
        listListener();
    }

    private void listListener() {
        ListView listE = (ListView) findViewById(R.id.listE);

        listE.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        events.remove(position);
                        if(events.size()==0){
                            SharedPreferences.Editor ed = fWeek.edit();
                            ed.putString("Event0","");
                            ed.putString("BeginE0","");
                            ed.putInt("Eventn",0);
                            ed.apply();
                        }else {
                            for (int i = 0; i < events.size(); i++) {
                                SharedPreferences.Editor ed = fWeek.edit();
                                ed.putString("Event" + i, events.get(i).event);
                                ed.putString("BeginE" + i, events.get(i).begin);
                                ed.putInt("Eventn", events.size());
                                ed.apply();
                            }
                        }
                        sortEvents();
                    }
                }
        );
    }

    private void listred() {
        int n = fWeek.getInt("Eventn",0);
        for(int i=0;i<n;i++){
            String event = fWeek.getString("Event"+i,"нет значения");
            String begin = fWeek.getString("BeginE"+i,"нет значения");
            events.add(new Events(event,begin));
        }
        sortEvents();
    }

    private void list() {
        if(events.size()>0) {
            ListView listE = (ListView) findViewById(R.id.listE);
            Adapter = new BoxAdapterE(this, events);
            listE.setAdapter(Adapter);
        }else{
            ListView listE = (ListView) findViewById(R.id.listE);
            listE.setAdapter(null);
        }
    }

    private void sortEvents() {
        Calendar c = Calendar.getInstance();
        String nowTime = c.get(Calendar.YEAR)+"-";
        if(c.get(Calendar.MONTH)+1<10){
            nowTime=nowTime+"0"+(c.get(Calendar.MONTH)+1)+"-";
        }else{
            nowTime=nowTime+(c.get(Calendar.MONTH)+1)+"-";
        }
        if(c.get(Calendar.DAY_OF_MONTH)<10){
            nowTime=nowTime+"0"+c.get(Calendar.DAY_OF_MONTH)+" ";
        }else{
            nowTime=nowTime+c.get(Calendar.DAY_OF_MONTH)+" ";
        }
        if(c.get(Calendar.HOUR_OF_DAY)<10){
            nowTime=nowTime+"0"+c.get(Calendar.HOUR_OF_DAY)+":";
        }else{
            nowTime=nowTime+c.get(Calendar.HOUR_OF_DAY)+":";
        }
        if(c.get(Calendar.MINUTE)<10){
            nowTime=nowTime+"0"+c.get(Calendar.MINUTE)+":";
        }else{
            nowTime=nowTime+c.get(Calendar.MINUTE)+":";
        }
        if(c.get(Calendar.SECOND)<10){
            nowTime=nowTime+"0"+c.get(Calendar.SECOND);
        }else{
            nowTime=nowTime+c.get(Calendar.SECOND);
        }

        for(int i=0;i<events.size();i++){
            if(events.get(i).begin.compareTo(nowTime)<0){
                events.remove(i);
            }
        }

        if(events.size()>1) {
            for (int i = 0; i < events.size(); i++) {
                for (int j = i; j < events.size(); j++){
                    if(events.get(i).begin.compareTo(events.get(j).begin)>0){
                        Events temp=events.get(i);
                        events.set(i,events.get(j));
                        events.set(j,temp);
                    }
                }
            }
        }

        for(int i=0;i<events.size();i++){
            SharedPreferences.Editor ed = fWeek.edit();
            ed.putString("Event"+i,events.get(i).event);
            ed.putString("BeginE"+i,events.get(i).begin);
            ed.putInt("Eventn",events.size());
            ed.apply();
        }

        list();
    }

    private void addlistiner() {
        Button add = (Button)findViewById(R.id.buttonAddE);

        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText event = (EditText)findViewById(R.id.EventText);
                        EditText begin = (EditText)findViewById(R.id.beginText);
                        String even = event.getText().toString();
                        String beg  = begin.getText().toString();
                        event.setText("");
                        begin.setText("");
                        events.add(new Events(even,beg));
                        sortEvents();
                    }
                }
        );
    }
}
