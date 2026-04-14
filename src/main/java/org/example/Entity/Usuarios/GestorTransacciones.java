package org.example.Entity.Usuarios;

import org.example.Entity.Transacciones;
import org.example.Interface.CapacidadUserTransacciones;

import java.util.ArrayList;

public class GestorTransacciones extends Usuarios implements CapacidadUserTransacciones {
    @Override
    public ArrayList<Transacciones> verHistorialTransacciones() {
        return null;
    }

    @Override
    public void acreditarTransaccion(int idTransaccion) {

    }
}
