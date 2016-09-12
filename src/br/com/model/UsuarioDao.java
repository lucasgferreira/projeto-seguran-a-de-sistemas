package br.com.model;

import br.com.controller.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

            if (rs.next()){
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
}
