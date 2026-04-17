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

       // ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Usuarios> usuarios = new ArrayList<>();

        ArrayList<Sucursal> sucursalesIcbc = new ArrayList<>();
        ArrayList<Sucursal> sucursalesGalicia = new ArrayList<>();

        Banco bancoGalicia = new Banco(1, "Galicia", "Direccion 123", sucursalesGalicia);
        Banco bancoIcbc = new Banco(2, "ICBC", "Direccion 234", sucursalesIcbc);

        Sucursal sucursal1 = new Sucursal("Sucursal Urquiza", 1, "Larralde 231", bancoGalicia, usuarios);
        sucursalesGalicia.add(sucursal1);
        CuentaBanco cuentaBanco1 = new CuentaBanco(1, "CBU1", TipoCuenta.SUELDO, 0, bancoGalicia,false);
        CuentaBanco cuentaBanco2 = new CuentaBanco(2, "CBU2", TipoCuenta.SUELDO, 0, bancoGalicia,false);
        CuentaBanco cuentaBanco3 = new CuentaBanco(3, "CBU3", TipoCuenta.SUELDO, 0, bancoGalicia,false);
        CuentaBanco cuentaBanco4 = new CuentaBanco(4, "CBU4", TipoCuenta.SUELDO, 0, bancoGalicia,false);
        CuentaBanco cuentaBanco5 = new CuentaBanco(5, "CBU5", TipoCuenta.SUELDO, 0, bancoGalicia,false);
        CuentaBanco cuentaBanco6 = new CuentaBanco(6, "CBU6", TipoCuenta.SUELDO, 0, bancoGalicia,false);

        Admin admin1 = new Admin(2, "Enzo", "Fernandez", 4522233, "Larralde 3421", Rol.ADMIN, "admin1", "pass3", bancoGalicia, sucursal1, cuentaBanco2);
        AdminBancario adminGalicia = new AdminBancario(1, "Lautaro ", "Fernandez", 45236660, "Valdenegro 3430", Rol.ADMIN_BANCARIO, "adminG", "passG", bancoGalicia, sucursal1, cuentaBanco4);
        Cliente cliente1 = new Cliente(1, "Nicolas", "Jardel", 235552, "San bernardo 123", Rol.CLIENTE, "Cuser", "Cpass", bancoGalicia, sucursal1, cuentaBanco1);

        sucursal1.getClientesSucursal().add(cliente1);

        GestorClientes gestorClientes = new GestorClientes(3, "Ezequiel", "Villareal", 12344, "Direccion generica 123", Rol.G_CLIENTES, "GCuser", "GCpass", bancoGalicia, sucursal1, cuentaBanco3);
        GestorBalances gestorBalances=new GestorBalances(4,"Juan pablo","Conti",2334112,"Direccio generica 462",Rol.G_BALANCES,"GBuser","GBpass",bancoGalicia,sucursal1,cuentaBanco5);
        GestorCuentasBancarias gestorCuentasBancarias=new GestorCuentasBancarias(5,"Dariel","Castillo",72157,"Triunvirato 582",Rol.G_CUENTAS,"GCBuser","GCBpass",bancoGalicia,sucursal1,cuentaBanco6);

        usuarios.add(cliente1);
        usuarios.add(gestorClientes);
        usuarios.add(admin1);
        usuarios.add(adminGalicia);
        usuarios.add(cliente1);
        usuarios.add(gestorBalances);
        usuarios.add(gestorCuentasBancarias);
        adminGalicia.realizarApertura(1);
        adminGalicia.realizarApertura(2);
        adminGalicia.realizarApertura(3);
        adminGalicia.realizarApertura(4);
        adminGalicia.realizarApertura(5);
        adminGalicia.depositarSueldo(1,50000);
        adminGalicia.depositarSueldo(2,50000);
        adminGalicia.depositarSueldo(3,50000);
        adminGalicia.depositarSueldo(4,50000);
        adminGalicia.depositarSueldo(5,50000);

        Scanner escaner = new Scanner(System.in);
        Boolean entrada=true;
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

                //Listo
                if (usuarioLogueado instanceof GestorClientes) {
                    GestorClientes gClientes = new GestorClientes(usuarioLogueado);
                    System.out.println("\n--Menu Gestor Clientes--");
                    System.out.println("1)Ver clientes\n2)Crear Cliente\n3)Buscar cliente por id\n0)Cerrar Sesion");
                    System.out.print("Opcion: ");
                    opcion = escaner.nextInt();
                    escaner.nextLine(); // Limpiar buffer

                    switch (opcion) {
                        case 1 :
                            gClientes.verClientes();
                            break;
                        case 2 :
                            gClientes.crearCliente();
                            break;
                        case 3 :
                            System.out.print("ID a buscar: ");
                            int id = escaner.nextInt();
                            escaner.nextLine();
                            gClientes.buscarClientePorId(id);
                            break;

                        case 0 :
                            usuarioLogueado = null;
                            break;
                    }
                }

                else if (usuarioLogueado instanceof AdminBancario) {
                    AdminBancario adminB = new AdminBancario(usuarioLogueado);
                    System.out.println("\n--Menu Admin Bancario--");
                    System.out.println("1)Ver Clientes\n2)Depositar sueldo\n3)dar baja cuenta\n4)Buscar Cliente por id\n5)ver mis datos\n6)Crear G-Cuenta-Bancaria\n7)Crear G-Balances\n8)Crear G-Clientes\n9)Crear Sucursales\n0)Cerrar Sesion");

                    System.out.print("Opcion: ");
                    opcion = escaner.nextInt();
                    escaner.nextLine();

                    switch (opcion) {
                        case 1 :
                            adminB.verUsuarios(); //Hacer
                            break;
                        case 2:
                            System.out.println("Ingrese el id del usuario al que se le depositara el sueldo:");
                            int idUser=escaner.nextInt();
                            System.out.println("Ingrese el sueldo a depositar: ");
                            float sueldoDepo=escaner.nextFloat();
                            adminB.depositarSueldo(idUser,sueldoDepo);
                            break;
                        case 3:
                            System.out.println("Ingrese el id de la cuenta a dar de baja:");
                            int cuentaBaja=escaner.nextInt();
                            adminB.darBajaCuenta(cuentaBaja);
                            break;
                        case 4:
                            System.out.println("Ingrese el id del cliente a buscar: ");
                            int clienteABuscar=escaner.nextInt(); //Hacer
                            break;
                        case 5:
                            adminB.verMisDatos();
                            break;
                        case 6:
                            adminB.crearGCuentaBancaria();
                            break;
                        case 7:
                            adminB.crearGBalances();
                            break;
                        case 8:
                            adminB.crearGClientes();
                            break;
                        case 9:
                            adminB.crearSucursal();
                            break;
                        case 0 :
                            usuarioLogueado = null;
                            break;
                    }
                }

                else if (usuarioLogueado instanceof Admin) {
                    Admin adminT = new Admin(usuarioLogueado);
                    System.out.println("\n--Menu Admin--");
                    System.out.println("1)Ver Clientes\n2)Depositar sueldo\n3)dar baja cuenta\n4)Buscar Cliente por id\n5)datos por user\n6)ver mis datos\n7)Crear G-Cuenta-Bancaria\n8)Crear G-Balances\n9)Crear G-Clientes\n0)Cerrar Sesion");
                    System.out.print("Opcion: ");
                    opcion = escaner.nextInt();
                    escaner.nextLine();

                    switch (opcion) {
                        case 1 :
                            adminT.verClientes();
                            break;
                        case 2:
                            System.out.println("Ingrese el id del usuario al que se le depositara el sueldo:");
                            int idUser=escaner.nextInt();
                            System.out.println("Ingrese el sueldo a depositar: ");
                            float sueldoDepo=escaner.nextFloat();
                            adminT.depositarSueldo(idUser,sueldoDepo);
                        case 3:
                            System.out.println("Ingrese el id de la cuenta a dar de baja: ");
                            int idBaja=escaner.nextInt();
                            adminT.darBajaCuenta(idBaja);
                        case 4:
                            System.out.println("Ingrese el id del cliente a buscar: ");
                            int idCliente=escaner.nextInt();
                            adminT.buscarClientePorId(idCliente);
                            break;
                        case 5:
                            System.out.println("Ingrese el id del usuario a visualizar: ");
                            int idBuscar=escaner.nextInt();
                            adminT.datosPorUser(idBuscar);
                            break;
                        case 6:
                            adminT.verMisDatos();
                            break;
                        case 7:
                            adminT.crearGCuentaBancaria();
                            break;
                        case 8:
                            adminT.crearGBalances();
                            break;
                        case 9:
                            adminT.crearGClientes();
                            break;
                        case 0 :
                            usuarioLogueado = null;
                            break;
                    }
                }

                //listo
                else if (usuarioLogueado instanceof GestorBalances) {
                    GestorBalances gBalances = new GestorBalances(usuarioLogueado);
                    System.out.println("\n--Menu Gestor Balances--");
                    System.out.println("1)Balance Cuentas\n2)Balance de todas los Sucursales\n0)Cerrar Sesion");
                    opcion = escaner.nextInt();
                    escaner.nextLine();

                    switch (opcion) {
                        case 1 :
                            System.out.println("Balance: " + gBalances.hacerBalancesSucursal());
                            break;
                        case 2:
                            gBalances.hacerBalanceCuentas();
                            break;
                        case 0 :
                            usuarioLogueado = null;
                            break;

                    }
                }

                //Listo
                else if (usuarioLogueado instanceof GestorCuentasBancarias) {
                    GestorCuentasBancarias gCuentas = new GestorCuentasBancarias(usuarioLogueado);
                    System.out.println("\n--Menu Gestor Cuentas--");
                    System.out.println("1)Dar Baja Cuenta\n2)Crear Cuenta Bancaria\n3)Realizar apertura de cuenta\n4)Asignar Cuenta a user\n0)Cerrar Sesion");
                    opcion = escaner.nextInt();
                    escaner.nextLine();

                    switch (opcion) {
                        case 1 :
                            System.out.print("ID Cuenta: ");
                            int id = escaner.nextInt();
                            escaner.nextLine();
                            gCuentas.darDeBajaCuenta(id);
                            break;
                        case 2:
                            CuentaBanco nueva=gCuentas.crearCuenta();
                            break;
                        case 3:
                            System.out.println("Ingrese el id de la cuenta a abrir: ");
                            int idApertura=escaner.nextInt();
                            gCuentas.realizarApertura(idApertura);
                            break;
                        case 4:
                            System.out.println("Ingrese El id user a asignar cuenta: ");
                            int idUser=escaner.nextInt();
                            System.out.println("Ingrese El id cuenta a asignar: ");
                            int idCuenta=escaner.nextInt();
                            gCuentas.asignarCuenta(idUser,idCuenta);
                        case 0 :
                            usuarioLogueado = null;
                            break;
                    }
                }

                //Listo
                else if (usuarioLogueado instanceof Cliente) {

                    Cliente cli = new Cliente(usuarioLogueado);
                    System.out.println("\n--Menu Cliente--");
                    System.out.println("1)Ver Datos\n2)Transferis\n3)Depositar\n4)Extraer\n0)Cerrar Sesion");
                    opcion = escaner.nextInt();
                    escaner.nextLine();

                    switch (opcion) {
                        case 1 : cli.verMisDatos();
                        case 2:
                            System.out.println("Ingrese el monto a transferir: \n");
                            float monto=escaner.nextFloat();
                            System.out.println("Ingrese el cbu a transferir: \n");
                            String cbu=escaner.nextLine();
                            cli.transferir(monto,cbu);
                            break;
                        case 3:
                            System.out.println("Ingrese el monto a transferir: \n");
                            float montoDeposito=escaner.nextFloat();
                            cli.depositar(montoDeposito);
                            break;
                        case 4:
                            System.out.println("Ingrese el monto a transferir: \n");
                            float montoExtraccion=escaner.nextFloat();
                            cli.extraer(montoExtraccion);
                        case 0 : usuarioLogueado = null;
                    }
                }
            }
        } while (entrada);
    }
}