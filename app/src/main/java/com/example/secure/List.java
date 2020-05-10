package com.example.secure;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class List extends Activity implements AdapterView.OnItemSelectedListener {

    Spinner sp1;
    EditText editText,editText2,editText3,editText4,editText5,editText6,editText7;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText6 = (EditText) findViewById(R.id.editText6);
        editText7 = (EditText) findViewById(R.id.editText7);


        ArrayList<String> state = new ArrayList<String>();

        try{
            SQLiteDatabase sql = openOrCreateDatabase("security",SQLiteDatabase.CREATE_IF_NECESSARY,null);



            Cursor cursor = sql.rawQuery("SELECT * FROM register",null);




            cursor.moveToFirst();

            do{
                String u = cursor.getString(cursor.getColumnIndex("name"));
                state.add(cursor.getString(cursor.getColumnIndex("id")));

                }while (cursor.moveToNext());
        }catch(Exception ex){Toast.makeText(this,"exception = "+ex,Toast.LENGTH_LONG).show();}

        sp1 = (Spinner)findViewById(R.id.sp1);

        ArrayAdapter<String> a = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,state);

        sp1.setAdapter(a);

        sp1.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        editText.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
        editText6.setText("");
        editText7.setText("");

        sp1.setSelection(position);

        String id1 = (String)sp1.getSelectedItem();

        try{
            SQLiteDatabase sql = openOrCreateDatabase("security",SQLiteDatabase.CREATE_IF_NECESSARY,null);

            Cursor cursor = sql.rawQuery("SELECT * FROM register where id = "+id1,null);

            cursor.moveToFirst();

            editText.append(cursor.getString(cursor.getColumnIndex("id")));
            editText2.append(cursor.getString(cursor.getColumnIndex("name")));
            editText3.append(cursor.getString(cursor.getColumnIndex("mobile")));
            editText4.append(cursor.getString(cursor.getColumnIndex("email")));
            editText5.append(cursor.getString(cursor.getColumnIndex("emerg1")));
            editText6.append(cursor.getString(cursor.getColumnIndex("emerg2")));
            editText7.append(cursor.getString(cursor.getColumnIndex("emerg3")));


        }catch(Exception e){}
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
