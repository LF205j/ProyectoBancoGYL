package org.example.Interface;

import org.example.Entity.Usuarios;

import java.util.ArrayList;

public interface CapacidadLogin {
    Usuarios iniciarSesion(ArrayList<Usuarios>listaUsers,String username,String password);
    void cerrarSesion();
}
