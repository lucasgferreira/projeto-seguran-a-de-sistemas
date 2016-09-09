package br.com.controller;

import br.com.model.Dictionary;
import br.com.model.Md5;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by lucas on 08/09/16.
 */
public class Login {
    @FXML
    private TextField TFusuario;
    @FXML
    private PasswordField PFsenha;

    @FXML
    private void onLogar() {

        StringBuilder valida = new StringBuilder();
        valida.delete(0, valida.length());
        System.out.println(Md5.md5(TFusuario.getText()+PFsenha.getText()));
        if (TFusuario.getText().isEmpty()){
            valida.append(Dictionary.DigiteUsusario);
        }
        if (PFsenha.getText().isEmpty()){
            valida.append("\n"+Dictionary.DigiteSenha);
        }
        if (!valida.toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText(Dictionary.error);
            a.setContentText(valida.toString());
            a.show();
        }
    }
}