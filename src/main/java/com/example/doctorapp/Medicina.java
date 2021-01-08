package com.example.doctorapp;

import androidx.annotation.NonNull;

public class Medicina {
    //String medicinaID;
    String precio;
    String stock;
    String nombre;
    String codigo;

    public Medicina(String precio, String stock, String nombre, String codigo) {
        // this.medicinaID=medicinaID;
        this.precio=precio;
        this.stock=stock;
        this.nombre=nombre;
        this.codigo=codigo;
    }

   /* public String getMedicinaID() {
        return medicinaID;
    }*/

    public String getStock() {
        return stock;
    }

    public String getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @NonNull
    @Override
    public String toString() {
        return nombre;
    }
}
