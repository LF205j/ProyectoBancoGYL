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

        System.out.println("Ingrese el nombre del nuevo cliente");
        String nombreCli=escaner.nextLine();
        System.out.println("Ingrese el apellido del nuevo cliente :");
        String apellidoCli=escaner.nextLine();
        System.out.println();

    }

    @Override
    public void buscarClientePorId(int id) {
        Cliente cli=this.getSucursal().getClientesSucursal().get(id);
        cli.verMisDatos();


    }
}
