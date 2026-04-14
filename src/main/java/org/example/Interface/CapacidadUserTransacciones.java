package org.example.Interface;

import org.example.Entity.Transacciones;

import java.util.ArrayList;

public interface CapacidadUserTransacciones {
    ArrayList<Transacciones>verHistorialTransacciones();
    void acreditarTransaccion(int idTransaccion);
}
