package Controls;
import Types.*;
import Controls.*;
/*THIS IS THE MAIN CLASS */
import ManagedBeans.ApplicationsPageBean;

//PLEASE USE THIS FOR TESTING IN ORDER TO KEEP OTHER CLASS FILES CLEAN!

public class mainClass {
	
	public static void main(String[] args){	
	ManagerDriver MD = new ManagerDriver();
	Student[] appliedStudents = MD.getAllRoomRequests();
	appliedStudents[0].printStudent();
	
	ApplicationsPageBean app = new ApplicationsPageBean();
	app.init();
	
	System.out.println("test "  + app.getStudentName()[0]);
	
	
	}
}
