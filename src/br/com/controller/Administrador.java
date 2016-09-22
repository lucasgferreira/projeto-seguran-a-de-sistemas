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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucas on 12/09/16.
 */
public class Administrador {

    @FXML
    private Button BTnovo;
    @FXML
    private Button BTsalvar;
    @FXML
    private Button BTeditar;
    @FXML
    private Button BTexcluir;
    @FXML
    private TableView<Usuario> TVuser;

    @FXML
    private TableColumn<Usuario, Boolean> TCmodulob;
    @FXML
    private TableColumn<Usuario, Boolean> TCmoduloc;
    @FXML
    private Button BTsenha;
    @FXML
    private TextField TFuser;
    @FXML
    private PasswordField PFpass;
    @FXML
    private Label LBuser;
    @FXML
    private CheckBox CBmb;
    @FXML
    private CheckBox CBmc;
    Usuario us = new Usuario();

    @FXML
    private void initialize(){
        BTsalvar.disableProperty().bind(TVuser.getSelectionModel().selectedItemProperty().isNotNull());
        BTeditar.disableProperty().bind(TVuser.getSelectionModel().selectedItemProperty().isNull());
        BTexcluir.disableProperty().bind(TVuser.getSelectionModel().selectedItemProperty().isNull());
        BTsenha.disableProperty().bind(TVuser.getSelectionModel().selectedItemProperty().isNull());
        PFpass.disableProperty().bind(TVuser.getSelectionModel().selectedItemProperty().isNotNull());

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
        this.us = u;
        LBuser.setText(u.getUsuario());
    }

    public Usuario getUser(){
        try {
            if (TVuser.getSelectionModel().getSelectedItem() != null){
                Usuario u = (Usuario) TVuser.getSelectionModel().getSelectedItem();
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
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
        if (TFuser.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Digite o usuário!");
            a.show();
        }

        else if (CBmb.isSelected() || CBmc.isSelected()){
            try {
                if (TVuser.getSelectionModel().getSelectedItem() != null){
                    UsuarioDao ud = new UsuarioDao();
                    Usuario u = (Usuario) TVuser.getSelectionModel().getSelectedItem();
                    u.setUsuario(TFuser.getText());
                    u.setModuloA(false);
                    u.setModuloB(CBmb.selectedProperty().getValue());
                    u.setModuloC(CBmc.selectedProperty().getValue());
                    ud.alterar(u);
                    listAll();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Alert c = new Alert(Alert.AlertType.INFORMATION);
            c.setHeaderText("ATENÇÃO");
            c.setContentText("Selecione um Módulo");
            c.show();
        }
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

    public void onSenha(ActionEvent event) throws IOException {
        if (TVuser.getSelectionModel().getSelectedItem() != null) {
            Usuario u = (Usuario) TVuser.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/password.fxml"));
            Parent root = loader.load();

            Password controller = loader.getController();
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

    public void onPerfil(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/perfil_adm.fxml"));
        Parent root = loader.load();

        UsuarioDao udao = new UsuarioDao();
        us = udao.loga(us.getUsuario(), us.getSenha());

        PerfilAdmin controller = loader.getController();
        controller.setUser(us);
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
        stage.show();
    }
}
