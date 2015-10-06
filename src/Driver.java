/* This class holds all of our Database related methods */

import java.sql.*;

public class Driver {
	
	String url = "jdbc:mysql://localhost:3306/test";
	String dbUser = "root";
	String dbPass = "password";
	
	Connection connect(){
		try{
			Connection myConn = DriverManager.getConnection(url, dbUser, dbPass);
			return myConn;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void addUser
	public static void main(String[] args) {
		

	}

}
