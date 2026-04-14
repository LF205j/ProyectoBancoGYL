package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.example.Entity.*;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
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

        Cliente cliente1= new Cliente(1,"Ezequiel","Villareal",12344533,"Callao 295",Rol.CLIENTE,"cliente1","pass1",cuenta1);
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
                            System.out.println("--Menu Admin--\n");
                            System.out.println("1)Registrar Nuevo cliente\n");
                            System.out.println("2)Ver todos los clientes\n");
                            System.out.println("3)Buscar cliente por cbu \n");
                            System.out.println("4)Depositar Sueldo a Cliente\n");
                            System.out.println("5)Buscar cliente por id\n");
                            System.out.println("6)Dar baja cuenta bancaria\n");
                            System.out.println("7)Datos Cliente especifico\n");
                            System.out.println("0)salir\n");
                            System.out.println("--Ingrese una opcion:--\n");
                            opcion=escaner.nextInt();

                            switch (opcion){
                                case 1:

                                    break;
                                case 2:
                                    admin1.verClientes();
                                    break;
                                case 3:
                                    System.out.println("Ingrese el cbu del cliente:");
                                    String cbu=escaner.nextLine();
                                    admin1.buscarClientePorCbu(cbu);
                                    break;
                                case 4:
                                    System.out.println("Ingrese el monto a depositar:");
                                    float monto=escaner.nextFloat();

                                    System.out.println("Ingrese el id del cliente:");
                                    int idCliente=escaner.nextInt();

                                    admin1.depositarSueldo(idCliente,monto);
                            }
                        } else if (u instanceof Cliente) {

                        }
                    }
                }
            }
        }while(opcion!=0);
//        switch (num){
//            case 1:
//                break;
//            case 2:
//                break;
//
//        }
    }
}
