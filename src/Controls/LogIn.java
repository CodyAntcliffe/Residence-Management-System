package Controls;
/* This class houses all of our log-in related methods*/

import java.sql.*;

public class LogIn {
    
	//This bean is sessionScoped, private variables have life-span of single user session
	//userName can be used to uniquely identify rows in database that are relevant to the user
	
	private String userName;
	private String password;
	
	public void setUserName(final String userName) {
		this.userName=userName;
	}
	public void setPassword(final String password) {
		this.password=password;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword(){
		return password;
	}
	
	public void tryLogin() {
		System.out.println("User name: " + userName);
		System.out.println("Password: " + password);
	}
	
	//Checks if log-in was successful. 
	public static Boolean checkLogin(String UN, String PW){
		
		Connection C = Driver.connect();
		
		try{
			Statement myStmt = C.createStatement();
			ResultSet RS;
			
			//Check if the userName UN exists
			String checkUserName = "SELECT 1 FROM users where userName = '"+UN+"'";				
			RS = myStmt.executeQuery(checkUserName);
			
			//Log in unsuccessful if not in Users table
			if(!RS.next()){
				System.out.println("Invalid user name.  Log-in unsuccessful.");
				return false;
			}
			//Now check to make sure the password matches the stored encrypted password.
			else{
				String checkPW = "SELECT 1 FROM users where passWord = '"+encryptPassword(PW)+"'";	
				RS = myStmt.executeQuery(checkPW);
				
				//If does not match
				if(!RS.next()){
					System.out.println("Invalid passsword.  Log-in unsuccessful.");
					return false;
				}
				//User name and password are both found in the database
				else
				{
					System.out.println("Log-in Successful.");
					return true;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	//Passwords should never be stored plain-text. We will use simple shift cipher as an example.
	//Returns the password in cipher text for storing.
	public static String encryptPassword(String plainPW){ 
		//Encrypt...
		char[] plainArray = plainPW.toCharArray();
		for(int i=0; i < plainPW.length(); i++){
			plainArray[i]++;
		}
		String cipherPW = new String(plainArray);
		
		return cipherPW;
	}
		
	//Returns the password in plainText
	public static String decryptPassword(String cipherPW){
		//Decrypt...
		char[] cipherArray = cipherPW.toCharArray();
		for(int i=0; i < cipherPW.length(); i++){
			cipherArray[i]--;
		}
		String plainPW = new String(cipherArray);
		
		return plainPW;
	}
}
