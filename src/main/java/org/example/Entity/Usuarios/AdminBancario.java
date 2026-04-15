package org.example.Entity.Usuarios;

import org.example.Entity.Banco;
import org.example.Entity.CuentaBanco;
import org.example.Entity.Enum.Rol;
import org.example.Entity.Enum.TipoCuenta;
import org.example.Entity.Sucursal;
import org.example.Interface.CapacidadAdminBancario;
import org.example.Interface.CapacidadUserCuentasBancarias;

import java.util.Scanner;

public class AdminBancario  extends Usuarios implements CapacidadAdminBancario {


    public AdminBancario(int id, String nombre, String apellido, int dni, String direccion, Rol rol, String username, String password, Banco banco, Sucursal sucursal, CuentaBanco cuentaBanco) {
        super(id, nombre, apellido, dni, direccion, rol, username, password, banco, sucursal, cuentaBanco);
    }

    public AdminBancario(Usuarios u) {
        super(u.getId(), u.getNombre(), u.getApellido(), u.getDni(), u.getDireccion(),
                u.getRol(), u.getUsername(), u.getPassword(), u.getBanco(),
                u.getSucursal(), u.getCuentaBanco());
    }

    @Override
    public void verUsuarios() {

    }
    @Override
    public Usuarios crearUsuarios() {
        return null;
    }

    @Override
    public float hacerBalanceCuentas() {
        float total = 0;
        // Recorre los clientes de la sucursal donde trabaja el gestor
        for (Cliente cli : this.getSucursal().getClientesSucursal()) {
            total += cli.getCuentaBanco().getSaldo();
        }
        return total;
    }

    @Override
    public float hacerBalancesSucursales() {
        return 0;
    }

    @Override
    public void darDeBajaCuenta(int idCuenta) {
        for (Cliente cli : this.getSucursal().getClientesSucursal()) {
            if (cli.getCuentaBanco().getId() == idCuenta) {
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
    public CuentaBanco crearCuenta() {

        Scanner escaner = new Scanner(System.in);
        System.out.println("\n--- Creación de Nueva Cuenta Bancaria ---");

        System.out.print("Ingrese el ID numérico para la cuenta: ");
        int id = escaner.nextInt();
        escaner.nextLine(); // Limpiar el buffer de entrada

        System.out.print("Ingrese el CBU: ");
        String cbu = escaner.nextLine();

        System.out.println("Seleccione el Tipo de Cuenta:");
        System.out.println("1) SUELDO");
        System.out.println("2) CORRIENTE");
        System.out.println("3) AHORRO");
        int opc = escaner.nextInt();

        TipoCuenta tipo = switch (opc) {
            case 2 -> TipoCuenta.CORRIENTE;
            case 3 -> TipoCuenta.AHORRO;
            default -> TipoCuenta.SUELDO;
        };
        CuentaBanco nuevaCuenta = new CuentaBanco(id, cbu, tipo, 0, this.getBanco(), false);

        System.out.println("Cuenta creada correctamente (Estado: Pendiente de Apertura).");
        return nuevaCuenta;
    }

    @Override
    public void realizarApertura(int idCuenta) {
        boolean encontrado = false;
        // Recorremos los clientes de la sucursal donde trabaja este gestor
        for (Cliente cli : this.getSucursal().getClientesSucursal()) {
            if (cli.getCuentaBanco().getId() == idCuenta) {
                // Verificamos si ya está abierta
                if (!cli.getCuentaBanco().getEstado()) {
                    cli.getCuentaBanco().setEstado(true); // Cambiamos false a true
                    System.out.println("Éxito: La cuenta con ID " + idCuenta + " ahora está ACTIVA.");
                } else {
                    System.out.println("Aviso: La cuenta ya se encontraba abierta.");
                }
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Error: No se encontró ninguna cuenta con el ID " + idCuenta + " en esta sucursal.");
        }
    }


    @Override
    public void verMisDatos() {

    }
}
