package com.example.secure;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i_startservice=new Intent(MainActivity.this,BgService.class);
        startService(i_startservice);
    }

    public void list(View v)
    {
        Intent intent = new Intent(this,List.class);
        startActivity(intent);
    }

    public void register(View v)
    {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    public void edit(View v)
    {
        Intent intent = new Intent(this,Edit.class);
        startActivity(intent);
    }

    public void message(View v)
    {

        GPSTracker gps = new GPSTracker(MainActivity.this);
        //check if GPS is enabled

        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Toast.makeText(this,gps.getLatitude()+" and "+gps.getLongitude(),Toast.LENGTH_LONG).show();

            try{
                SQLiteDatabase sql = openOrCreateDatabase("security",SQLiteDatabase.CREATE_IF_NECESSARY,null);
                Cursor cursor = sql.rawQuery("SELECT * FROM register",null);

                cursor.moveToFirst();

                String number = cursor.getString(cursor.getColumnIndex("emerg1"));
                String number1 = cursor.getString(cursor.getColumnIndex("emerg2"));
                String number2 = cursor.getString(cursor.getColumnIndex("emerg3"));
                String messageToSend = "I am in help. My location is: http://www.google.co.in/maps?f=q&q="+latitude+","+longitude;
                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
                SmsManager.getDefault().sendTextMessage(number1, null, messageToSend, null,null);
                SmsManager.getDefault().sendTextMessage(number2, null, messageToSend, null,null);

            }catch(Exception e){}

        }

    }

    public void call(View v){

        try {
            SQLiteDatabase sql = openOrCreateDatabase("security",SQLiteDatabase.CREATE_IF_NECESSARY,null);
            Cursor cursor = sql.rawQuery("SELECT * FROM register",null);

            cursor.moveToFirst();
            String number = cursor.getString(cursor.getColumnIndex("emerg1"));

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+number));
            startActivity(callIntent);
        }catch (Exception e){}
    }
}
