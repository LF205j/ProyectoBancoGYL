package org.example.Entity.Usuarios;

import org.example.Entity.CuentaBanco;
import org.example.Entity.Enum.Rol;
import org.example.Interface.CapacidadLogin;
import org.example.Interface.CapacidadUserCliente;

import java.util.ArrayList;

public class Cliente extends Usuarios implements CapacidadUserCliente, CapacidadLogin {
    private CuentaBanco cuentaBanco;

    public Cliente(int id, String nombre, String apellido, int dni, String direccion, Rol rol, String username, String password, CuentaBanco cuentaBanco) {
        super(id, nombre, apellido, dni, direccion, rol, username, password);
        this.cuentaBanco = cuentaBanco;
    }

    //    public Cliente(int id, String nombre, String apellido, int dni, String direccion, Rol rol, CuentaBanco cuentaBanco) {
//        super(id, nombre, apellido, dni, direccion, rol);
//        this.cuentaBanco = cuentaBanco;
//    }

    @Override
    public void hacerTransferecia(String cbu,ArrayList<Cliente>clientes,float monto) {
        for (Cliente cli: clientes){
            if (cli.getCuentaBanco().getCbu().equals(cbu)){

            }
        }
    }

    @Override
    public void verMisDatos() {
        System.out.println("Datos cliente: "+super.toString() +" \n");
        System.out.println("Datos Cuenta cliente: "+this.cuentaBanco.toString() +" \n");
    }

    @Override
    public void retirar(float monto) {
        if (monto<this.cuentaBanco.getSaldo()){

        }
    }

    @Override
    public void depositar(float monto) {

    }

    @Override
    public void transferir(float monto) {

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
    public Usuarios iniciarSesion(ArrayList<Usuarios> listaUsers, String username, String password) {
        for (Usuarios u: listaUsers){
            if (u.getPassword().equals(password) && u.getUsername().equals(username)){
                System.out.println("Se ingreso Exitosamente");
                return u;
            }
        }
        System.out.println("Usuario o password incorrectos \n");
        return  null;
    }

    @Override
    public void cerrarSesion() {

    }
}
