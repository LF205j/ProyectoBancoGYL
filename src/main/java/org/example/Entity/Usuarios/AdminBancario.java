package org.example.Entity.Usuarios;

import org.example.Entity.CuentaBanco;
import org.example.Interface.CapacidadAdminBancario;
import org.example.Interface.CapacidadUserCuentasBancarias;

public class AdminBancario  extends Usuarios implements CapacidadAdminBancario {
    @Override
    public float hacerBalanceCuentas() {
        return 0;
    }

    @Override
    public float hacerBalancesSucursales() {
        return 0;
    }

    @Override
    public void darDeBajaCuenta(int idCuenta) {

    }

    @Override
    public CuentaBanco crearCuenta() {
        return null;
    }

    @Override
    public void realizarApertura(int idCuenta) {

    }

    @Override
    public void habilitarTransferencia(int idTransferencia) {

    }

    @Override
    public void habilitarDeposito(int idDepositar) {

    }

    @Override
    public void habilitarExtraccion(int idExtraccion) {

    }

    @Override
    public void verNotificaciones() {

    }
}
