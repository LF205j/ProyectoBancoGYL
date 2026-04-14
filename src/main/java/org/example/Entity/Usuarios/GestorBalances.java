package org.example.Entity.Usuarios;

import org.example.Interface.CapacidadUserBalances;

public class GestorBalances extends Usuarios implements CapacidadUserBalances {
    @Override
    public float hacerBalanceCuentas() {
        return 0;
    }

    @Override
    public float hacerBalancesSucursales() {
        return 0;
    }
}
