package com.example.doctorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class recetar extends AppCompatActivity {
    Spinner spinner_medicinas;
    EditText et_Pac;
    EditText et_fecha;
    EditText et_cantreceta;
    Button bregresar;
    Button bregistrar;
    String paciente_rec;
    String fecha_receta;
    String cant_sel;
    String cod_sel;
    String nom_sel;
    String precio_sel;
    private DatabaseReference Medicinadb;
    String m_seleccionada="";
    Medicina med_registrada=null;
    Receta rec_registrada=null;
    final List<Medicina> medicinas_recetadas= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetar);
        Medicinadb = FirebaseDatabase.getInstance().getReference();
        spinner_medicinas = (Spinner) findViewById(R.id.spinnermedicinas);
        et_Pac=(EditText)findViewById(R.id.editTextPaciente);
        et_fecha=(EditText)findViewById(R.id.editTextFecha);
        et_cantreceta=(EditText) findViewById(R.id.editTextNumber);
        bregresar=(Button)findViewById(R.id.buttonRegresar);
        bregistrar=(Button)findViewById(R.id.buttonReg);
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
                    ArrayAdapter<Medicina> arrayA=new ArrayAdapter<>(recetar.this,android.R.layout.simple_dropdown_item_1line,medicinas);
                    spinner_medicinas.setAdapter(arrayA);
                    System.out.println(medicinas);
                    spinner_medicinas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            m_seleccionada=adapterView.getItemAtPosition(i).toString();
                            for (Medicina m:medicinas){
                                if (m.getNombre().equals(m_seleccionada)) {
                                    //txtCodigo.setText(m.getCodigo());
                                    //txtPrecio.setText(m.getPrecio());
                                    //txtStock.setText(m.getStock());
                                    precio_sel=m.getPrecio();
                                    nom_sel=m.getNombre();
                                    cod_sel=m.getCodigo();
                                    cant_sel=m.getStock();
                                    System.out.println("Stock de la vaina:"+cant_sel );


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

    public void registrar(View view){

        Integer a=Integer.valueOf(et_cantreceta.getText().toString());
        Integer b=Integer.valueOf(cant_sel);
        if (a!=null && b!=null) {
            int comparar = a.compareTo(b);
            //a mayor a b
            if (comparar > 0) {
                Toast.makeText(this, "No hay la cantidad ingresada de medicina en la maquina despachadora. Ingrese una cantidad menor", Toast.LENGTH_LONG).show();

            }else{
                med_registrada= new Medicina(precio_sel,cant_sel,nom_sel,cod_sel);
                /*for (Medicina m: medicinas_recetadas){
                    if (m.equals(med_registrada)){
                        med_registrada.setStock((Integer.valueOf(med_registrada.getStock())+Integer.valueOf(m.getStock())));
                    }
                }*/
                medicinas_recetadas.add(med_registrada);
                System.out.println(medicinas_recetadas);
                Toast.makeText(this, "La medicina ha sido registrada", Toast.LENGTH_LONG).show();
                et_cantreceta.setText("");

            }



        }else{
            System.out.println("a");
        }



    }

    public String idReceta(){
        Random r=new Random();
        //Generar
        int rand_1=r.nextInt(2000);
        int rand_2=r.nextInt(2000);

        Integer r1=new Integer(rand_1);
        Integer r2=new Integer(rand_2);

        String id_receta= r1.toString()+r2.toString();
        return id_receta;

    }

    public void registrar_receta(View view){
        String idr=idReceta();
        String id = Medicinadb.push().getKey();
        Receta rec_registrada= new Receta(medicinas_recetadas,"si",id,et_fecha.getText().toString(),et_Pac.getText().toString());
        Medicinadb.child("Recetas").child(rec_registrada.getId_generado()).setValue(rec_registrada);
        Toast.makeText(this, "La receta ha sido registrada. Se generará el código QR", Toast.LENGTH_LONG).show();
        et_Pac.setText("");
        et_fecha.setText("");
        Bundle p=new Bundle();
        p.putString("clave",id);
        Intent i=new Intent(this,Codigo.class);
        i.putExtras(p);
        startActivity(i);


    }



}



