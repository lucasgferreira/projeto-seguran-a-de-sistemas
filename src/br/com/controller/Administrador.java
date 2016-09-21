package br.com.controller;

import br.com.model.Md5;
import br.com.model.UsuarioDao;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucas on 12/09/16.
 */
public class Administrador {

    public Button BTnovo;
    public Button BTsalvar;
    public Button BTeditar;
    public Button BTexcluir;
    public TableView<Usuario> TVuser;

    public TableColumn<Usuario, Boolean> TCmodulob;
    public TableColumn<Usuario, Boolean> TCmoduloc;
    @FXML
    private TextField TFuser;
    @FXML
    private PasswordField PFpass;
    @FXML
    private Label LBuser;
    public CheckBox CBmb;
    public CheckBox CBmc;

    @FXML
    private void initialize(){
        BTsalvar.disableProperty().bind(TVuser.getSelectionModel().selectedItemProperty().isNotNull());
        BTeditar.disableProperty().bind(TVuser.getSelectionModel().selectedItemProperty().isNull());
        BTexcluir.disableProperty().bind(TVuser.getSelectionModel().selectedItemProperty().isNull());

        listAll();
        TCmodulob.setCellFactory(CheckBoxTableCell.forTableColumn(TCmodulob));
        TCmoduloc.setCellFactory(CheckBoxTableCell.forTableColumn(TCmoduloc));

        TVuser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                try {
                    if (TVuser.getSelectionModel().getSelectedItem() != null){

                        Usuario u = (Usuario) TVuser.getSelectionModel().getSelectedItem();
                        TFuser.setText(u.getUsuario());
                        CBmb.selectedProperty().setValue(u.moduloBProperty().getValue());
                        CBmc.selectedProperty().setValue(u.moduloCProperty().getValue());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void setUser(Usuario u){
        LBuser.setText(u.getUsuario());
    }

    private void listAll(){
        TVuser.getSelectionModel().clearSelection();
        UsuarioDao ud = new UsuarioDao();
        TVuser.getItems().clear();
        TVuser.setItems(FXCollections.observableArrayList(ud.listAll()));
    }

    @FXML
    private void onAbout(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/about.fxml"));
        stage.setScene(new Scene(root));
        stage.setWidth(450);
        stage.setHeight(300);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
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
        stage.setMaximized(false);
        stage.setHeight(320);
        stage.setWidth(600);
        stage.show();
    }

    @FXML
    private void onSair() {

        Platform.exit();
    }

    @FXML
    private void onNovo() {
        TVuser.getSelectionModel().clearSelection();
        TFuser.clear();
        PFpass.clear();
        CBmb.selectedProperty().setValue(false);
        CBmc.selectedProperty().setValue(false);
    }

    @FXML
    private void onSalvar() {

        if (TFuser.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Digite o usuário!");
            a.show();
        }
        if (PFpass.getText().length() < 6){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Digite uma senha com no mínimo 6 dígitos!");
            a.show();
        }
        else if (CBmb.isSelected() || CBmc.isSelected()){
            UsuarioDao ud = new UsuarioDao();
            Usuario u = new Usuario();
            u.setUsuario(TFuser.getText());
            u.setSenha(Md5.md5(PFpass.getText()));
            u.setModuloA(false);
            u.setModuloB(CBmb.selectedProperty().getValue());
            u.setModuloC(CBmc.selectedProperty().getValue());
            ud.cadastrar(u);
            listAll();
        }
        else {
            Alert c = new Alert(Alert.AlertType.INFORMATION);
            c.setHeaderText("ATENÇÃO");
            c.setContentText("Selecione um Módulo");
            c.show();
        }
    }

    @FXML
    private void onEditar() {

    }

    @FXML
    private void onExcluir() {
        if (TVuser.getSelectionModel().getSelectedItem() != null){

            Usuario u = (Usuario) TVuser.getSelectionModel().getSelectedItem();

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("DESEJA EXCLUIR USUÁRIO!!!");
            a.setContentText(u.getUsuario());

            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                UsuarioDao ud = new UsuarioDao();
                ud.excluir(u.getId());
                listAll();
            }
        }
    }

    public void onPerfil() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("ATENÇÃO");
        a.setContentText("");
        a.show();
    }


    public void onSenha(ActionEvent actionEvent) throws IOException {
        Stage scene = new Stage();
        URL arquivoFXML = getClass().getResource("../view/password.fxml");
        Parent fxmlParent = (Parent) FXMLLoader.load(arquivoFXML);
        scene.setScene(new Scene(fxmlParent, 450, 200));
        scene.show();
    }
}
