package br.com.sigoa.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
	
	private static final String LINK_DB = "jdbc:mysql://localhost/db_sigoa";//Verificar caminho para o BD
	private static final String USER_DB = "root";// Adicionar nome do usuario do mysql
	private static final String PSW_DB = "Alan_Silva2907";// Adicionar senha se houver
	
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//Nome do driver utilizado
			
			return DriverManager.getConnection(LINK_DB, USER_DB, PSW_DB);	
		} 
		catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(" Erro na conexão: ", e);
		}
	}
	
	public static void closeConnection(Connection con) {
		try {
			if (con!=null) {
			con.close();
			}
		} catch (SQLException e) {
			Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE,	null, e);
		}
			
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) {
		
		closeConnection(con);
		
		try {
			if (stmt!=null){
				stmt.close();
			}
		} catch (SQLException e) {
			Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE,	null, e);
		}
			
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		
		closeConnection(con, stmt);
		
		try {
			if (rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE,	null, e);
		}
			
	}
}