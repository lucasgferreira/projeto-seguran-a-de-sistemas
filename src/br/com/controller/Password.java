package br.com.controller;

import br.com.model.Md5;
import br.com.model.UsuarioDao;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

/**
 * Created by lucas on 21/09/16.
 */
public class Password {
    public PasswordField PFsenha;
    public PasswordField PFconfsenha;


    public void onSalvar(ActionEvent actionEvent) {
        if (PFconfsenha.getText().length() < 6){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Digite uma senha com no mínimo 6 dígitos!");
            a.show();
        }
        if (!PFsenha.getText().equals(PFconfsenha.getText())){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Senhas não batem!");
            a.show();
        }
        else {

            if (TVuser.getSelectionModel().getSelectedItem() != null){

                Usuario u = (Usuario) TVuser.getSelectionModel().getSelectedItem();
                UsuarioDao ud = new UsuarioDao();
                u.setUsuario(TFuser.getText());

                if (PFpass.getText().isEmpty()){
                    u.setSenha(null);
                }
                else {
                    u.setSenha(Md5.md5(TFuser.getText() + PFpass.getText()));
                }

                u.setModuloA(false);
                u.setModuloB(CBmb.selectedProperty().getValue());
                u.setModuloC(CBmc.selectedProperty().getValue());
                try {
                    ud.alterar(u);
                }catch (Exception e){
                    System.out.print(e.getMessage());
                }

            }
            listAll();
        }
        else {
            Alert c = new Alert(Alert.AlertType.INFORMATION);
            c.setHeaderText("ATENÇÃO");
            c.setContentText("Selecione um Módulo");
            c.show();
        }
    }
}
