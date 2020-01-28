
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connectionFactory 
{
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysqli://localhost:3306/projetojava";
    private static final String user = "root";
    private static final String password = "root";
    
    public static Connection getConnection()
    {
        try 
        {
            Class.forName(driver); //Carrega o driver
            return DriverManager.getConnection(url, user, password); //Retorna a conexão
        } 
        catch (ClassNotFoundException | SQLException ex) 
        {
            throw new RuntimeException("Erro na conexão com o Banco de Dados", ex);
        }
    }
    
    public static void closeConnection(Connection objConexao)
    {
        if (objConexao != null)
        {
            try 
            {
                objConexao.close();
            } 
            catch (SQLException ex) 
            {
                System.err.println("Erro ao fechar a conexão: " + ex);
            }
        }
    }
    
    public static void closeConnection(Connection objConexao, Statement stmt)
    {
        if (stmt != null)
        {
            try 
            {
                stmt.close();
            } 
            catch (SQLException ex) 
            {
                 System.err.println("Erro ao fechar a o Statement: " + ex);
            }
        }
        
        closeConnection(objConexao);
    }
    
    public static void closeConnection(Connection objConexao, PreparedStatement stmt, ResultSet rs)
    {
        if (rs != null)
        {
            try 
            {
                rs.close();
            } 
            catch (SQLException ex) 
            {
                 System.err.println("Erro ao fechar o result set: " + ex);
            }
        }
        
        closeConnection(objConexao, stmt);
    }
}
