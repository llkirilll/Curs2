package com.example.acer.timeworker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public String nowTime;

    SharedPreferences fWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread ThreadTime = null;
        Runnable runnable = new CountDownRunner();
        ThreadTime = new Thread(runnable);
        ThreadTime.start();

        listred();
        buttonListener();
    }

    private void Note() {
        Intent resultIntentW = new Intent(this, WeekActivity.class);
        PendingIntent resultPendingIntentW = PendingIntent.getActivity(this, 0, resultIntentW,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builderW =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setContentTitle("Ваша еженедельная деятельность")
                        .setContentText(fWeek.getString("WeekWork","")+
                                " "+fWeek.getString("timeTo",""))
                        .setContentIntent(resultPendingIntentW)
                        .setWhen(System.currentTimeMillis());

        Notification notificationW = builderW.build();

        NotificationManager notificationManagerW =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManagerW.notify(1, notificationW);


        Intent resultIntentE = new Intent(this, EventActivity.class);
        PendingIntent resultPendingIntentE = PendingIntent.getActivity(this, 0, resultIntentE,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builderE =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setContentTitle("Ваша разовая деятельность")
                        .setContentText(fWeek.getString("EventWork","")+
                                " "+fWeek.getString("eventTime",""))
                        .setContentIntent(resultPendingIntentE)
                        .setWhen(System.currentTimeMillis());

        Notification notificationE = builderE.build();

        NotificationManager notificationManagerE =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManagerE.notify(2, notificationE);
    }

    public void listred() {
        fWeek = getSharedPreferences("Week",MODE_PRIVATE);
        SharedPreferences.Editor ed = fWeek.edit();

        if (fWeek.getInt("Weekf",0)==0) {
            ed.putInt("Weekf", 1);
        }
        if (fWeek.getInt("Weekn",0)==0) {
            ed.putInt("Weekn", 1);
        }

        for(int i=0;i<7;i++){
            switch(i){
                case 0:ed.putString("Weekd0","Понедельник");
                case 1:ed.putString("Weekd1","Вторник");
                case 2:ed.putString("Weekd2","Среда");
                case 3:ed.putString("Weekd3","Четверг");
                case 4:ed.putString("Weekd4","Пятница");
                case 5:ed.putString("Weekd5","Суббота");
                case 6:ed.putString("Weekd6","Воскресенье");
            }
        }

        ed.apply();
    }

    public void buttonListener(){
        Button btn_m_to_w = (Button)findViewById(R.id.buttonMTW);
        Button btn_m_to_e = (Button)findViewById(R.id.buttonMTE);

        btn_m_to_w.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".WeekActivity");
                        startActivity(intent);
                    }
                }
        );

        btn_m_to_e.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".EventActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    public void timeCheck(){
        runOnUiThread(new Runnable(){
            public void run(){
                try{
                    TextView timeText = (TextView)findViewById(R.id.textView);
                    Calendar c = Calendar.getInstance();
                    nowTime = c.get(Calendar.YEAR)+"-";
                    if(c.get(Calendar.MONTH)<10){
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
                        nowTime=nowTime+"0"+c.get(Calendar.SECOND)+"";
                    }else{
                        nowTime=nowTime+c.get(Calendar.SECOND)+"";
                    }
                    timeText.setText(nowTime);
                    timeText.setGravity(Gravity.CENTER);

                    int dayofweek = (c.get(Calendar.DAY_OF_WEEK))-2;
                    if(dayofweek==-1){
                        dayofweek=6;
                    }

                    SharedPreferences.Editor ed = fWeek.edit();
                    if((nowTime.substring(11).equals("23:59:59"))&(fWeek.getInt("Weekf",0)==2)&(dayofweek==6)){
                        if(fWeek.getInt("Weekn",0)==1){
                            ed.putInt("Weekn",2);
                        }
                        if(fWeek.getInt("Weekn",0)==2){
                            ed.putInt("Weekn",1);
                        }
                    }

                    String worktimeb="s";
                    String worktimee="";

                    if(fWeek.getInt("Weekf",0)==1){
                        int i=dayofweek;
                        while(worktimeb.equals("s")){
                            if(!(fWeek.getString("OneWeekb"+i,"").equals(""))){
                                worktimeb=fWeek.getString("OneWeekb"+i,"");
                                worktimee=fWeek.getString("OneWeeke"+i,"");
                            }else{
                                i++;
                                if(i==7){
                                    i=0;
                                }
                                if(i==dayofweek){
                                    worktimeb="";
                                    worktimee="";
                                }
                            }
                        }
                        if(worktimeb==""){
                            TextView timeText2 = (TextView)findViewById(R.id.textView2);
                            TextView timeText3 = (TextView)findViewById(R.id.textView3);
                            timeText2.setText("");
                            timeText3.setText("");
                            ed.putString("WeekWork","Не задана еженедельная занятость");
                            ed.putString("timeTo","");
                            ed.apply();
                        }else{
                            TextView timeText2 = (TextView)findViewById(R.id.textView2);
                            TextView timeText3 = (TextView)findViewById(R.id.textView3);
                            String nowT = nowTime.substring(11);
                            if((nowT.compareTo(worktimeb)<0)&(i==dayofweek)){
                                timeText2.setText(fWeek.getString("OneWeekdel"+i,""));

                                String toTime;
                                String begTime = fWeek.getString("OneWeekb"+i,"");
                                String beg;
                                String now;
                                int clock;
                                int min;
                                int sec;

                                toTime="до начала еженедельной деятельности: ";

                                beg=begTime.substring(0,2);
                                now=nowTime.substring(11,13);
                                clock=Integer.parseInt(beg)-Integer.parseInt(now);

                                beg=begTime.substring(3,5);
                                now=nowTime.substring(14,16);
                                min=Integer.parseInt(beg)-Integer.parseInt(now);
                                if (min<0) {
                                    min=60+min;
                                    clock--;
                                }

                                beg=begTime.substring(6);
                                now=nowTime.substring(17);
                                sec=Integer.parseInt(beg)-Integer.parseInt(now);
                                if (sec<0) {
                                    sec=60+sec;
                                    min=min-1;
                                    if (min < 0) {
                                        min=60+min;
                                        clock--;
                                    }
                                }

                                if(clock<10){
                                    toTime=toTime+"0"+clock+":";
                                }else{
                                    toTime=toTime+clock+":";
                                }
                                if(min<10){
                                    toTime=toTime+"0"+min+":";
                                }else{
                                    toTime=toTime+min+":";
                                }
                                if(sec<10){
                                    toTime=toTime+"0"+sec;
                                }else{
                                    toTime=toTime+sec;
                                }
                                timeText3.setText(toTime);
                                ed.putString("WeekWork",fWeek.getString("OneWeekdel"+i,""));
                                ed.putString("timeTo",toTime.substring(0,10)+toTime.substring(38));
                            }

                            if((nowT.compareTo(worktimeb)>0)&(nowT.compareTo(worktimee)<0)&(i==dayofweek)){
                                timeText2.setText(fWeek.getString("OneWeekdel"+i,""));

                                String toTime;
                                String endTime = fWeek.getString("OneWeeke"+i,"");
                                String End;
                                String now;
                                int clock;
                                int min;
                                int sec;

                                toTime="до конца еженедельной деятельности: ";

                                End=endTime.substring(0,2);
                                now=nowTime.substring(11,13);
                                clock=Integer.parseInt(End)-Integer.parseInt(now);

                                End=endTime.substring(3,5);
                                now=nowTime.substring(14,16);
                                min=Integer.parseInt(End)-Integer.parseInt(now);
                                if(min<0){
                                    min=60+min;
                                    clock--;
                                }

                                End=endTime.substring(6);
                                now=nowTime.substring(17);
                                sec=Integer.parseInt(End)-Integer.parseInt(now);
                                if (sec<0) {
                                    sec=60+sec;
                                    min=min-1;
                                    if (min < 0) {
                                        min=60+min;
                                        clock--;
                                    }
                                }

                                if(clock<10){
                                    toTime=toTime+"0"+clock+":";
                                }else{
                                    toTime=toTime+clock+":";
                                }
                                if(min<10){
                                    toTime=toTime+"0"+min+":";
                                }else{
                                    toTime=toTime+min+":";
                                }
                                if(sec<10){
                                    toTime=toTime+"0"+sec;
                                }else{
                                    toTime=toTime+sec;
                                }
                                timeText3.setText(toTime);
                                ed.putString("WeekWork",fWeek.getString("OneWeekdel"+i,""));
                                ed.putString("timeTo",toTime.substring(0,10)+toTime.substring(36));
                            }

                            if(nowT.compareTo(worktimee)>0|(i!=dayofweek)){
                                if(i!=dayofweek){
                                    i=dayofweek;
                                }
                                i++;
                                if(i==7){
                                    i=0;
                                }
                                int ii=1;
                                while ((fWeek.getString("OneWeekb"+i,"").equals(""))){
                                    ii++;
                                    i++;
                                    if(i==7){
                                        i=0;
                                    }
                                }
                                timeText2.setText(fWeek.getString("OneWeekdel"+i,""));

                                String toTime;
                                String begTime = fWeek.getString("OneWeekb"+i,"");
                                String now;
                                String beg;

                                toTime="до начала следующей еженедельной деятельности: ";
                                int sec;
                                int min;
                                int clock;

                                beg=begTime.substring(0,2);
                                now=nowTime.substring(11,13);
                                clock=Integer.parseInt(beg)-Integer.parseInt(now);
                                if (clock < 0) {
                                    ii--;
                                    clock=24+clock;
                                }

                                beg=begTime.substring(3,5);
                                now=nowTime.substring(14,16);
                                min=Integer.parseInt(beg)-Integer.parseInt(now);
                                if(min<0){
                                    clock--;
                                    if(clock<0){
                                        ii--;
                                        clock=24+clock;
                                    }
                                    min=60+min;
                                }

                                beg=begTime.substring(6);
                                now=nowTime.substring(17);
                                sec=Integer.parseInt(beg)-Integer.parseInt(now);
                                if(sec<0){
                                    sec=60+sec;
                                    min--;
                                    if(min<0){
                                        clock--;
                                        if(clock<0){
                                            ii--;
                                            clock=24+clock;
                                        }
                                        min=60+min;
                                    }
                                }


                                toTime=toTime+ii+" дней ";
                                if(clock<10){
                                    toTime=toTime+"0"+clock+":";
                                }else{
                                    toTime=toTime+clock+":";
                                }
                                if(min<10){
                                    toTime=toTime+"0"+min+":";
                                }else{
                                    toTime=toTime+min+":";
                                }
                                if(sec<10){
                                    toTime=toTime+"0"+sec;
                                }else{
                                    toTime=toTime+sec;
                                }

                                timeText3.setText(toTime);
                                ed.putString("WeekWork",fWeek.getString("OneWeekdel"+i,""));
                                ed.putString("timeTo",toTime.substring(0,10)+toTime.substring(47));
                            }
                        }
                    }

                    if(fWeek.getInt("Weekf",0)==2){
                        int i=dayofweek;
                        int Weekn=fWeek.getInt("Weekn",0);
                        int conWeekn=Weekn;
                        while(worktimeb.equals("s")){
                            if (Weekn == 1) {
                                if(!(fWeek.getString("OneWeekb"+i,"").equals(""))){
                                    worktimeb=fWeek.getString("OneWeekb"+i,"");
                                    worktimee=fWeek.getString("OneWeeke"+i,"");
                                }else{
                                    i++;
                                    if(i==7){
                                        i=0;
                                        Weekn=2;
                                    }
                                    if((i==dayofweek)&(conWeekn==Weekn)){
                                        worktimeb="";
                                        worktimee="";
                                    }
                                }
                            }
                            if (Weekn == 2) {
                                if(!(fWeek.getString("TwoWeekb"+i,"").equals(""))){
                                    worktimeb=fWeek.getString("TwoWeekb"+i,"");
                                    worktimee=fWeek.getString("TwoWeeke"+i,"");
                                }else{
                                    i++;
                                    if(i==7){
                                        i=0;
                                        Weekn=1;
                                    }
                                    if((i==dayofweek)&(conWeekn==Weekn)){
                                        worktimeb="";
                                        worktimee="";
                                    }
                                }
                            }
                        }

                        if(worktimeb==""){
                            TextView timeText2 = (TextView)findViewById(R.id.textView2);
                            TextView timeText3 = (TextView)findViewById(R.id.textView3);
                            timeText2.setText("");
                            timeText3.setText("");
                            ed.putString("WeekWork","Не задана еженедельная занятость");
                            ed.putString("timeTo","");
                        }else{
                            TextView timeText2 = (TextView)findViewById(R.id.textView2);
                            TextView timeText3 = (TextView)findViewById(R.id.textView3);
                            String nowT = nowTime.substring(11);
                            if((nowT.compareTo(worktimeb)<0)&(i==dayofweek)&(conWeekn==Weekn)){
                                String toTime;
                                String beg;
                                String now;
                                String begTime="";
                                int clock;
                                int min;
                                int sec;

                                if(Weekn==1) {
                                    timeText2.setText(fWeek.getString("OneWeekdel" + i, ""));
                                    begTime = fWeek.getString("OneWeekb"+i,"");
                                    ed.putString("WeekWork",fWeek.getString("OneWeekdel"+i,""));
                                }
                                if(Weekn==2) {
                                    timeText2.setText(fWeek.getString("TwoWeekdel" + i, ""));
                                    begTime = fWeek.getString("TwoWeekb"+i,"");
                                    ed.putString("WeekWork",fWeek.getString("TwoWeekdel"+i,""));
                                }

                                toTime="до начала еженедельной деятельности: ";

                                beg=begTime.substring(0,2);
                                now=nowTime.substring(11,13);
                                clock=Integer.parseInt(beg)-Integer.parseInt(now);

                                beg=begTime.substring(3,5);
                                now=nowTime.substring(14,16);
                                min=Integer.parseInt(beg)-Integer.parseInt(now);
                                if (min<0) {
                                    min=60+min;
                                    clock--;
                                }

                                beg=begTime.substring(6);
                                now=nowTime.substring(17);
                                sec=Integer.parseInt(beg)-Integer.parseInt(now);
                                if (sec<0) {
                                    sec=60+sec;
                                    min=min-1;
                                    if (min < 0) {
                                        min=60+min;
                                        clock--;
                                    }
                                }

                                if(clock<10){
                                    toTime=toTime+"0"+clock+":";
                                }else{
                                    toTime=toTime+clock+":";
                                }
                                if(min<10){
                                    toTime=toTime+"0"+min+":";
                                }else{
                                    toTime=toTime+min+":";
                                }
                                if(sec<10){
                                    toTime=toTime+"0"+sec;
                                }else{
                                    toTime=toTime+sec;
                                }
                                timeText3.setText(toTime);
                                ed.putString("timeTo",toTime.substring(0,10)+toTime.substring(38));
                            }

                            if((nowT.compareTo(worktimeb)>0)&(nowT.compareTo(worktimee)<0)&(i==dayofweek)&(conWeekn==Weekn)){
                                String toTime;
                                String endTime = "";
                                String End;
                                String now;
                                int clock;
                                int min;
                                int sec;

                                if(Weekn==1) {
                                    timeText2.setText(fWeek.getString("OneWeekdel" + i, ""));
                                    endTime = fWeek.getString("OneWeeke"+i,"");
                                    ed.putString("WeekWork",fWeek.getString("OneWeekdel"+i,""));
                                }
                                if(Weekn==2) {
                                    timeText2.setText(fWeek.getString("TwoWeekdel" + i, ""));
                                    endTime = fWeek.getString("TwoWeeke"+i,"");
                                    ed.putString("WeekWork",fWeek.getString("TwoWeekdel"+i,""));
                                }

                                toTime="до конца еженедельной деятельности: ";

                                End=endTime.substring(0,2);
                                now=nowTime.substring(11,13);
                                clock=Integer.parseInt(End)-Integer.parseInt(now);

                                End=endTime.substring(3,5);
                                now=nowTime.substring(14,16);
                                min=Integer.parseInt(End)-Integer.parseInt(now);
                                if(min<0){
                                    min=60+min;
                                    clock--;
                                }

                                End=endTime.substring(6);
                                now=nowTime.substring(17);
                                sec=Integer.parseInt(End)-Integer.parseInt(now);
                                if (sec<0) {
                                    sec=60+sec;
                                    min=min-1;
                                    if (min < 0) {
                                        min=60+min;
                                        clock--;
                                    }
                                }

                                if(clock<10){
                                    toTime=toTime+"0"+clock+":";
                                }else{
                                    toTime=toTime+clock+":";
                                }
                                if(min<10){
                                    toTime=toTime+"0"+min+":";
                                }else{
                                    toTime=toTime+min+":";
                                }
                                if(sec<10){
                                    toTime=toTime+"0"+sec;
                                }else{
                                    toTime=toTime+sec;
                                }
                                timeText3.setText(toTime);
                                ed.putString("timeTo",toTime.substring(0,10)+toTime.substring(36));
                            }

                            if(nowT.compareTo(worktimee)>0|(i!=dayofweek)|(conWeekn!=Weekn)){
                                if(i!=dayofweek){
                                    i=dayofweek;
                                }
                                if(Weekn!=conWeekn){
                                    Weekn=conWeekn;
                                }
                                i++;
                                if((i==7)&(Weekn==1)){
                                    i=0;
                                    Weekn=2;
                                }
                                if((i==7)&(Weekn==2)){
                                    i=0;
                                    Weekn=1;
                                }
                                if(Weekn==1){
                                    worktimeb=fWeek.getString("OneWeekb"+i,"");
                                }
                                if(Weekn==2){
                                    worktimeb=fWeek.getString("TwoWeekb"+i,"");
                                }
                                int ii=1;
                                while ((worktimeb.equals(""))){
                                    ii++;
                                    i++;
                                    if((i==7)&(Weekn==1)){
                                        i=0;
                                        Weekn=2;
                                    }
                                    if((i==7)&(Weekn==2)){
                                        i=0;
                                        Weekn=1;
                                    }
                                    if(Weekn==1){
                                        worktimeb=fWeek.getString("OneWeekb"+i,"");
                                    }
                                    if(Weekn==2){
                                        worktimeb=fWeek.getString("TwoWeekb"+i,"");
                                    }
                                }
                                if(Weekn==1){
                                    timeText2.setText(fWeek.getString("OneWeekdel"+i,""));
                                    ed.putString("WeekWork",fWeek.getString("OneWeekdel"+i,""));
                                }
                                if(Weekn==2){
                                    timeText2.setText(fWeek.getString("TwoWeekdel"+i,""));
                                    ed.putString("WeekWork",fWeek.getString("TwoWeekdel"+i,""));
                                }

                                String toTime;
                                String begTime = worktimeb;
                                String now;
                                String beg;

                                toTime="до начала следующей еженедельной деятельности: ";
                                int sec;
                                int min;
                                int clock;

                                beg=begTime.substring(0,2);
                                now=nowTime.substring(11,13);
                                clock=Integer.parseInt(beg)-Integer.parseInt(now);
                                if (clock < 0) {
                                    ii--;
                                    clock=24+clock;
                                }

                                beg=begTime.substring(3,5);
                                now=nowTime.substring(14,16);
                                min=Integer.parseInt(beg)-Integer.parseInt(now);
                                if(min<0){
                                    clock--;
                                    if(clock<0){
                                        ii--;
                                        clock=24+clock;
                                    }
                                    min=60+min;
                                }

                                beg=begTime.substring(6);
                                now=nowTime.substring(17);
                                sec=Integer.parseInt(beg)-Integer.parseInt(now);
                                if(sec<0){
                                    sec=60+sec;
                                    min--;
                                    if(min<0){
                                        clock--;
                                        if(clock<0){
                                            ii--;
                                            clock=24+clock;
                                        }
                                        min=60+min;
                                    }
                                }


                                toTime=toTime+ii+" дней ";
                                if(clock<10){
                                    toTime=toTime+"0"+clock+":";
                                }else{
                                    toTime=toTime+clock+":";
                                }
                                if(min<10){
                                    toTime=toTime+"0"+min+":";
                                }else{
                                    toTime=toTime+min+":";
                                }
                                if(sec<10){
                                    toTime=toTime+"0"+sec;
                                }else{
                                    toTime=toTime+sec;
                                }

                                timeText3.setText(toTime);
                                ed.putString("timeTo",toTime.substring(0,10)+toTime.substring(47));
                            }
                        }
                    }

                    if(fWeek.getInt("Eventn",0)!=0){
                        String Ebeg = fWeek.getString("BeginE0","");
                        String WorkE = fWeek.getString("Event0","");
                        TextView timeText4 = (TextView)findViewById(R.id.textView4);
                        TextView timeText5 = (TextView)findViewById(R.id.textView5);
                        timeText4.setText(WorkE);
                        timeText5.setText(Ebeg);
                        ed.putString("eventTime",Ebeg);
                        ed.putString("EventWork",WorkE);
                    }else{
                        TextView timeText4 = (TextView)findViewById(R.id.textView4);
                        TextView timeText5 = (TextView)findViewById(R.id.textView5);
                        timeText4.setText("");
                        timeText5.setText("");
                        ed.putString("eventTime","Не задана разовая деятельность");
                        ed.putString("EventWork","");
                    }
                    ed.apply();
                    Note();


                }catch(Exception e){
                }
            }
        });
    }

    class CountDownRunner implements Runnable{
        @Override
        public void run(){
            while(!Thread.currentThread().isInterrupted()){
                try{
                    timeCheck();
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                } catch (Exception e){
                }
            }
        }
    }
}
