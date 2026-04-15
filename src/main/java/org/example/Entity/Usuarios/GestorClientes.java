package org.example.Entity.Usuarios;

import org.example.Entity.Banco;
import org.example.Entity.CuentaBanco;
import org.example.Entity.Enum.Rol;
import org.example.Entity.Sucursal;
import org.example.Interface.CapacidadUserGestionClientes;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorClientes extends Usuarios implements CapacidadUserGestionClientes {

    public GestorClientes(int id, String nombre, String apellido, int dni, String direccion, Rol rol, String username, String password, Banco banco, Sucursal sucursal, CuentaBanco cuentaBanco) {
        super(id, nombre, apellido, dni, direccion, rol, username, password, banco, sucursal, cuentaBanco);
    }
    public GestorClientes(Usuarios u) {
        super(u.getId(), u.getNombre(), u.getApellido(), u.getDni(), u.getDireccion(),
                u.getRol(), u.getUsername(), u.getPassword(), u.getBanco(),
                u.getSucursal(), u.getCuentaBanco());
    }

    @Override
    public void verClientes() {
        ArrayList<Cliente>clientesSucu=new ArrayList<>();

        clientesSucu=this.getSucursal().getClientesSucursal();
        for (Cliente cli: clientesSucu){
            cli.verMisDatos();
        }


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
    public void buscarClientePorId(int id) {
        // 1. Obtenemos la lista de la sucursal
        ArrayList<Cliente> clientes = this.getSucursal().getClientesSucursal();
        boolean encontrado = false;

        // 2. Recorremos buscando la coincidencia de ID
        for (Cliente cli : clientes) {
            if (cli.getId() == id) { // Comparamos el atributo ID
                cli.verMisDatos();
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún cliente con el ID: " + id);
        }
    }
}
