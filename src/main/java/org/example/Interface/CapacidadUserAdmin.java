package org.example.Interface;

import org.example.Entity.Cliente;

import java.util.ArrayList;

public interface CapacidadUserAdmin extends CapacidadUsers{

    void asignarCuenta(int idUser,int idCuenta);

    Cliente buscarClientePorCbu(String cbu);
    void verClientes();
    void depositarSueldo(int idUser,float monto);
    Cliente buscarClientePorId(int id);
    void datosPorUser(int id);
    @Override
    void verMisDatos();
}