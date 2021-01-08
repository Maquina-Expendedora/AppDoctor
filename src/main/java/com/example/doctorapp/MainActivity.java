package com.example.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView tBienvenido;
    Button bConsultar;
    Button bRecetar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tBienvenido=(TextView)findViewById(R.id.textView);
        bConsultar=(Button)findViewById(R.id.button);
        bRecetar=(Button)findViewById(R.id.button2);

    }
    public void consultar(View view){
        Intent i=new Intent(this,consultar.class);
        startActivity(i);
    }

    public void recetar(View view){
        Intent i=new Intent(this,recetar.class);
        startActivity(i);
    }

}