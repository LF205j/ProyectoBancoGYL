package org.example.Entity.Usuarios;

import org.example.Entity.Banco;
import org.example.Entity.CuentaBanco;
import org.example.Entity.Enum.Rol;
import org.example.Entity.Sucursal;
import org.example.Interface.CapacidadLogin;
import org.example.Interface.CapacidadUserCliente;

import java.util.ArrayList;

public class Cliente extends Usuarios implements CapacidadUserCliente, CapacidadLogin {
    public Cliente(int id, String nombre, String apellido, int dni, String direccion, Rol rol, String username, String password, Banco banco, Sucursal sucursal, CuentaBanco cuentaBanco) {
        super(id, nombre, apellido, dni, direccion, rol, username, password, banco, sucursal, cuentaBanco);
    }

    public Cliente(Usuarios u) {
        super(u.getId(), u.getNombre(), u.getApellido(), u.getDni(), u.getDireccion(),
                u.getRol(), u.getUsername(), u.getPassword(), u.getBanco(),
                u.getSucursal(), u.getCuentaBanco());
    }

    public Cliente() {
    }


    //    public Cliente(int id, String nombre, String apellido, int dni, String direccion, Rol rol, String username, String password) {
//        super(id, nombre, apellido, dni, direccion, rol, username, password);
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
        System.out.println("Datos Cuenta cliente: "+this.getCuentaBanco().toString() +" \n");
    }

    @Override
    public void extraer(float monto) {
        if (monto > 0 && monto <= this.getCuentaBanco().getSaldo()) {
            float nuevoSaldo = this.getCuentaBanco().getSaldo() - monto;
            this.getCuentaBanco().setSaldo(nuevoSaldo);
            System.out.println("Extracción exitosa. Nuevo saldo: " + nuevoSaldo);
        } else {
            System.out.println("Fondos insuficientes o monto inválido.");
        }
    }

    @Override
    public void depositar(float monto) {
        if (monto!=0){
            float carga=this.getCuentaBanco().getSaldo()+monto;
            this.getCuentaBanco().setSaldo(carga);
        }

    }

    @Override
    public void transferir(float monto,String cbu) {
        if (monto > 0 && monto <= this.getCuentaBanco().getSaldo()) {
            Cliente destino = null;
            // Buscamos al cliente por CBU en la lista de la sucursal
            for (Cliente c : this.getSucursal().getClientesSucursal()) {
                if (c.getCuentaBanco().getCbu().equals(cbu)) {
                    destino = c;
                    break;
                }
            }

            if (destino != null) {
                this.extraer(monto); // Quitamos dinero de esta cuenta
                destino.depositar(monto); // Sumamos dinero a la otra
                System.out.println("Transferencia enviada a: " + destino.getNombre());
            } else {
                System.out.println("No se encontró el CBU de destino.");
            }
        } else {
            System.out.println("Saldo insuficiente para transferir.");
        }
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cuentaBanco=" + this.getCuentaBanco() +
                '}';
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
