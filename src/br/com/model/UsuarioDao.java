package br.com.model;

import br.com.controller.Usuario;
import javafx.scene.control.Alert;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 08/09/16.
 */
public class UsuarioDao extends Dao{

    public Usuario loga(String user, String password){
        Usuario u = new Usuario();
        try {
            abrirConexao();
            String query = "select * from Usuario where nome=? && senha=?";
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.first()){
                u.setId(rs.getInt("idUsuario"));
                u.setUsuario(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setModuloA(rs.getBoolean("moduloA"));
                u.setModuloB(rs.getBoolean("moduloB"));
                u.setModuloC(rs.getBoolean("moduloC"));
            }
            else {
                u = null;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return u;
    }

    public void cadastrar(Usuario u){
        try {
            abrirConexao();
            String query = "INSERT INTO `login`.`Usuario` (`nome`, `senha`, `moduloA`, `moduloB`, `moduloC`) " +
                    "VALUES (?, ?, ?, ?, ?);";
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getSenha());
            ps.setBoolean(3, u.moduloAProperty().getValue());
            ps.setBoolean(4, u.moduloBProperty().getValue());
            ps.setBoolean(5, u.moduloCProperty().getValue());
            ps.execute();

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Cadastro efetuado com sucesso!");
            a.show();
        } catch (SQLException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("ERRO");
            a.setContentText("erro ao cadastrar usuario, tente novamente!");
            a.show();
            e.printStackTrace();
        }
    }

    public void alterar(Usuario u){
        try {
            abrirConexao();
            String query;

                query = "UPDATE `login`.`Usuario` " +
                        "SET `nome`=?, `moduloA`=?, `moduloB`=?, `moduloC`=? " +
                        "WHERE `idUsuario`=?;";

                ps = (PreparedStatement) connection.prepareStatement(query);
                ps.setString(1, u.getUsuario());
                ps.setBoolean(2, u.moduloAProperty().getValue());
                ps.setBoolean(3, u.moduloBProperty().getValue());
                ps.setBoolean(4, u.moduloCProperty().getValue());
                ps.setInt(5, u.getId());
                ps.execute();
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("ATENÇÃO");
                a.setContentText("Usuário alterado com sucesso!");
                a.show();

        } catch (SQLException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("ERRO");
            a.setContentText("erro ao alterar usuário, tente novamente!");
            a.show();
            System.out.print(e.getMessage());
        }
    }

    public void alterarSenha(Usuario u){
        try {
            abrirConexao();
            String query;

                query = "UPDATE `login`.`Usuario` " +
                        "SET `senha`=? WHERE `idUsuario`=?;";

                ps = (PreparedStatement) connection.prepareStatement(query);
                ps.setString(1, u.getSenha());
                ps.setInt(2, u.getId());
                ps.execute();

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("ATENÇÃO");
            a.setContentText("Senha alterada com sucesso!");
            a.show();
        } catch (SQLException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("ERRO");
            a.setContentText("erro ao alterar senha, tente novamente!");
            a.show();
            e.printStackTrace();
        }
    }

    public void excluir(Integer id){
        try {
            abrirConexao();
            String query = "DELETE FROM `login`.`Usuario` WHERE `idUsuario`=?;";
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();

            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setHeaderText("ATENÇÃO");
            al.setContentText("Usuário deletado com sucesso!");
            al.show();
        } catch (SQLException e) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText("ERRO");
            al.setContentText("falha ao deletar usuário!");
            al.show();
            e.printStackTrace();
        }
    }

    public List<Usuario> listAll(){
        ArrayList<Usuario> list = new ArrayList<>();
        try {
            abrirConexao();
            String query = "SELECT * FROM `login`.`Usuario` WHERE moduloA = 0";
            ps = (PreparedStatement) connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt("idUsuario"));
                u.setUsuario(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setModuloA(rs.getBoolean("moduloA"));
                u.setModuloB(rs.getBoolean("moduloB"));
                u.setModuloC(rs.getBoolean("moduloC"));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
