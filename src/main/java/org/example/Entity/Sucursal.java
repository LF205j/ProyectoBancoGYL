package org.example.Entity;

import org.example.Entity.Usuarios.*;

import java.util.ArrayList;
import java.util.Date;

public class Sucursal {
    private String nombreSucursal;
    private int id;
    private String direccionSucursal;
    private Banco bancoPadre;
    private ArrayList<Usuarios>usuariosAdmin;
    private ArrayList<Cliente>clientesSucursal;

    public Sucursal(String nombreSucursal, int id, String direccionSucursal, Banco bancoPadre, ArrayList<Usuarios> usuariosAdmin, ArrayList<Cliente> clientesSucursal) {
        this.nombreSucursal = nombreSucursal;
        this.id = id;
        this.direccionSucursal = direccionSucursal;
        this.bancoPadre = bancoPadre;
        this.usuariosAdmin = usuariosAdmin;
        this.clientesSucursal = clientesSucursal;
    }

    public ArrayList<Usuarios> getUsuariosAdmin() {
        return usuariosAdmin;
    }

    public void setUsuariosAdmin(ArrayList<Usuarios> usuariosAdmin) {
        this.usuariosAdmin = usuariosAdmin;
    }

    public ArrayList<Cliente> getClientesSucursal() {
        return clientesSucursal;
    }

    public void setClientesSucursal(ArrayList<Cliente> clientesSucursal) {
        this.clientesSucursal = clientesSucursal;
    }

    public Banco getBancoPadre() {
        return bancoPadre;
    }

    public void setBancoPadre(Banco bancoPadre) {
        this.bancoPadre = bancoPadre;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionSucursal() {
        return direccionSucursal;
    }

    public void setDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }
}
