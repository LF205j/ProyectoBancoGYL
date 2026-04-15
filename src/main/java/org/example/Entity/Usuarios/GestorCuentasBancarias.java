package org.example.Entity.Usuarios;

import org.example.Entity.Banco;
import org.example.Entity.CuentaBanco;
import org.example.Entity.Enum.Rol;
import org.example.Entity.Sucursal;
import org.example.Interface.CapacidadUserCuentasBancarias;

public class GestorCuentasBancarias  extends Usuarios implements CapacidadUserCuentasBancarias {

    public GestorCuentasBancarias(int id, String nombre, String apellido, int dni, String direccion, Rol rol, String username, String password, Banco banco, Sucursal sucursal, CuentaBanco cuentaBanco) {
        super(id, nombre, apellido, dni, direccion, rol, username, password, banco, sucursal, cuentaBanco);
    }
    public GestorCuentasBancarias(Usuarios u) {
        super(u.getId(), u.getNombre(), u.getApellido(), u.getDni(), u.getDireccion(),
                u.getRol(), u.getUsername(), u.getPassword(), u.getBanco(),
                u.getSucursal(), u.getCuentaBanco());
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


}
