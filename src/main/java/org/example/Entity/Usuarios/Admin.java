package org.example.Entity.Usuarios;

import org.example.Entity.Banco;
import org.example.Entity.CuentaBanco;
import org.example.Entity.Enum.Rol;
import org.example.Entity.Sucursal;
import org.example.Interface.CapacidadLogin;
import org.example.Interface.CapacidadUserAdmin;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin  extends Usuarios implements CapacidadUserAdmin,CapacidadLogin {


    public Admin(int id, String nombre, String apellido, int dni, String direccion, Rol rol, String username, String password, Banco banco, Sucursal sucursal, CuentaBanco cuentaBanco) {
        super(id, nombre, apellido, dni, direccion, rol, username, password, banco, sucursal, cuentaBanco);
    }
    public Admin(Usuarios u) {
        super(u.getId(), u.getNombre(), u.getApellido(), u.getDni(), u.getDireccion(),
                u.getRol(), u.getUsername(), u.getPassword(), u.getBanco(),
                u.getSucursal(), u.getCuentaBanco());
    }



    //Creador y asignacion de cuentas
    @Override
    public void asignarCuenta(int idUser, int idCuenta) {

    }

    @Override
    public Cliente buscarClientePorCbu(String cbu) {

        for (Cliente cli: this.getSucursal().getClientesSucursal()){
            if (cli.getCuentaBanco()!=null && cli.getCuentaBanco().getCbu().equals(cbu)){
                System.out.println("User encontrado");
                return cli;
            }
        }
        return null;
    }

    @Override
    public void verClientes() {
        for (Cliente cli: this.getSucursal().getClientesSucursal()){
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
        for (Cliente cli : this.getSucursal().getClientesSucursal()) {
            if (cli.getCuentaBanco().getId() == id) {
                if (cli.getCuentaBanco().getEstado()) { // Si está abierta (true)
                    cli.getCuentaBanco().setEstado(false);
                    System.out.println("Cuenta dada de baja con éxito.");
                } else {
                    System.out.println("La cuenta ya se encuentra dada de baja.");
                }
                return;
            }
        }
        System.out.println("No se encontró la cuenta.");
    }

    @Override
    public Cliente buscarClientePorId(int id) {
        for (Cliente cli: this.getSucursal().getClientesSucursal()){
            if (cli.getId()==id){
                System.out.println("Usuario Encontrado \n");
                return cli;
            }
        }
        return null;

    }

    @Override
    public void datosPorUser(int id) {
        for (Cliente cli: this.getSucursal().getClientesSucursal()){
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
    public Usuarios crearUsuariosAdmin() {
        Scanner escaner=new Scanner(System.in);
        System.out.println("Ingrese el tipo de usuario que quiere crear(1)G-Balance 2)G-Operaciones 3)G-CuentasB ):  \n");
        int opcion=escaner.nextInt();
        if (opcion==1){
            System.out.println("--Creacion Usuario Cliente--");
            System.out.println("Ingrese el nombre del nuevo cliente: ");
            //CuentaBanco cuenta=new CuentaBanco();
        }
        return null;
    }

    @Override
    public Cliente crearUsuariosCliente() {
        return null;
    }

    @Override
    public Sucursal crearSucursal() {

        Scanner escaner=new Scanner(System.in);
        System.out.println("Ingrese el nombre de la nueva sucursal?: \n");
        String nombre=escaner.nextLine();
        System.out.println("Ingrese el id de la nueva sucursal?: \n");
        int id=escaner.nextInt();
        System.out.println("Ingrese la direccion de la nueva sucursal?: \n");
        String direccion=escaner.nextLine();

        ArrayList<Usuarios>usuariosAdmin=new ArrayList<>();
        ArrayList<Cliente>clientes=new ArrayList<>();

        Sucursal sucursal=new Sucursal(nombre,id,direccion,this.getBanco(),usuariosAdmin,clientes);
        return sucursal;
    }

    @Override
    public void asignarResponsables() {

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