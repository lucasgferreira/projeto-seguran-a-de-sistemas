package br.com.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * Created by lucas on 21/09/16.
 */
public class PasswordADM {
    public PasswordField PFsenhaatual;
    public PasswordField PFsenha;
    public PasswordField PFconfsenha;
    public Label LBuser;
    Usuario u = new Usuario();


    public void setUser(Usuario u){
        this.u = u;
        LBuser.setText(u.getUsuario());
    }
    public void onSalvar(ActionEvent actionEvent) {
        if (PFsenhaatual.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Digite a senha atual!");
            a.show();
        }
        if (PFconfsenha.getText().length() < 6){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Digite uma senha com no mínimo 6 dígitos!");
            a.show();
        }
        if (!PFsenha.getText().equals(PFconfsenha.getText())){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Senhas não batem!");
            a.show();
        }

        else {
            Alert c = new Alert(Alert.AlertType.INFORMATION);
            c.setHeaderText("ATENÇÃO");
            c.setContentText("Selecione um Módulo");
            c.show();
        }
    }
}
