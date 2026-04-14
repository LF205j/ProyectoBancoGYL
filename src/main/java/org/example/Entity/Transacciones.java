package org.example.Entity;

import java.util.Date;

public class Transacciones {
    private int id;
    private float monto;
    private Date fecha;
    private String cbuInicio;
    private String cbuFin;

    public Transacciones(int id, float monto, Date fecha, String cbuInicio, String cbuFin) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.cbuInicio = cbuInicio;
        this.cbuFin = cbuFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCbuInicio() {
        return cbuInicio;
    }

    public void setCbuInicio(String cbuInicio) {
        this.cbuInicio = cbuInicio;
    }

    public String getCbuFin() {
        return cbuFin;
    }

    public void setCbuFin(String cbuFin) {
        this.cbuFin = cbuFin;
    }
}
