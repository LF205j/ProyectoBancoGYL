package org.example.Entity;

import org.example.Entity.Enum.TipoCuenta;
import org.example.Entity.Usuarios.Cliente;
import org.example.Interface.CapacidadCuentaBanco;

public class CuentaBanco implements CapacidadCuentaBanco {
    private int id;
    private String cbu;
    private TipoCuenta tipoCuenta;

    private float saldo;
    private Banco bancoPadre;

    public CuentaBanco(int id, String cbu, TipoCuenta tipoCuenta, float saldo, Banco bancoPadre) {
        this.id = id;
        this.cbu = cbu;
        this.tipoCuenta = tipoCuenta;
        this.saldo = 0;
        this.bancoPadre = bancoPadre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Banco getBancoPadre() {
        return bancoPadre;
    }

    public void setBancoPadre(Banco bancoPadre) {
        this.bancoPadre = bancoPadre;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public void mostrarSaldo() {

    }

    @Override
    public void realizarTransferencia(float monto, Cliente cli) {

        if (monto<this.saldo ){
            //this.saldo=-monto;
            this.saldo=(this.getSaldo()-monto);
            //cli.setCuentaBanco();
            cli.getCuentaBanco().setSaldo(cli.getCuentaBanco().saldo+monto);
            System.out.println("La transferencia se realizo correctamente \n");
        }else{
            System.out.println("El cbu no es correcto o es nulo");
        }
    }

    @Override
    public void verDatos() {

        String texto= toString();
        System.out.println(texto);
    }

    @Override
    public String toString() {
        return "CuentaBanco{" +
                "cbu=" + cbu +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", saldo=" + saldo +
                ", bancoPadre=" + bancoPadre +
                '}';
    }
}