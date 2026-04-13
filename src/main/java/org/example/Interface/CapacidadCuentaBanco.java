package org.example.Interface;

import org.example.Entity.Cliente;

public interface CapacidadCuentaBanco {
    void mostrarSaldo();
    void realizarTransferencia(float monto, Cliente cli);
    void verDatos();
}
