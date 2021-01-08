package com.example.doctorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class consultar extends AppCompatActivity {
    TextView tBienvenido;
    Button bregresar;
    Spinner spinner_medicinas;
    TextView txtPrecio;
    TextView txtStock;
    TextView txtCodigo;
    private DatabaseReference Medicinadb;
    String m_seleccionada="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        Medicinadb = FirebaseDatabase.getInstance().getReference();
        txtPrecio = (TextView) findViewById(R.id.textprecio);
        txtStock = (TextView) findViewById(R.id.textstock);
        txtCodigo = (TextView) findViewById(R.id.textcod);
        spinner_medicinas = (Spinner) findViewById(R.id.spinnermedicinas);
        bregresar = (Button) findViewById(R.id.buttonregresar);
        cargarMedicinas();
    }
    public void cargarMedicinas(){
        final List<Medicina> medicinas= new ArrayList<>();
        Medicinadb.child("Medicinas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot d:dataSnapshot.getChildren()){
                        String nombrem= d.child("nombre").getValue().toString();
                        String preciom= d.child("precio").getValue().toString();
                        String stockm= d.child("stock").getValue().toString();
                        String codigom= d.child("codigo").getValue().toString();
                        medicinas.add(new Medicina(preciom,stockm,nombrem,codigom));
                    }
                    ArrayAdapter<Medicina> arrayA=new ArrayAdapter<>(consultar.this,android.R.layout.simple_dropdown_item_1line,medicinas);
                    spinner_medicinas.setAdapter(arrayA);
                    System.out.println(medicinas);
                    spinner_medicinas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            m_seleccionada=adapterView.getItemAtPosition(i).toString();
                            for (Medicina m:medicinas){
                                if (m.getNombre().equals(m_seleccionada)) {
                                    txtCodigo.setText(m.getCodigo());
                                    txtPrecio.setText(m.getPrecio());
                                    txtStock.setText(m.getStock());
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void regresar(View view){
        finish();
    }


}