package Types;


/*Application object. All there is here are the attributes for an application that can be submitted by the user.
Additional functionality will be handled by the page bean. This object only exists to be filled so it can be displayed or passed
to the database*/


public class Application {

	public String status;
	public int studentNum;
	public String term;
	public String roomID;
	public boolean approved;
	
	
	
	
	public Application(String st, int sNum, String tm, String rID, boolean apr){
		this.status = st;
		this.studentNum = sNum;
		this.term = tm;
		this.roomID = rID;
		this.approved = apr;
	}
	
	
	public static void main(String[] args) {
	}

}
