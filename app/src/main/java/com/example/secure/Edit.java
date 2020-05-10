package com.example.secure;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Edit extends Activity {

    EditText editText,editText2,editText3,editText4,editText5,editText6,editText7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText6 = (EditText) findViewById(R.id.editText6);
        editText7 = (EditText) findViewById(R.id.editText7);

    }

    public void update(View view){
        try{
            SQLiteDatabase sql = openOrCreateDatabase("security",SQLiteDatabase.CREATE_IF_NECESSARY,null);

            String id = editText.getText().toString();
            String name = editText2.getText().toString();
            String mobile = editText3.getText().toString();
            String email = editText4.getText().toString();
            String emerg1 = editText5.getText().toString();
            String emerg2 = editText6.getText().toString();
            String emerg3 = editText7.getText().toString();

            if(emerg1.equals("")){
                Toast.makeText(this,"First emergency contact cannot be empty",Toast.LENGTH_LONG).show();
            }

            else if(id.equals("")){
                Toast.makeText(this,"id cannot be empty",Toast.LENGTH_LONG).show();

            }
            else if(name.equals("")){
                Toast.makeText(this,"name cannot be empty",Toast.LENGTH_LONG).show();
            }
            else if(emerg1.equals(emerg2)||emerg1.equals(emerg3)||emerg2.equals(emerg3)){
                Toast.makeText(this,"Please enter different contact numbers",Toast.LENGTH_LONG).show();
            }
            else {
                ContentValues cv = new ContentValues();

                cv.put("name", name);
                cv.put("mobile", mobile);
                cv.put("email", email);
                cv.put("emerg1", emerg1);
                cv.put("emerg2", emerg2);
                cv.put("emerg3", emerg3);

                sql.update("register", cv, "id=" + id, null);
                Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
            }



        }catch(Exception ex){
            Toast.makeText(this,"exception = "+ex,Toast.LENGTH_LONG).show();}
    }
}
