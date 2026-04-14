package org.example.Entity;

import org.example.Entity.Enum.TipoNotificacion;

import java.util.Date;

public class Notificacioes {
    private int id;
    private String descripcon;
    private TipoNotificacion tipoNotificacion;
    private int idUsuarioSolicitante;
    private Boolean estado;
    private Date fecha_notificacion;


    public Notificacioes(int id, Date fecha_notificacion, Boolean estado, int idUsuarioSolicitante, TipoNotificacion tipoNotificacion, String descripcon) {
        this.id = id;
        this.fecha_notificacion = fecha_notificacion;
        this.estado = estado;
        this.idUsuarioSolicitante = idUsuarioSolicitante;
        this.tipoNotificacion = tipoNotificacion;
        this.descripcon = descripcon;
    }

    public Date getFecha_notificacion() {
        return fecha_notificacion;
    }

    public void setFecha_notificacion(Date fecha_notificacion) {
        this.fecha_notificacion = fecha_notificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcon() {
        return descripcon;
    }

    public void setDescripcon(String descripcon) {
        this.descripcon = descripcon;
    }

    public TipoNotificacion getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public int getIdUsuarioSolicitante() {
        return idUsuarioSolicitante;
    }

    public void setIdUsuarioSolicitante(int idUsuarioSolicitante) {
        this.idUsuarioSolicitante = idUsuarioSolicitante;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
