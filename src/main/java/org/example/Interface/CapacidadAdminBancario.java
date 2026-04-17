package org.example.Interface;

import org.example.Entity.CuentaBanco;
import org.example.Entity.Sucursal;
import org.example.Entity.Usuarios.Usuarios;

import java.util.ArrayList;

public interface CapacidadAdminBancario extends CapacidadUserCuentasBancarias,CapacidadUserBalances,CapacidadUsers,CapacidadUserAdmin,CapacidadLogin{
    @Override
    Usuarios buscarClientePorId(int id);

    @Override
    Usuarios buscarClientePorCbu(String cbu);

    @Override
    Usuarios iniciarSesion(ArrayList<Usuarios> listaUsers, String username, String password);

    void verUsuarios();
    Usuarios crearUsuarios();
    @Override
    default float hacerBalanceCuentas() {
        return 0;
    }

    Sucursal crearSucursal();
    @Override
    default float hacerBalancesSucursal() {
        return 0;
    }

    @Override
    default void darDeBajaCuenta(int idCuenta) {

    }

    void buscarSucursalPorId(int idSucursal);
    @Override
    void datosPorUser(int id);

    @Override
    void depositarSueldo(int idUser, float monto);

    @Override
    void verClientes();

    @Override
    default CuentaBanco crearCuenta() {
        return null;
    }

    @Override
    default void realizarApertura(int idCuenta) {

    }

    @Override
    void asignarCuenta(int idUser, int idCuenta);

    @Override
    void verMisDatos();

    @Override
    void crearCliente();

    @Override
    void darBajaCuenta(int id);

    @Override
    void crearGCuentaBancaria();

    @Override
    void crearGClientes();

    @Override
    void crearGBalances();

    @Override
    void cerrarSesion();
}
