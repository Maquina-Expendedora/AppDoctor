package com.example.doctorapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;


public class Codigo extends AppCompatActivity {


    Button generarcodigo;
    Button regresar;
    ImageView codgenerado;
    String codigo;
    Bitmap qrBits;
    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);
        generarcodigo=findViewById(R.id.buttongenerar);
        regresar=findViewById(R.id.buttonregresar);
        codgenerado=findViewById(R.id.imageView);
        codigo = getIntent().getExtras().getString("clave");
        System.out.println(codigo);
    }

    public void regresar(View view) {
        finish();
    }


    public void generar(View view) {
        QRGEncoder qrgEncoder = new QRGEncoder(codigo, null, QRGContents.Type.TEXT, 500);
        try {
            qrBits = qrgEncoder.getBitmap();
            codgenerado.setImageBitmap(qrBits);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardar(View view){
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            try {
                boolean save = new QRGSaver().save(savePath,codigo, qrBits, QRGContents.ImageType.IMAGE_JPEG);
                String result = save ? "Image Saved" : "Image Not Saved";
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }
}




