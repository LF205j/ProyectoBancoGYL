package org.example.Interface;

import org.example.Entity.Sucursal;
import org.example.Entity.Usuarios.Cliente;
import org.example.Entity.Usuarios.Usuarios;

public interface CapacidadUserAdmin extends CapacidadUsers{

    void asignarCuenta(int idUser,int idCuenta);
    Usuarios buscarClientePorCbu(String cbu);
    void verClientes();
    void depositarSueldo(int idUser,float monto);
    void darBajaCuenta(int id);
    Usuarios buscarClientePorId(int id);
    void datosPorUser(int id);
    @Override
    void verMisDatos();
    void crearGCuentaBancaria();
    void crearGBalances();
    void crearGClientes();
    void crearCliente();
    Sucursal crearSucursal();
    void asignarResponsables();


}