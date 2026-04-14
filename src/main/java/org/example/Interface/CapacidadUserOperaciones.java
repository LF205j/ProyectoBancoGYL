package org.example.Interface;

public interface CapacidadUserOperaciones extends CapacidadVerNotificaciones {
    //float monto, String cbu
    void habilitarTransferencia(int idTransferencia);
    void habilitarDeposito(int idDepositar);
    void habilitarExtraccion(int idExtraccion);

    @Override
    void verNotificaciones();
}
