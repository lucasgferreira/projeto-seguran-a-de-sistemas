package br.com.controller;

import javafx.event.ActionEvent;

/**
 * Created by lucas on 08/09/16.
 */
public class Usuario {

    private String usuario;
    private String senha;
    private Boolean moduloA, moduloB, moduloC;

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

    public Boolean getModuloA() {
        return moduloA;
    }

    public void setModuloA(Boolean moduloA) {
        this.moduloA = moduloA;
    }

    public Boolean getModuloB() {
        return moduloB;
    }

    public void setModuloB(Boolean moduloB) {
        this.moduloB = moduloB;
    }

    public Boolean getModuloC() {
        return moduloC;
    }

    public void setModuloC(Boolean moduloC) {
        this.moduloC = moduloC;
    }
}
