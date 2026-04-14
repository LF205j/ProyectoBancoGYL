package org.example.Interface;

import org.example.Entity.Cliente;

import java.util.ArrayList;

public interface CapacidadUserCliente extends CapacidadUsers{

    void hacerTransferecia(String cbu, ArrayList<Cliente>clientes,float monto);

    @Override
    void verMisDatos();
    void retirar(float monto);
}