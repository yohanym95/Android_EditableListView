package com.example.yohan.editabletextview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    EditText etname,etage,ettelno,etcity;
    Button btnAdd,btnView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname = findViewById(R.id.etName);
        etage = findViewById(R.id.etAge);
        ettelno = findViewById(R.id.etTelNo);
        etcity = findViewById(R.id.etCity);

        btnAdd = findViewById(R.id.btnadd);
        btnView = findViewById(R.id.btnview);




        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListView.class);
                startActivity(i);


            }
        });

    }

    public void addData(View v){
        String name  = etname.getText().toString();
        String age = etage.getText().toString();
        String telNo = ettelno.getText().toString();
        String city = etcity.getText().toString();

        try{

            databaseHelper db = new databaseHelper(this);
            db.open();
            db.addData(name,age,telNo,city);
            db.close();
            Toast.makeText(MainActivity.this,"Successfully saved!",Toast.LENGTH_SHORT).show();
            etname.setText("");
            etage.setText("");
            ettelno.setText("");
            etcity.setText("");


        }catch(Exception e){
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }


}
