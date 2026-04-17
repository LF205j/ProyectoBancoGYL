package org.example.Entity.Usuarios;

import org.example.Entity.Banco;
import org.example.Entity.CuentaBanco;
import org.example.Entity.Enum.Rol;
import org.example.Entity.Enum.TipoCuenta;
import org.example.Entity.Sucursal;
import org.example.Interface.CapacidadAdminBancario;
import org.example.Interface.CapacidadUserCuentasBancarias;

import java.util.ArrayList;
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
    public void transferir(float monto, String cbu) {
        super.transferir(monto, cbu);
    }

    @Override
    public void extraer(float monto) {
        super.extraer(monto);
    }

    @Override
    public void depositar(float monto) {
        super.depositar(monto);
    }

    @Override
    public void verUsuarios() {
        // 1. Obtenemos la lista de sucursales desde el banco del administrador
        // Usamos el getter heredado de la clase Usuarios
        ArrayList<Sucursal> sucursales = this.getBanco().getSucursales();

        System.out.println("--- Reporte General de Usuarios por Sucursal ---");

        for (Sucursal suc : sucursales) {
            // 2. Mostramos el nombre o ID de la sucursal actual
            System.out.println("\nSucursal: " + suc.getNombreSucursal() + " (ID: " + suc.getId() + ")");
            System.out.println("------------------------------------------");

            // 3. Obtenemos los clientes de esa sucursal específica
            ArrayList<Usuarios> clientes = suc.getClientesSucursal();

            if (clientes.isEmpty()) {
                System.out.println("   [No hay usuarios registrados en esta sucursal]");
            } else {
                for (Usuarios cli : clientes) {
                    // 4. Mostramos los datos del usuario
                    // Aprovechamos el método toString() que ya definiste en Usuarios/Cliente
                    System.out.println(" > " + cli.getNombre() + " " + cli.getApellido() +
                            " | DNI: " + cli.getDni() +
                            " | CBU: " + cli.getCuentaBanco().getCbu());
                }
            }
        }
    }
    @Override
    public Usuarios crearUsuarios() {
        Scanner escaner = new Scanner(System.in);
        System.out.println("\n--- Crear Nuevo Usuario ---");
        System.out.println("Seleccione el tipo de usuario:");
        System.out.println("1) Admin (Local)");
        System.out.println("2) Gestor de Clientes");
        System.out.println("3) Gestor de Balances");
        System.out.println("4) Gestor de Cuentas");
        int tipo = escaner.nextInt();
        escaner.nextLine(); // Limpiar buffer

        // Pedimos datos comunes a todos
        System.out.print("ID: "); int id = escaner.nextInt(); escaner.nextLine();
        System.out.print("Nombre: "); String nom = escaner.nextLine();
        System.out.print("Apellido: "); String ape = escaner.nextLine();
        System.out.print("DNI: "); int dni = escaner.nextInt(); escaner.nextLine();
        System.out.print("Dirección: "); String dir = escaner.nextLine();
        System.out.print("Username: "); String user = escaner.nextLine();
        System.out.print("Password: "); String pass = escaner.nextLine();

        Usuarios nuevoUsuario = null;
        Rol rolElegido;

        // Instanciamos según la opción
        switch (tipo) {
            case 1 -> {
                rolElegido = Rol.ADMIN;
                nuevoUsuario = new Admin(id, nom, ape, dni, dir, rolElegido, user, pass, this.getBanco(), this.getSucursal(), null);
            }
            case 2 -> {
                rolElegido = Rol.G_CLIENTES;
                nuevoUsuario = new GestorClientes(id, nom, ape, dni, dir, rolElegido, user, pass, this.getBanco(), this.getSucursal(), null);
            }
            case 3 -> {
                rolElegido = Rol.G_BALANCES;
                nuevoUsuario = new GestorBalances(id, nom, ape, dni, dir, rolElegido, user, pass, this.getBanco(), this.getSucursal(), null);
            }
            case 4 -> {
                rolElegido = Rol.G_CUENTAS;
                nuevoUsuario = new GestorCuentasBancarias(id, nom, ape, dni, dir, rolElegido, user, pass, this.getBanco(), this.getSucursal(), null);
            }
            default -> {
                System.out.println("Opción inválida.");
                return null;
            }
        }

        // Lo agregamos a la lista de la sucursal para que persista
        if (nuevoUsuario != null) {
            this.getSucursal().getClientesSucursal().add(nuevoUsuario);
            System.out.println("Usuario " + nuevoUsuario.getNombre() + " creado con éxito como " + nuevoUsuario.getRol());
        }

        return nuevoUsuario;
    }

    @Override
    public float hacerBalanceCuentas() {
        float total = 0;
        // Recorre los clientes de la sucursal donde trabaja el gestor
        for (Usuarios cli : this.getSucursal().getClientesSucursal()) {
            total += cli.getCuentaBanco().getSaldo();
        }
        return total;
    }



    @Override
    public Sucursal crearSucursal() {
        Scanner escaner = new Scanner(System.in);

        System.out.println("\n--- Registro de Nueva Sucursal ---");

        System.out.print("Ingrese el nombre de la sucursal (ej: Sucursal Centro): ");
        String nombre = escaner.nextLine();

        System.out.print("Ingrese el ID numérico: ");
        int id = escaner.nextInt();
        escaner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese la dirección: ");
        String direccion = escaner.nextLine();

        // Inicializamos las listas vacías para que no lancen NullPointerException después
        //ArrayList<Usuarios> listaAdmins = new ArrayList<>();
        ArrayList<Usuarios> listaClientes = new ArrayList<>();

        // Creamos la instancia.
        // Usamos 'this.getBanco()' para que la sucursal sepa a qué banco pertenece.
        Sucursal nuevaSucursal = new Sucursal(nombre, id, direccion, this.getBanco(), listaClientes);

        // IMPORTANTE: Agregar la sucursal a la lista del banco para que aparezca en los balances
        if (this.getBanco() != null) {
            this.getBanco().getSucursales().add(nuevaSucursal);
            System.out.println("Éxito: Sucursal '" + nombre + "' vinculada al banco " + this.getBanco().getNombreBanco());
        }

        return nuevaSucursal;
    }

    @Override
    public void asignarResponsables() {

    }

    @Override
    public float hacerBalancesSucursal() {

        float balanceTotalBanco = 0;

        // 1. Obtenemos el banco al que pertenece este gestor
        Banco miBanco = this.getBanco();

        if (miBanco != null && miBanco.getSucursales() != null) {
            // 2. Recorremos todas las sucursales del banco
            for (Sucursal sucu : miBanco.getSucursales()) {
                float balanceSucursal = 0;

                // 3. De cada sucursal, recorremos sus clientes
                if (sucu.getClientesSucursal() != null) {
                    for (Usuarios cli : sucu.getClientesSucursal()) {
                        // Sumamos el saldo de la cuenta del cliente
                        balanceSucursal += cli.getCuentaBanco().getSaldo();
                    }
                }

                System.out.println("Sucursal: " + sucu.getNombreSucursal() + " - Balance: $" + balanceSucursal);
                balanceTotalBanco += balanceSucursal;
            }
        }

        return balanceTotalBanco;
    }

    @Override
    public void darDeBajaCuenta(int idCuenta) {
        for (Usuarios cli : this.getSucursal().getClientesSucursal()) {
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
    public void buscarSucursalPorId(int idSucursal) {
        for (Sucursal sucu: this.getBanco().getSucursales()){
            if (sucu.getId()==idSucursal){
                System.out.println(sucu.toString());
            }
        }
    }

    @Override
    public void datosPorUser(int id) {

    }

    @Override
    public void depositarSueldo(int idUser, float monto) {
        Usuarios cliente = buscarClientePorId(idUser);

        // 1. Validar que el cliente exista
        if (cliente == null) {
            System.out.println("Error: No se encontró el cliente con ID " + idUser);
            return;
        }

        // 2. Validar que el monto sea positivo
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
    public void verClientes() {

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
        for (Usuarios cli : this.getSucursal().getClientesSucursal()) {
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

    public void asignarCuenta(int idUser, int idCuenta) {
        Usuarios clienteEncontrado = null;
        for (Usuarios cli : this.getSucursal().getClientesSucursal()) {
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
    public Usuarios buscarClientePorCbu(String cbu) {
        for (Usuarios cli: this.getSucursal().getClientesSucursal()){
            if (cli.getCuentaBanco()!=null && cli.getCuentaBanco().getCbu().equals(cbu)){
                System.out.println("User encontrado");
                return cli;
            }
        }
        return null;
    }

    @Override
    public Usuarios iniciarSesion(ArrayList<Usuarios> listaUsers, String username, String password) {
        return null;
    }


    @Override
    public void hacerTransferecia(String cbu, ArrayList<Cliente> clientes, float monto) {

    }

    @Override
    public void verMisDatos() {
        System.out.println(super.toString());
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
    public void darBajaCuenta(int id) {
        for (Usuarios cli : this.getSucursal().getClientesSucursal()) {
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
    public Usuarios buscarClientePorId(int id) {
        for (Usuarios cli: this.getSucursal().getClientesSucursal()){
            if (cli.getId()==id){
                System.out.println("Usuario Encontrado \n");
                return cli;
            }
        }
        return null;

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
        this.getSucursal().getClientesSucursal().add(nuevoG);
        System.out.println("Gestor de Cuentas creado y asignado a " + this.getSucursal().getNombreSucursal());
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

        this.getSucursal().getClientesSucursal().add(nuevoGC);
        System.out.println("Gestor de Clientes dado de alta.");
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

        this.getSucursal().getClientesSucursal().add(nuevoB);
        System.out.println("Gestor de Balances creado exitosamente.");
    }

    @Override
    public void cerrarSesion() {

    }

}
