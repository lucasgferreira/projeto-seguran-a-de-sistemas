package br.com.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucas on 08/09/16.
 */
public class Dao {
    /*variaveis de conexão*/
    private static final String url = "jdbc:mysql://localhost:3306/login?autoReconnect=true&useSSL=false";
    private static final String drive = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "mysql";

    public Connection connection;
    public PreparedStatement ps;

    public void abrirConexao() throws SQLException {
        try {
            Class.forName(drive);
            connection = DriverManager.getConnection(url, user, password);

        }
        catch (ClassNotFoundException ex){
            System.out.println("Classe não encontrada, adicionar drive JDBC a biblioteca!");
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException erro){
            System.out.println(erro.getMessage());
            throw new RuntimeException(erro);
        }
    }
    public void fecharConexao() throws SQLException {
        if (ps != null){
            ps.close();
            System.out.println("Execução de query encerrada");
        }
    }
}
