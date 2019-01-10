package com.example.yohan.editabletextview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.SQLException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText etname,etage,ettelno,etcity;
    Button btnAdd,btnView;

    private DatabaseReference mDatabase;


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
        mDatabase = FirebaseDatabase.getInstance().getReference();




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

        HashMap<String,String> dataMap = new HashMap<>();
        dataMap.put("Name",name);
        dataMap.put("Age",age);
        dataMap.put("Tel No",telNo);
        dataMap.put("City", city);

        mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                }
            }
        });

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
