package org.example.Entity.Usuarios;

import org.example.Entity.Enum.Rol;
import org.example.Interface.CapacidadLogin;
import org.example.Interface.CapacidadUserAdmin;

import java.util.ArrayList;

public class Admin  extends Usuarios implements CapacidadUserAdmin,CapacidadLogin {

    private ArrayList<Cliente>clientes;

    public Admin(int id, String nombre, String apellido, int dni, String direccion, Rol rol, String username, String password, ArrayList<Cliente> clientes) {
        super(id, nombre, apellido, dni, direccion, rol, username, password);
        this.clientes = clientes;
    }

    //Creador y asignacion de cuentas
    @Override
    public void asignarCuenta(int idUser, int idCuenta) {

    }

    @Override
    public Cliente buscarClientePorCbu(String cbu) {
        for (Cliente cli: clientes){
            if (cli.getCuentaBanco()!=null && cli.getCuentaBanco().getCbu().equals(cbu)){
                System.out.println("User encontrado");
                return cli;
            }
        }
        return null;
    }

    @Override
    public void verClientes() {
        for (Cliente cli: clientes){
            cli.verMisDatos();
        }
    }

    @Override
    public void depositarSueldo(int idUser, float monto) {
        Cliente clienteADepositar=buscarClientePorId(idUser);

        if (monto!=0){
            clienteADepositar.getCuentaBanco().setSaldo(monto);
        }

    }

    @Override
    public void darBajaCuenta(int id) {

    }

    @Override
    public Cliente buscarClientePorId(int id) {
        for (Cliente cli: clientes){
            if (cli.getId()==id){
                System.out.println("Usuario Encontrado \n");
                return cli;
            }
        }
        return null;

    }

    @Override
    public void datosPorUser(int id) {
        for (Cliente cli: clientes){
            if (cli.getId()==id){
                System.out.println("Usuario Encontrado \n");
                System.out.println(cli.toString());
            }
        }
    }

    @Override
    public void verMisDatos() {
        System.out.println(super.toString());
    }

    @Override
    public Usuarios iniciarSesion(ArrayList<Usuarios>listaUsers,String username,String password) {
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