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
        Cliente clienteEncontrado = null;

        // 1. Buscamos al cliente en la sucursal por su ID
        for (Cliente cli : this.getSucursal().getClientesSucursal()) {
            if (cli.getId() == idUser) {
                clienteEncontrado = cli;
                break;
            }
        }

        if (clienteEncontrado != null) {
            System.out.println("Cuenta asignada exitosamente.");
        } else {
            System.out.println("Error: No se encontró el cliente con ID " + idUser);
        }
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
        Cliente cliente = buscarClientePorId(idUser);

        if (cliente == null) {
            System.out.println("Error: No se encontró el cliente con ID " + idUser);
            return;
        }
        if (monto <= 0) {
            System.out.println("Error: El monto a depositar debe ser mayor a cero.");
            return;
        }

        // 3. Sumar al saldo (No sobrescribir)
        float saldoActual = cliente.getCuentaBanco().getSaldo();
        cliente.getCuentaBanco().setSaldo(saldoActual + monto);

        System.out.println("Depósito exitoso. Nuevo saldo de " + cliente.getNombre() + ": " + (saldoActual + monto));
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
    public void crearGCuentaBancaria() {
        Scanner escaner = new Scanner(System.in);
        System.out.println("\n--- Alta de Gestor de Cuentas Bancarias ---");

        // Pedir datos (puedes crear un método privado 'pedirDatosComunes' para no repetir esto)
        System.out.print("ID: ");
        int id = escaner.nextInt(); escaner.nextLine();
        System.out.print("Nombre: ");
        String nom = escaner.nextLine();
        System.out.print("Apellido: ");
        String ape = escaner.nextLine();
        System.out.print("DNI: ");
        int dni = escaner.nextInt(); escaner.nextLine();
        System.out.print("Username: ");
        String user = escaner.nextLine();
        System.out.print("Password: ");
        String pass = escaner.nextLine();

        // Instanciar con el Rol correspondiente
        GestorCuentasBancarias nuevoG = new GestorCuentasBancarias(id, nom, ape, dni, "Direccion sucursal", Rol.G_CUENTAS, user, pass, this.getBanco(), this.getSucursal(), null);

        // Guardar en la lista de la sucursal
        this.getSucursal().getUsuariosAdmin().add(nuevoG);
        System.out.println("Gestor de Cuentas creado y asignado a " + this.getSucursal().getNombreSucursal());
    }

    @Override
    public void crearGBalances() {
        Scanner escaner = new Scanner(System.in);
        System.out.println("\n--- Alta de Gestor de Balances ---");

        System.out.println("Ingrese un id: ");
        int idGbalance=escaner.nextInt();
        System.out.println("Ingrese el nombre del nuevo cliente");
        String nombreGbalance=escaner.nextLine();
        System.out.println("Ingrese el apellido del nuevo cliente :");
        String apellidoGbalance=escaner.nextLine();
        System.out.println("Ingrese su dni:");
        int dniGbalance=escaner.nextInt();
        System.out.println("Ingrese su direccion: ");
        String direccion=escaner.nextLine();
        System.out.println("Ingrese su username: ");
        String username=escaner.nextLine();
        System.out.println("Ingrese su password: ");
        String password=escaner.nextLine();
        Banco bancoCli=new Banco(getBanco());
        Sucursal sucuCli=new Sucursal(getSucursal());

        GestorBalances nuevoB = new GestorBalances(idGbalance, nombreGbalance, apellidoGbalance, dniGbalance, direccion, Rol.G_BALANCES, username, password, this.getBanco(), this.getSucursal(), null);

        this.getSucursal().getUsuariosAdmin().add(nuevoB);
        System.out.println("Gestor de Balances creado exitosamente.");
    }

    @Override
    public void crearGClientes() {
        Scanner escaner = new Scanner(System.in);
        System.out.println("\n--- Alta de Gestor de Clientes ---");

        System.out.println("Ingrese un id: ");
        int idCliente=escaner.nextInt();
        System.out.println("Ingrese el nombre del nuevo cliente");
        String nombreCli=escaner.nextLine();
        System.out.println("Ingrese el apellido del nuevo cliente :");
        String apellidoCli=escaner.nextLine();
        System.out.println("Ingrese su dni:");
        int dniCli=escaner.nextInt();
        System.out.println("Ingrese su direccion: ");
        String direccion=escaner.nextLine();
        System.out.println("Ingrese su username: ");
        String username=escaner.nextLine();
        System.out.println("Ingrese su password: ");
        String password=escaner.nextLine();
        Banco bancoCli=new Banco(getBanco());
        Sucursal sucuCli=new Sucursal(getSucursal());

        GestorClientes nuevoGC = new GestorClientes(idCliente, nombreCli, apellidoCli, dniCli, direccion, Rol.G_CLIENTES, username, password, this.getBanco(), this.getSucursal(), null);

        this.getSucursal().getUsuariosAdmin().add(nuevoGC);
        System.out.println("Gestor de Clientes dado de alta.");
    }

    @Override
    public void crearCliente() {
        Scanner escaner=new Scanner(System.in);

        System.out.println("Ingrese un id: ");
        int idCliente=escaner.nextInt();
        System.out.println("Ingrese el nombre del nuevo cliente");
        String nombreCli=escaner.nextLine();
        System.out.println("Ingrese el apellido del nuevo cliente :");
        String apellidoCli=escaner.nextLine();
        System.out.println("Ingrese su dni:");
        int dniCli=escaner.nextInt();
        System.out.println("Ingrese su direccion: ");
        String direccion=escaner.nextLine();
        System.out.println("Ingrese su username: ");
        String username=escaner.nextLine();
        System.out.println("Ingrese su password: ");
        String password=escaner.nextLine();
        Banco bancoCli=new Banco(getBanco());
        Sucursal sucuCli=new Sucursal(getSucursal());

        CuentaBanco cuentaBanco=new CuentaBanco();//Lo asigna el GestorCuentaBancos

        Cliente nuevoCliente=new Cliente(idCliente,nombreCli,apellidoCli,dniCli,direccion,Rol.CLIENTE,nombreCli,apellidoCli,bancoCli,sucuCli,cuentaBanco);

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