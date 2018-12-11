package com.example.acer.timeworker;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.util.ArrayList;

public class WeekActivity extends AppCompatActivity {

    SharedPreferences fWeek;
    RadioGroup radioGroup;

    ArrayList<Day> daysOne = new ArrayList<Day>();
    ArrayList<Day> daysTwo = new ArrayList<Day>();
    BoxAdapter boxAdapter;
    BoxAdapter boxAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        fWeek = getSharedPreferences("Week",MODE_PRIVATE);

        cheked();
        Change();
        listListener1();
        listListener2();
        switchListener();
    }

    private void switchListener() {
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor ed = fWeek.edit();
                if(isChecked){
                    ed.putInt("Weekn",2);
                    ed.apply();
                }else{
                    ed.putInt("Weekn",1);
                    ed.apply();
                }
            }
        });
    }

    private void listListener2() {
        ListView listTwoWeek = (ListView) findViewById(R.id.listTwoW);
        listTwoWeek.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SharedPreferences.Editor ed = fWeek.edit();
                        EditText begt = (EditText)findViewById(R.id.begTextW);
                        EditText endt = (EditText)findViewById(R.id.endTextW);
                        EditText workt = (EditText)findViewById(R.id.workTextW);
                        String beg = begt.getText().toString();
                        String end = endt.getText().toString();
                        String work = workt.getText().toString();
                        begt.setText("");
                        workt.setText("");
                        endt.setText("");
                        ed.putString("TwoWeekb"+position,beg);
                        ed.putString("TwoWeeke"+position,end);
                        ed.putString("TwoWeekdel"+position,work);
                        ed.apply();
                        list();
                    }
                }
        );

        listTwoWeek.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        SharedPreferences.Editor ed = fWeek.edit();
                        ed.remove("TwoWeekb"+position);
                        ed.remove("TwoWeeke"+position);
                        ed.remove("TwoWeekdel"+position);
                        ed.apply();
                        list();
                        return false;
                    }
                }
        );
    }

    private void listListener1() {
        ListView listOneWeek = (ListView) findViewById(R.id.listOneW);

        listOneWeek.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SharedPreferences.Editor ed = fWeek.edit();
                        EditText begt = (EditText)findViewById(R.id.begTextW);
                        EditText endt = (EditText)findViewById(R.id.endTextW);
                        EditText workt = (EditText)findViewById(R.id.workTextW);
                        String beg = begt.getText().toString();
                        String end = endt.getText().toString();
                        String work = workt.getText().toString();
                        begt.setText("");
                        workt.setText("");
                        endt.setText("");
                        ed.putString("OneWeekb"+position,beg);
                        ed.putString("OneWeeke"+position,end);
                        ed.putString("OneWeekdel"+position,work);
                        ed.apply();
                        list();
                    }
                }
        );

        listOneWeek.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        SharedPreferences.Editor ed = fWeek.edit();
                        ed.remove("OneWeekb"+position);
                        ed.remove("OneWeeke"+position);
                        ed.remove("OneWeekdel"+position);
                        ed.apply();
                        list();
                        return false;
                    }
                }
        );
    }

    public void cheked() {
        RadioButton radone = (RadioButton)findViewById(R.id.RadOne);
        RadioButton radtwo = (RadioButton)findViewById(R.id.RadTwo);

        Switch switch1 = (Switch) findViewById(R.id.switch1);
        if(fWeek.getInt("Weekf",0)==1){
            radone.setChecked(true);
        }
        if(fWeek.getInt("Weekf",0)==2){
            radtwo.setChecked(true);
        }
        if(fWeek.getInt("Weekn",0)==1){
            switch1.setChecked(false);
        }
        if(fWeek.getInt("Weekn",0)==2){
            switch1.setChecked(true);
        }
        list();
    }

    public void list() {
        daysOne.clear();
        daysTwo.clear();
        if(fWeek.getInt("Weekf",0)==1) {
            for (int i = 0; i < 7; i++) {
                String day   = fWeek.getString("Weekd" + i, "");
                String begin = fWeek.getString("OneWeekb" + i, "");
                String end   = fWeek.getString("OneWeeke" + i, "");
                String works = fWeek.getString("OneWeekdel" + i, "");
                daysOne.add(new Day(day, begin, end, works));
            }
            ListView listOneWeek = (ListView) findViewById(R.id.listOneW);
            ListView listTwoWeek = (ListView) findViewById(R.id.listTwoW);

            boxAdapter = new BoxAdapter(this, daysOne);
            listOneWeek.setAdapter(boxAdapter);
            listTwoWeek.setAdapter(null);
        }

        if(fWeek.getInt("Weekf",0)==2) {
            for (int i = 0; i < 7; i++) {
                String day   = fWeek.getString("Weekd" + i, "");
                String begin = fWeek.getString("OneWeekb" + i, "");
                String end   = fWeek.getString("OneWeeke" + i, "");
                String works = fWeek.getString("OneWeekdel" + i, "");
                daysOne.add(new Day(day, begin, end, works));
            }

            for (int i = 0; i < 7; i++) {
                String day   = fWeek.getString("Weekd" + i, "");
                String begin = fWeek.getString("TwoWeekb" + i, "");
                String end   = fWeek.getString("TwoWeeke" + i, "");
                String works = fWeek.getString("TwoWeekdel" + i, "");
                daysTwo.add(new Day(day, begin, end, works));
            }

            ListView listOneWeek = (ListView) findViewById(R.id.listOneW);
            ListView listTwoWeek = (ListView) findViewById(R.id.listTwoW);

            boxAdapter = new BoxAdapter(this, daysOne);
            listOneWeek.setAdapter(boxAdapter);
            boxAdapter2 = new BoxAdapter(this, daysTwo);
            listTwoWeek.setAdapter(boxAdapter2);
        }
    }

    public void Change(){
        radioGroup = (RadioGroup)findViewById(R.id.ChangW);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences.Editor ed = fWeek.edit();
                switch (checkedId){
                    case R.id.RadOne:
                        ed.putInt("Weekf",1);
                        ed.apply();
                        list();
                        break;
                    case R.id.RadTwo:
                        ed.putInt("Weekf",2);
                        ed.apply();
                        list();
                        break;
                }
            }
        });
    }
}
