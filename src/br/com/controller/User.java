package br.com.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucas on 11/09/16.
 */
public class User {

    @FXML
    private Button BTmodulob;
    @FXML
    private Button BTmoduloc;
    @FXML
    private Label LBuser;

    Usuario u = new Usuario();

    @FXML
    private void initialize(){
        if (u.moduloBProperty().getValue() == false){
            BTmodulob.setDisable(true);
        }
        if (u.moduloCProperty().getValue() == false){
            BTmoduloc.setDisable(true);
        }
    }

    public void setUser(Usuario u){
        this.u = u;
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

    @FXML
    private void onPerfil(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Perfil_user.fxml"));
        Parent root = loader.load();

        PerfilUser controller = loader.getController();
        controller.setUser(u);

        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }

    @FXML
    private void onExit() {
        Platform.exit();
    }
}
