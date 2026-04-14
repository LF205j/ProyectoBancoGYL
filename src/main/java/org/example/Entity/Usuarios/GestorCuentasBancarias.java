package org.example.Entity.Usuarios;

import org.example.Entity.CuentaBanco;
import org.example.Interface.CapacidadUserCuentasBancarias;

public class GestorCuentasBancarias  extends Usuarios implements CapacidadUserCuentasBancarias {
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
    public void verNotificaciones() {

    }
}
