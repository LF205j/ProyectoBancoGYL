package org.example.Entity;

import org.example.Interface.CapacidadLogin;
import org.example.Interface.CapacidadUserCliente;

public class Cliente extends Usuarios implements CapacidadUserCliente, CapacidadLogin {
    private CuentaBanco cuentaBanco;

    public Cliente(int id, String nombre, String apellido, int dni, String direccion, String rol, CuentaBanco cuentaBanco) {
        super(id, nombre, apellido, dni, direccion, rol);
        this.cuentaBanco = cuentaBanco;
    }


    @Override
    public void verMisDatos() {
        System.out.println("Datos cliente: "+super.toString() +" \n");
        System.out.println("Datos Cuenta cliente: "+this.cuentaBanco.toString() +" \n");
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cuentaBanco=" + cuentaBanco +
                '}';
    }

    public CuentaBanco getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(CuentaBanco cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    @Override
    public void iniciarSesion() {

    }

    @Override
    public void cerrarSesion() {

    }
}
