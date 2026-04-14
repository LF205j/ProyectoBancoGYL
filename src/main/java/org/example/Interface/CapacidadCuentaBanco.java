package org.example.Interface;

import org.example.Entity.Usuarios.Cliente;

public interface CapacidadCuentaBanco {
    void mostrarSaldo();
    void realizarTransferencia(float monto, Cliente cli);
    void verDatos();
}
