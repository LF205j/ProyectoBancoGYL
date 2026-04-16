package org.example.Entity.Usuarios;

import org.example.Entity.Banco;
import org.example.Entity.CuentaBanco;
import org.example.Entity.Enum.Rol;
import org.example.Entity.Sucursal;
import org.example.Interface.CapacidadUserBalances;

public class GestorBalances extends Usuarios implements CapacidadUserBalances {
    public GestorBalances(int id, String nombre, String apellido, int dni, String direccion, Rol rol, String username, String password, Banco banco, Sucursal sucursal, CuentaBanco cuentaBanco) {
        super(id, nombre, apellido, dni, direccion, rol, username, password, banco, sucursal, cuentaBanco);
    }

    public GestorBalances(Usuarios u) {
        super(u.getId(), u.getNombre(), u.getApellido(), u.getDni(), u.getDireccion(),
                u.getRol(), u.getUsername(), u.getPassword(), u.getBanco(),
                u.getSucursal(), u.getCuentaBanco());
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
        float balanceTotalBanco = 0;

        // 1. Obtenemos el banco al que pertenece este gestor
        Banco miBanco = this.getBanco();

        if (miBanco != null && miBanco.getSucursales() != null) {
            // 2. Recorremos todas las sucursales del banco
            for (Sucursal sucu : miBanco.getSucursales()) {
                float balanceSucursal = 0;

                // 3. De cada sucursal, recorremos sus clientes
                if (sucu.getClientesSucursal() != null) {
                    for (Cliente cli : sucu.getClientesSucursal()) {
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
}
