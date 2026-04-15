package org.example.Entity;

import org.example.Entity.Usuarios.Usuarios;

import java.util.ArrayList;

public class Banco {
    private int id;
    private String nombreBanco;
    private String direccion;
    private ArrayList<Sucursal> sucursales;



    public Banco(int id, String nombreBanco, String direccion, ArrayList<Sucursal> sucursales) {
        this.id = id;
        this.nombreBanco = nombreBanco;
        this.direccion = direccion;
        this.sucursales = sucursales;
    }

    public Banco(Banco b) {
        this.id = b.id;
        this.nombreBanco = b.nombreBanco;
        this.direccion = b.direccion;
        if (b.sucursales != null) {
            this.sucursales = new ArrayList<>(b.sucursales);
        } else {
            this.sucursales = new ArrayList<>();
        }
    }


    public Banco() {
    }

    public ArrayList<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(ArrayList<Sucursal> sucursales) {
        this.sucursales = sucursales;
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