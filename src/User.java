
//Super class for Manager, Applicant and Resident

public class User {
	
	int userID; //Primary key for user related database
	public String userName; // Can't change once registered, only accessible by log-in methods
	public String passWord; // Can't change once registered, only accessible by log-in methods
	
	String accountType; //account type of the user
	
	public void register(String UN, String plainPW){
		this.userName = UN;
		this.passWord = this.encryptPassword(plainPW);
		this.accountType = null;
	}
	
	//Passwords should never be stored plain-text. We will use simple shift cipher as an example.
	
	//Returns the password in cipher text for storing.
	private String encryptPassword(String plainPW){ 
		//Encrypt...
		char[] plainArray = plainPW.toCharArray();
		for(int i=0; i < plainPW.length(); i++){
			plainArray[i]++;
		}
		String cipherPW = new String(plainArray);
		
		return cipherPW;
	}
		
	//Returns the password in plainText
	private String decryptPassword(String cipherPW){
		//Decrypt...
		char[] cipherArray = cipherPW.toCharArray();
		for(int i=0; i < cipherPW.length(); i++){
			cipherArray[i]--;
		}
		String plainPW = new String(cipherArray);
		
		return plainPW;
	}
	
	/*
	 * This will need to be modified to use our database once implemented
	 * We will first look in the User table for the appropriate username, and if found, compare password.
	 */
	//Method for verifying if log-in details match
	public Boolean verifyLogin(String UN, String PW){
		
		if(PW.equals(this.decryptPassword(this.passWord)) && UN.equals(this.userName)){
			System.out.println("Log-In Succesful");
			return true;
		}
		else{
			System.out.println("Log-In Unsuccesful. Try Again.");
			return false;
		}
	}
	
	
	public static void main(String[] args){
	
	}
}
