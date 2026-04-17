package org.example.Interface;

import org.example.Entity.CuentaBanco;

public interface CapacidadUserCuentasBancarias  {
    void darDeBajaCuenta(int idCuenta);
    CuentaBanco crearCuenta();
    void realizarApertura(int idCuenta);
    void asignarCuenta(int idUser,int idCuenta);

}
