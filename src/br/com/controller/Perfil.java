package br.com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by lucas on 11/09/16.
 */
public class Perfil {

    @FXML
    private Label LBuser;


    public void setUser(Usuario u){
        LBuser.setText(u.getUsuario());
    }

    @FXML
    private void onLogout(ActionEvent event) throws IOException {
        Parent login_page_parent = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene login_page_scene = new Scene(login_page_parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(login_page_scene);
        stage.centerOnScreen();
        stage.setWidth(600);
        stage.setHeight(320);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
    }
}
