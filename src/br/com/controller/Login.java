package br.com.controller;

import br.com.model.Md5;
import br.com.model.UsuarioDao;
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

        String valida = null;

        System.out.println(Md5.md5(TFusuario.getText()+PFsenha.getText()));
        if (TFusuario.getText().isEmpty() || PFsenha.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("preencha os campos!!");
            a.show();
        }
        else {
            UsuarioDao udao = new UsuarioDao();
            Usuario u = udao.loga(TFusuario.getText(), Md5.md5(TFusuario.getText()+PFsenha.getText()));
            if (u == null){
                Alert e = new Alert(Alert.AlertType.INFORMATION);
                e.setHeaderText("ATENÇÃO");
                e.setContentText("Usuario ou senha incorretos!!");
                e.show();
            }
            else if (u.moduloAProperty().getValue() == true){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/administrador.fxml"));
                Parent home_page_parent = loader.load();

                Administrador controller = loader.getController();
                controller.setUser(u);

                Scene home_page_scene = new Scene(home_page_parent);
                Node node = (Node) event.getSource();

                Stage stage = (Stage) node.getScene().getWindow();

                stage.setScene(home_page_scene);
                stage.setMaximized(true);
                stage.setResizable(true);
                stage.setMinHeight(480);
                stage.setMinWidth(640);
                stage.show();
            }
            else if (u.moduloBProperty().getValue() == true || u.moduloCProperty().getValue() == true){

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/user.fxml"));
                Parent home_page_parent = loader.load();

                User controller = loader.getController();
                controller.setUser(u);

                Scene home_page_scene = new Scene(home_page_parent);
                Node node = (Node) event.getSource();

                Stage stage = (Stage) node.getScene().getWindow();

                stage.setScene(home_page_scene);
                stage.setResizable(true);
                stage.setMaximized(true);
                stage.setMinHeight(480);
                stage.setMinWidth(640);
                stage.show();
            }
        }
    }

}