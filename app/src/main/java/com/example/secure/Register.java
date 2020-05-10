package com.example.secure;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends Activity {

    EditText editText,editText2,editText3,editText4,editText5,editText6,editText7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText6 = (EditText) findViewById(R.id.editText6);
        editText7 = (EditText) findViewById(R.id.editText7);
    }

    public void submit(View v){
        try{
            DbHandler db = new DbHandler(getApplicationContext());
            String id = editText.getText().toString();
            String nm = editText2.getText().toString();
            String mb = editText3.getText().toString();
            String em = editText4.getText().toString();
            String em1 = editText5.getText().toString();
            String em2 = editText6.getText().toString();
            String em3 = editText7.getText().toString();

            if(em1.equals("")){
                Toast.makeText(this,"First emergency contact cannot be empty",Toast.LENGTH_LONG).show();
            }

            else if(id.equals("")){
                Toast.makeText(this,"id cannot be empty",Toast.LENGTH_LONG).show();

            }
            else if(nm.equals("")){
                Toast.makeText(this,"name cannot be empty",Toast.LENGTH_LONG).show();
            }
            else if(em1.equals(em2)||em1.equals(em3)||em2.equals(em3)){
                Toast.makeText(this,"Please enter different contact numbers",Toast.LENGTH_LONG).show();
            }
            else {
                db.put(id, nm, mb, em, em1, em2, em3);
                Toast.makeText(this, "Inserted in database", Toast.LENGTH_LONG).show();
            }
        }catch(Exception ex){}
    }
}
