package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.example.Entity.*;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Main {
    static void main() {
        ArrayList<Cliente>clientes=new ArrayList<>();

        Banco banco1=new Banco(1,"Galicia","Direccion 123");
        Banco banco2=new Banco(2,"ICBC","Direccion 234");

        CuentaBanco cuenta1=new CuentaBanco("NoSe123", TipoCuenta.SUELDO,banco1);
        CuentaBanco cuenta2=new CuentaBanco("Mejor234",TipoCuenta.SUELDO,banco2);

        Cliente cliente1= new Cliente(1,"Ezequiel","Villareal",12344533,"Callao 295","CLIENTE",cuenta1);
        clientes.add(cliente1);
        Cliente cliente2= new Cliente(2,"Lautaro","Fernandez",23463,"Valdenegro 1920","CLIENTE",cuenta2);
        clientes.add(cliente2);

        Admin admin1=new Admin(1,"Enzo","Fernandez",4522233,"Larralde 3421","ADMIN",clientes);

        cuenta1.verDatos();
        cliente1.verMisDatos();
        //System.out.println("El cliente : "+cliente1.getNombre()+" tiene la cuenta con el id: "+cuenta1.getId());
        admin1.buscarClientePorId(1);



        admin1.depositarSueldo(1,50000);
        cliente1.getCuentaBanco().realizarTransferencia(150,admin1.buscarClientePorCbu("Mejor234"));

        cliente1.verMisDatos();
        cliente2.verMisDatos();
    }
}
