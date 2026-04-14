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
    static void main() {
        ArrayList<Cliente>clientes=new ArrayList<>();
        ArrayList<Usuarios>usuarios=new ArrayList<>();

        Banco banco1=new Banco(1,"Galicia","Direccion 123");
        Banco banco2=new Banco(2,"ICBC","Direccion 234");

        CuentaBanco cuenta1=new CuentaBanco("NoSe123", TipoCuenta.SUELDO,banco1);
        CuentaBanco cuenta2=new CuentaBanco("Mejor234",TipoCuenta.SUELDO,banco2);

        Cliente cliente1= new Cliente(1,"Ezequiel","Villareal",12344533,"Callao 295", Rol.CLIENTE,"cliente1","pass1",cuenta1);
        clientes.add(cliente1);

        usuarios.add(cliente1);
        Cliente cliente2= new Cliente(2,"Lautaro","Fernandez",23463,"Valdenegro 1920",Rol.CLIENTE,"cliente2","pass2",cuenta2);
        clientes.add(cliente2);
        usuarios.add(cliente2);

        Admin admin1=new Admin(1,"Enzo","Fernandez",4522233,"Larralde 3421",Rol.ADMIN,"admin1","pass3",clientes);
        usuarios.add(admin1);

        cuenta1.verDatos();
        cliente1.verMisDatos();
        //System.out.println("El cliente : "+cliente1.getNombre()+" tiene la cuenta con el id: "+cuenta1.getId());
        admin1.buscarClientePorId(1);
        admin1.depositarSueldo(1,50000);
        System.out.println("HOLA");
        cliente1.getCuentaBanco().realizarTransferencia(150,admin1.buscarClientePorCbu("Mejor234"));
        System.out.println("sssssss");
        cliente1.verMisDatos();
        cliente2.verMisDatos();

        Scanner escaner=new Scanner(System.in);
        Usuarios usuarioNuevo=null;
        int opcion=0;

        do {
            System.out.println("--Bienvenido Al banco--\n");


            if (usuarioNuevo==null){
                System.out.println("--Inicio de sesion--\n");
                System.out.println("Ingrese su Nombre de usuario: \n");
                String username=escaner.nextLine();

                System.out.println("Ingrese su contrasenia: \n");
                String password=escaner.nextLine();

                for (Usuarios u: usuarios){
                    if (u.getPassword().equals(password) && u.getUsername().equals(username)){
                        System.out.println("Se ingreso Exitosamente");
                        if (u instanceof Admin){
                            usuarioNuevo=u;
                            Admin adminTemp=(Admin)u;
                            System.out.println("--Menu Admin--\n");
                            System.out.println("1)Registrar Nuevo cliente\n");
                            System.out.println("2)Ver todos los clientes\n");
                            System.out.println("3)Buscar cliente por cbu \n");
                            System.out.println("4)Depositar Sueldo a Cliente\n");
                            System.out.println("5)Buscar cliente por id\n");
                            System.out.println("6)Dar baja cuenta bancaria\n");
                            System.out.println("7)Ver mis datos\n");
                            System.out.println("0)salir\n");
                            System.out.println("--Ingrese una opcion:--\n");
                            opcion=escaner.nextInt();

                            switch (opcion){
                                case 1:

                                    break;
                                case 2:
                                    adminTemp.verClientes();
                                    break;
                                case 3:
                                    System.out.println("Ingrese el cbu del cliente:");
                                    String cbu=escaner.nextLine();
                                    adminTemp.buscarClientePorCbu(cbu);
                                    break;
                                case 4:
                                    System.out.println("Ingrese el monto a depositar:");
                                    float monto=escaner.nextFloat();

                                    System.out.println("Ingrese el id del cliente:");
                                    int idCliente=escaner.nextInt();

                                    admin1.depositarSueldo(idCliente,monto);
                                    break;
                                case 5:
                                    System.out.println("Ingrese el id del cliente a buscar: ");
                                    int id=escaner.nextInt();
                                    admin1.buscarClientePorId(id);
                                    break;
                                case 6:
                                    break;
                                case 7:
                                    admin1.verMisDatos();
                                    break;
                            }
                        } else if (u instanceof Cliente) {
                            usuarioNuevo=u;
                            Cliente clienteTemp=(Cliente)u;
                            System.out.println("--Menu Cliente--\n");
                            System.out.println("1)Ver mis datos\n");
                            System.out.println("2)Hacer Transferencia\n");
                            System.out.println("3)Buscar cliente por cbu \n");

                            System.out.println("0)salir\n");
                            System.out.println("--Ingrese una opcion:--\n");
                            opcion=escaner.nextInt();

                            switch (opcion){
                                case 1:
                                    clienteTemp.verMisDatos();
                                    break;
                                case 2:
                                    //clienteTemp.verClientes();
                                    break;
                                case 3:
                                    System.out.println("Ingrese el cbu del cliente:");
                                    String cbu=escaner.nextLine();
                                    admin1.buscarClientePorCbu(cbu);
                                    break;
                                case 0:

                                    break;

                            }
                        } else if (u instanceof GestorBalances) {
                                usuarioNuevo=u;
                                GestorBalances GBalance=(GestorBalances) u;

                                System.out.println("--Menu Gestor Balances--\n");
                                System.out.println("1)Hacer Balance Sucursales\n");
                                System.out.println("2)Hacer Balance cuentas X sucursal\n");
                                System.out.println("0)salir\n");
                                System.out.println("--Ingrese una opcion:--\n");
                                opcion=escaner.nextInt();

                                switch (opcion){
                                    case 1:

                                        float balanceSucursales=GBalance.hacerBalancesSucursales();
                                        System.out.println("El balance de todas las sucursales es de: "+balanceSucursales+" $");
                                        break;
                                    case 2:
                                        System.out.println("Ingrese el id de la sucursal a la que se hara balance de sus cuentas asignadas: ");
                                        int idSucursal=escaner.nextInt();

                                        float balanceCuentasXSucursal=GBalance.hacerBalancesSucursales();
                                        System.out.println("El balance de todas las cuentas de la sucursal: "+idSucursal+" es de: "+balanceCuentasXSucursal+" $");
                                        break;
                                    case 0:
                                        break;

                                }
                        } else if (u instanceof GestorCuentasBancarias) {
                                usuarioNuevo=u;
                                GestorCuentasBancarias GCuentasBancarias=(GestorCuentasBancarias) u;

                                System.out.println("--Menu Gestor Cuentas Bancarias--\n");
                                System.out.println("1)Dar Baja la cuenta especifica\n");
                                System.out.println("2)Crear cuenta \n");
                                System.out.println("3)Realizar Apertura cuenta\n");
                                System.out.println("0)salir\n");
                                System.out.println("--Ingrese una opcion:--\n");
                                opcion=escaner.nextInt();

                                switch (opcion){
                                    case 1:
                                        System.out.println("Ingresa el id de la cuenta que se quiere dar de baja: ");
                                        int idBaja=escaner.nextInt();
                                        GCuentasBancarias.darDeBajaCuenta(idBaja);
                                        break;
                                    case 2:
                                        CuentaBanco nuevaCuenta=GCuentasBancarias.crearCuenta();
                                        //Hacer array de cuentasBancarias
                                        break;
                                    case 3:
                                        System.out.println("Ingrese el id de la cuenta que se tiene que habilitar: ");
                                        int idHabilitar=escaner.nextInt();
                                        GCuentasBancarias.realizarApertura(idHabilitar);
                                        break;
                                    case 0:
                                        break;
                                }
                        } else if (u instanceof  GestorOperaciones) {
                            usuarioNuevo=u;
                            GestorOperaciones GOperaciones=(GestorOperaciones) u;
                            System.out.println("--Menu Gestor Operaciones--\n");
                            System.out.println("1)Habilitar Transferencia\n");
                            System.out.println("2)Habilitar Deposito \n");
                            System.out.println("3)Habilitar extraccion\n");
                            System.out.println("4)Ver Notificaciones\n");
                            System.out.println("0)salir\n");
                            System.out.println("--Ingrese una opcion:--\n");
                            opcion=escaner.nextInt();
                            switch (opcion){
                                case 1:
                                    System.out.println("Ingresa el id de la transferencia que se quiere habilitar: ");
                                    int idTransferencia=escaner.nextInt();
                                    GOperaciones.habilitarTransferencia(idTransferencia);
                                    break;
                                case 2:
                                    System.out.println("Ingresa el id del deposito que se quiere habilitar: ");
                                    int idDeposito=escaner.nextInt();
                                    GOperaciones.habilitarDeposito(idDeposito);
                                    break;
                                case 3:
                                    System.out.println("Ingresa el id de la extraccion que se quiere habilitar: ");
                                    int idExtraccion=escaner.nextInt();
                                    GOperaciones.habilitarExtraccion(idExtraccion);
                                    break;
                                case 4:
                                    GOperaciones.verNotificaciones();
                                    break;
                                case 0:
                                    break;

                            }

                        } else if (u instanceof GestorTransacciones) {
                            usuarioNuevo=u;
                            GestorTransacciones GTransacciones=(GestorTransacciones)u;

                        }
                    }
                }
            }
        }while(opcion!=0);

    }
}
