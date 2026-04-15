package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import org.example.Entity.*;
import org.example.Entity.Enum.Rol;
import org.example.Entity.Enum.TipoCuenta;
import org.example.Entity.Usuarios.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // --- INICIALIZACIÓN DE DATOS ---
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Usuarios> usuarios = new ArrayList<>();

        ArrayList<Sucursal> sucursalesIcbc = new ArrayList<>();
        ArrayList<Sucursal> sucursalesGalicia = new ArrayList<>();

        Banco bancoGalicia = new Banco(1, "Galicia", "Direccion 123", sucursalesGalicia);
        Banco bancoIcbc = new Banco(2, "ICBC", "Direccion 234", sucursalesIcbc);

        Sucursal sucursal1 = new Sucursal("Sucursal Urquiza", 1, "Larralde 231", bancoGalicia, usuarios, clientes);

        CuentaBanco cuentaBanco1 = new CuentaBanco(1, "CBU1", TipoCuenta.SUELDO, 0, bancoGalicia);
        CuentaBanco cuentaBanco2 = new CuentaBanco(2, "CBU2", TipoCuenta.SUELDO, 0, bancoGalicia);
        CuentaBanco cuentaBanco3 = new CuentaBanco(3, "CBU3", TipoCuenta.SUELDO, 0, bancoGalicia);
        CuentaBanco cuentaBanco4 = new CuentaBanco(4, "CBU4", TipoCuenta.SUELDO, 0, bancoGalicia);

        Admin admin1 = new Admin(2, "Enzo", "Fernandez", 4522233, "Larralde 3421", Rol.ADMIN, "admin1", "pass3", bancoGalicia, sucursal1, cuentaBanco2);
        AdminBancario adminGalicia = new AdminBancario(1, "Lautaro ", "Fernandez", 45236660, "Valdenegro 3430", Rol.ADMIN_BANCARIO, "adminG", "passG", bancoGalicia, sucursal1, cuentaBanco4);
        Cliente cliente1 = new Cliente(1, "Nicolas", "Jardel", 235552, "San bernardo 123", Rol.CLIENTE, "Cuser", "Cpass", bancoGalicia, sucursal1, cuentaBanco1);

        sucursal1.getClientesSucursal().add(cliente1);

        GestorClientes gestorClientes = new GestorClientes(3, "Ezequiel", "Villareal", 12344, "Direccion generica 123", Rol.G_CLIENTES, "GCuser", "GCpass", bancoGalicia, sucursal1, cuentaBanco3);

        usuarios.add(gestorClientes);
        usuarios.add(admin1);
        usuarios.add(adminGalicia);

        Scanner escaner = new Scanner(System.in);
        Usuarios usuarioLogueado = null;
        int opcion = -1;

        // --- BUCLE PRINCIPAL ---
        do {
            if (usuarioLogueado == null) {
                System.out.println("\n--Bienvenido Al banco--");
                System.out.println("--Inicio de sesion--");
                System.out.print("Ingrese su Nombre de usuario: ");
                String username = escaner.nextLine();
                System.out.print("Ingrese su contrasenia: ");
                String password = escaner.nextLine();

                for (Usuarios u : usuarios) {
                    if (u.getPassword().equals(password) && u.getUsername().equals(username)) {
                        usuarioLogueado = u;
                        System.out.println("Se ingreso Exitosamente como: " + u.getRol());
                        break;
                    }
                }
                if (usuarioLogueado == null) System.out.println("Credenciales incorrectas.");

            } else {
                // --- PROCESAMIENTO DE MENÚS SEGÚN ROL ---

                if (usuarioLogueado instanceof GestorClientes) {
                    GestorClientes gClientes = new GestorClientes(usuarioLogueado);
                    System.out.println("\n--Menu Gestor Clientes--");
                    System.out.println("1)Ver clientes\n2)Crear Cliente\n3)Buscar cliente por id\n0)Cerrar Sesion");
                    System.out.print("Opcion: ");
                    opcion = escaner.nextInt();
                    escaner.nextLine(); // Limpiar buffer

                    switch (opcion) {
                        case 1 -> gClientes.verClientes();
                        case 2 -> gClientes.crearCliente();
                        case 3 -> {
                            System.out.print("ID a buscar: ");
                            int id = escaner.nextInt();
                            escaner.nextLine();
                            gClientes.buscarClientePorId(id);
                        }
                        case 0 -> usuarioLogueado = null;
                    }
                }

                else if (usuarioLogueado instanceof AdminBancario) {
                    AdminBancario adminB = new AdminBancario(usuarioLogueado);
                    System.out.println("\n--Menu Admin Bancario--");
                    System.out.println("1)Ver Datos\n0)Cerrar Sesion");
                    System.out.print("Opcion: ");
                    opcion = escaner.nextInt();
                    escaner.nextLine();

                    switch (opcion) {
                        case 1 -> adminB.verMisDatos();
                        case 0 -> usuarioLogueado = null;
                    }
                }

                else if (usuarioLogueado instanceof Admin) {
                    Admin adminT = new Admin(usuarioLogueado);
                    System.out.println("\n--Menu Admin--");
                    System.out.println("1)Ver Clientes\n0)Cerrar Sesion");
                    System.out.print("Opcion: ");
                    opcion = escaner.nextInt();
                    escaner.nextLine();

                    switch (opcion) {
                        case 1 -> adminT.verClientes();
                        case 0 -> usuarioLogueado = null;
                    }
                }

                else if (usuarioLogueado instanceof GestorBalances) {
                    GestorBalances gBalances = new GestorBalances(usuarioLogueado);
                    System.out.println("\n--Menu Gestor Balances--");
                    System.out.println("1)Balance Sucursales\n0)Cerrar Sesion");
                    opcion = escaner.nextInt();
                    escaner.nextLine();

                    switch (opcion) {
                        case 1 -> System.out.println("Balance: " + gBalances.hacerBalancesSucursales());
                        case 0 -> usuarioLogueado = null;
                    }
                }

                else if (usuarioLogueado instanceof GestorCuentasBancarias) {
                    GestorCuentasBancarias gCuentas = new GestorCuentasBancarias(usuarioLogueado);
                    System.out.println("\n--Menu Gestor Cuentas--");
                    System.out.println("1)Baja Cuenta\n0)Cerrar Sesion");
                    opcion = escaner.nextInt();
                    escaner.nextLine();

                    switch (opcion) {
                        case 1 -> {
                            System.out.print("ID Cuenta: ");
                            int id = escaner.nextInt();
                            escaner.nextLine();
                            gCuentas.darDeBajaCuenta(id);
                        }
                        case 0 -> usuarioLogueado = null;
                    }
                }

                else if (usuarioLogueado instanceof Cliente) {
                    Cliente cli = new Cliente(usuarioLogueado);
                    System.out.println("\n--Menu Cliente--");
                    System.out.println("1)Ver Datos\n0)Cerrar Sesion");
                    opcion = escaner.nextInt();
                    escaner.nextLine();

                    switch (opcion) {
                        case 1 -> cli.verMisDatos();
                        case 0 -> usuarioLogueado = null;
                    }
                }
            }
        } while (true);
    }
}