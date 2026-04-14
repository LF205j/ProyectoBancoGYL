package org.example.Interface;

import org.example.Entity.Usuarios.Cliente;

public interface CapacidadUserAdmin extends CapacidadUsers{

    void asignarCuenta(int idUser,int idCuenta);

    Cliente buscarClientePorCbu(String cbu);
    void verClientes();
    void depositarSueldo(int idUser,float monto);
    void darBajaCuenta(int id);
    Cliente buscarClientePorId(int id);
    void datosPorUser(int id);
    @Override
    void verMisDatos();
}