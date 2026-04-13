package org.example.Entity;

import java.util.ArrayList;

public class Banco {
    private int id;
    private String nombreBanco;
    private String direccion;
    //private ArrayList<Cliente> clientes;


    public Banco(int id, String nombreBanco, String direccion) {
        this.id = id;
        this.nombreBanco = nombreBanco;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}