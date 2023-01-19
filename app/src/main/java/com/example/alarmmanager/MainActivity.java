package com.example.alarmmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int Alarm_Req_Code = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//type castkar rhy Q k alarm service kis say a rha alarm manager say

        //on click listner can be set on multiple viewsso we dont have to typecast it
        findViewById(R.id.buttonStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

                int time  = (Integer.parseInt(((EditText)(findViewById(R.id.edtTime))).getText().toString()));
                //jp time user set kary ga woh yahan say system current time millis say milay ga
                long triggerTime = System.currentTimeMillis()+(time* 1000L);//time *1000 is that we are getting timme in seconds and we want to execute our time in mili seconds so we have to execute it

                Intent iBroadCast = new Intent(MainActivity.this,MyReceiver.class);

                PendingIntent pendingIntent =PendingIntent.getBroadcast(MainActivity.this,100,iBroadCast,PendingIntent.FLAG_IMMUTABLE);

//                PendingIntent pendingIntent =PendingIntent.getBroadcast(MainActivity.this,Alarm_Req_Code,iBroadCast,0);

                alarmManager.set(AlarmManager.RTC_WAKEUP,triggerTime,pendingIntent);

            }
        });
        findViewById(R.id.buttonStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

                Intent iBroadCast = new Intent(MainActivity.this,MyReceiver.class);

                PendingIntent pendingIntent =PendingIntent.getBroadcast(MainActivity.this,100,iBroadCast,PendingIntent.FLAG_IMMUTABLE);

                alarmManager.cancel(pendingIntent);
                Toast.makeText(MainActivity.this, "Alarm disabled", Toast.LENGTH_SHORT).show();

            }
        });
    }
}