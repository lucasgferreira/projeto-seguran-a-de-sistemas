package br.com.controller;

import javafx.application.Platform;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnCloseRequest(event1 -> Platform.exit());
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setHeight(320);
        stage.setWidth(600);
        stage.setMaximized(false);
        stage.show();
    }

}
