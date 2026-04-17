package org.example.Entity.Usuarios;

import org.example.Entity.Banco;
import org.example.Entity.CuentaBanco;
import org.example.Entity.Enum.Rol;
import org.example.Entity.Sucursal;
import org.example.Interface.CapacidadUserBalances;
import org.example.Interface.CapacidadUserCliente;

import java.util.ArrayList;

public class GestorBalances extends Usuarios implements CapacidadUserBalances, CapacidadUserCliente {
    public GestorBalances(int id, String nombre, String apellido, int dni, String direccion, Rol rol, String username, String password, Banco banco, Sucursal sucursal, CuentaBanco cuentaBanco) {
        super(id, nombre, apellido, dni, direccion, rol, username, password, banco, sucursal, cuentaBanco);
    }

    public GestorBalances(Usuarios u) {
        super(u.getId(), u.getNombre(), u.getApellido(), u.getDni(), u.getDireccion(),
                u.getRol(), u.getUsername(), u.getPassword(), u.getBanco(),
                u.getSucursal(), u.getCuentaBanco());
    }

    //Sucursales
    @Override
    public float hacerBalancesSucursal() {
        float total = 0;
        // Recorre los clientes de la sucursal donde trabaja el gestor
        for (Usuarios cli : this.getSucursal().getClientesSucursal()) {
            total += cli.getCuentaBanco().getSaldo();
        }
        System.out.println("El total  de la sucursal numero: "+this.getSucursal().getId()+" es de: "+total);
        return total;

    }

    //banco
    @Override
    public float hacerBalanceCuentas() {
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
    public void hacerTransferecia(String cbu, ArrayList<Cliente> clientes, float monto) {

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
    public void verMisDatos() {
        super.verMisDatos();
    }

    @Override
    public void transferir(float monto, String cbu) {
        super.transferir(monto, cbu);
    }
}
