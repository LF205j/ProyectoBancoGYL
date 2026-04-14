package org.example.Interface;

import java.util.ArrayList;

public interface CapacidadUserTransacciones {
    ArrayList<Transacciones>verHistorialTransacciones();
    void acreditarTransaccion(int idTransaccion);
}
