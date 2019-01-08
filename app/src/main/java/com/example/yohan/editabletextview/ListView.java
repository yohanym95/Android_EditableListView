package com.example.yohan.editabletextview;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ListView extends AppCompatActivity implements list.getItem{

    EditText etname,etage,ettelno,etcity;
    databaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        etname = findViewById(R.id.etName1);
        etage = findViewById(R.id.etAge1);
        ettelno = findViewById(R.id.etTelno1);
        etcity = findViewById(R.id.etCity1);



        FragmentManager manager = this.getSupportFragmentManager();
        manager.beginTransaction()
                .show(manager.findFragmentById(R.id.FRAGLIST))
                .hide(manager.findFragmentById(R.id.FRAGVIEW))
                .commit();




    }

    @Override
    public void ItemSelected(int index) {
        db = new databaseHelper(this);
        db.open();

      String name = db.getName1(index+1);
      String age = db.getAge(index+1);
      String telNo = db.getTelNo(index+1);
      String city = db.getCity(index+1);


       etname.setText(name);
       etage.setText(age);
       ettelno.setText(telNo);
       etcity.setText(city);



        FragmentManager manager= this.getSupportFragmentManager();


        manager.beginTransaction()
                .show(manager.findFragmentById(R.id.FRAGVIEW))
                .hide(manager.findFragmentById(R.id.FRAGLIST))
                .addToBackStack(null)
                .commit();

    }
}
