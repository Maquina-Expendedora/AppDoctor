package com.example.doctorapp;

import java.util.ArrayList;
import java.util.List;

public class Receta {
    List<Medicina> medicinas= new ArrayList<>();
    String cancelada;
    String id_generado;
    String fecha;
    String paciente;

    public Receta(List<Medicina> medicinas, String cancelada, String id_generado, String fecha, String paciente) {
        this.medicinas = medicinas;
        this.cancelada = cancelada;
        this.id_generado = id_generado;
        this.fecha = fecha;
        this.paciente = paciente;
    }

    public List<Medicina> getMedicinas() {
        return medicinas;
    }

    public void setMedicinas(List<Medicina> medicinas) {
        this.medicinas = medicinas;
    }

    public String getCancelada() {
        return cancelada;
    }

    public void setCancelada(String cancelada) {
        this.cancelada = cancelada;
    }

    public String getId_generado() {
        return id_generado;
    }

    public void setId_generado(String id_generado) {
        this.id_generado = id_generado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
    public String toString() {
        return id_generado;
    }
}
