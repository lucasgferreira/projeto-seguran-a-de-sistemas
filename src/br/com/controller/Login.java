package br.com.controller;

import br.com.model.Dictionary;
import br.com.model.Md5;
import br.com.model.UsuarioDao;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucas on 08/09/16.
 */
public class Login {
    @FXML
    private TextField TFusuario;
    @FXML
    private PasswordField PFsenha;

    @FXML
    private void onLogar(ActionEvent event) throws IOException {

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
        else {
            UsuarioDao udao = new UsuarioDao();
            Usuario u = udao.loga(TFusuario.getText(), PFsenha.getText());
            if (u.getModuloA() == true){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/administrador.fxml"));
                Parent homePage = loader.load();

                Administrador ad = loader.getController();
                ad.setUser(u);

                Node node = (Node) event.getSource();

                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../view/administrador.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setOnCloseRequest(event1 -> Platform.exit());
                stage.setTitle("Administrador");
                stage.setResizable(true);
                stage.setMinHeight(480);
                stage.setMinWidth(640);
                stage.show();
            }
            else if (u.getModuloB() == true || u.getModuloC() == true){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/perfil.fxml"));
                Parent homePage = loader.load();

                Perfil pf = loader.getController();
                pf.setUser(u);

                Node node = (Node) event.getSource();

                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../view/perfil.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setOnCloseRequest(event1 -> Platform.exit());
                stage.setTitle("Perfil");
                stage.setResizable(true);
                stage.setMinHeight(480);
                stage.setMinWidth(640);
                stage.show();
            }
            else if (u == null){
                Alert e = new Alert(Alert.AlertType.INFORMATION);
                e.setHeaderText(Dictionary.error);
                e.setContentText(Dictionary.userPassIncorrect);
                e.show();
            }
        }
    }

}