package org.example.Interface;

import org.example.Entity.CuentaBanco;

public interface CapacidadUserCuentasBancarias extends CapacidadVerNotificaciones {
    void darDeBajaCuenta(int idCuenta);
    CuentaBanco crearCuenta();
    void realizarApertura(int idCuenta);

    @Override
    void verNotificaciones();
}
