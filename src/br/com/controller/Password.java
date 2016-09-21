package br.com.controller;

import br.com.model.Md5;
import br.com.model.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * Created by lucas on 21/09/16.
 */
public class Password {
    public PasswordField PFsenha;
    public PasswordField PFconfsenha;
    public Label LBuser;
    Usuario u = new Usuario();


    public void setUser(Usuario u){
        this.u = u;
        LBuser.setText(u.getUsuario());
    }
    public void onSalvar(ActionEvent actionEvent) {
        if (!PFsenha.getText().equals(PFconfsenha.getText())){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Senhas não batem!");
            a.show();
        }
        if (PFconfsenha.getText().length() < 6){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Digite uma senha com no mínimo 6 dígitos!");
            a.show();
        }
        else {
           UsuarioDao ud = new UsuarioDao();
           u.setSenha(Md5.md5(u.getUsuario()+PFconfsenha.getText()));
           ud.alterar(u);
        }
    }
}