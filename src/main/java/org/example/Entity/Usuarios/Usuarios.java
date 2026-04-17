package org.example.Entity.Usuarios;

import org.example.Entity.Banco;
import org.example.Entity.CuentaBanco;
import org.example.Entity.Enum.Rol;
import org.example.Entity.Sucursal;
import org.example.Interface.CapacidadUserCliente;
import org.example.Interface.CapacidadUsers;

public  abstract class Usuarios  implements CapacidadUsers, CapacidadUserCliente {
    private int id;
    private String nombre;
    private String apellido;
    private int dni;
    private String direccion;
    private Rol rol;
    private String username;
    private String password;
    private Banco banco;
    private Sucursal sucursal;
    private CuentaBanco cuentaBanco;

    public Usuarios(int id, String nombre, String apellido, int dni, String direccion, Rol rol, String username, String password, Banco banco, Sucursal sucursal, CuentaBanco cuentaBanco) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.rol = rol;
        this.username = username;
        this.password = password;
        this.banco = banco;
        this.sucursal = sucursal;
        this.cuentaBanco = cuentaBanco;
    }

    @Override
    public void verMisDatos() {

    }

    public CuentaBanco getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(CuentaBanco cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Usuarios() {
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", direccion='" + direccion + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

    @Override
    public void extraer(float monto) {
        if (monto > 0 && monto <= this.getCuentaBanco().getSaldo()) {
            float nuevoSaldo = this.getCuentaBanco().getSaldo() - monto;
            this.getCuentaBanco().setSaldo(nuevoSaldo);
            System.out.println("Extracción exitosa. Nuevo saldo: " + nuevoSaldo);
        } else {
            System.out.println("Fondos insuficientes o monto inválido.");
        }
    }

    @Override
    public void depositar(float monto) {
        if (monto!=0){
            float carga=this.getCuentaBanco().getSaldo()+monto;
            this.getCuentaBanco().setSaldo(carga);
        }

    }

    @Override
    public void transferir(float monto,String cbu) {
        if (monto > 0 && monto <= this.getCuentaBanco().getSaldo()) {
            Usuarios destino = null;
            // Buscamos al cliente por CBU en la lista de la sucursal
            for (Usuarios c : this.getSucursal().getClientesSucursal()) {
                if (c.getCuentaBanco().getCbu().equals(cbu)) {
                    destino = c;
                    break;
                }
            }

            if (destino != null) {
                this.extraer(monto); // Quitamos dinero de esta cuenta
                destino.depositar(monto); // Sumamos dinero a la otra
                System.out.println("Transferencia enviada a: " + destino.getNombre());
            } else {
                System.out.println("No se encontró el CBU de destino.");
            }
        } else {
            System.out.println("Saldo insuficiente para transferir.");
        }
    }
}