package br.com.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Created by lucas on 08/09/16.
 */
public class Usuario {

    private Integer id;
    private String usuario;
    private String senha;
    private BooleanProperty moduloA = new SimpleBooleanProperty();
    private BooleanProperty moduloB = new SimpleBooleanProperty();
    private BooleanProperty moduloC = new SimpleBooleanProperty();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isModuloA() {
        return moduloA.get();
    }

    public BooleanProperty moduloAProperty() {
        return moduloA;
    }

    public void setModuloA(boolean moduloA) {
        this.moduloA.set(moduloA);
    }

    public boolean isModuloB() {
        return moduloB.get();
    }

    public BooleanProperty moduloBProperty() {
        return moduloB;
    }

    public void setModuloB(boolean moduloB) {
        this.moduloB.set(moduloB);
    }

    public boolean isModuloC() {
        return moduloC.get();
    }

    public BooleanProperty moduloCProperty() {
        return moduloC;
    }

    public void setModuloC(boolean moduloC) {
        this.moduloC.set(moduloC);
    }
}
