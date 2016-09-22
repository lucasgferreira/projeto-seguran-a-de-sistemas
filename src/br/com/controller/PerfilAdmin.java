package br.com.controller;

import br.com.model.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by lucas on 21/09/16.
 */
public class PerfilAdmin {
    @FXML
    private Label LBuser;
    @FXML
    private TextField TFuser;
    @FXML
    private CheckBox CBma;
    @FXML
    private CheckBox CBmb;
    @FXML
    private CheckBox CBmc;

    Usuario u = new Usuario();


    public void setUser(Usuario u){
        this.u = u;
        LBuser.setText(u.getUsuario());
        TFuser.setText(u.getUsuario());
        CBma.selectedProperty().setValue(u.moduloAProperty().getValue());
        CBmb.selectedProperty().setValue(u.moduloBProperty().getValue());
        CBmc.selectedProperty().setValue(u.moduloCProperty().getValue());
    }

    public void onSalvar(ActionEvent event) {
        if (TFuser.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Digite o usuário!");
            a.show();
        }
        else if (CBma.isSelected() || CBmb.isSelected() || CBmc.isSelected()){
            UsuarioDao ud = new UsuarioDao();
            u.setUsuario(TFuser.getText());
            u.setModuloA(CBma.selectedProperty().getValue());
            u.setModuloB(CBmb.selectedProperty().getValue());
            u.setModuloC(CBmc.selectedProperty().getValue());
            ud.alterar(u);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        else {
            Alert c = new Alert(Alert.AlertType.INFORMATION);
            c.setHeaderText("ATENÇÃO");
            c.setContentText("Selecione um Módulo");
            c.show();
        }
    }

    public void onCancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onSenha(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/passwordadm.fxml"));
        Parent root = loader.load();

        PasswordADM controller = loader.getController();
        controller.setUser(u);

        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.setMinHeight(250);
        stage.setMinWidth(500);
        stage.show();
    }
}
