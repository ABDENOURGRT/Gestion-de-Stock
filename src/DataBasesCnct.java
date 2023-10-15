//cette class c'est la connection de la BDD 

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.jdi.connect.spi.Connection;

public class DataBasesCnct {
	
	
	    public java.sql.Connection con = null;
	    private Statement stmt = null;
	    private ResultSet rs = null;
	    public DataBasesCnct(String username, String password) {
	        try {
	            //Loading the drivers
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	        } catch (ClassNotFoundException e) {
	            System.out.println("Failed to load JDBC driver.");
	            e.printStackTrace();
	            return;
	        }
	        //connect the database
	        try {
	            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",username,password);
	            System.out.println("connected succesfully !");
	            setStmt(con.createStatement());
	        } catch (SQLException e) {
	            System.out.println("username or mot de passe incorrect.");
	            e.printStackTrace();        }

	        
	    }
	    public void setStmt(Statement stmt) {
	        this.stmt = stmt;
	    }

	}

