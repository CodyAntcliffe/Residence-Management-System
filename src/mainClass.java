/*THIS IS THE MAIN CLASS */

//PLEASE USE THIS FOR TESTING IN ORDER TO KEEP OTHER CLASS FILES CLEAN!

public class mainClass {
	
	public static void main(String[] args){
		
		//Currently testing database functions
		User test = new User();
		test.register("Cody", "password");
		Driver D = new Driver();
		D.addToDB(test);
		test.accountType = "dfsdfhaksdfj";
		D.addToDB(test);
	
	}
}
