package ManagedBeans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import Types.Student;
import Controls.Driver;
import Controls.ManagerDriver;
import Controls.StudentDriver;

public class ApplicationsPageBean {
	private String applicationSelected;
	private Student[] appliedStudents;
	
	private ManagerDriver managerDriver = new ManagerDriver();
	private Driver driver = new Driver();
	private StudentDriver studentDriver = new StudentDriver();
	
	@PostConstruct
	public void init(){
		System.out.println("ApplicationsPage PostConstructor init called");
		//call ManagerDriver method to return list of students who have applied
		appliedStudents = managerDriver.getAllRoomRequests();
	}
	
	
	public void acceptApplication(){
		System.out.println("Accepted application of selected student");
		//change student's account type to resident. Now is assigned to specific room
		
		//use driver method to just change its user type
		managerDriver.setApplicantToResident(applicationSelected);
	}
	
	public void rejectApplication(){
		//remove student's selected room
		//we can use the request room method, as all it does is set the student's room as null
		studentDriver.requestRoom(null, applicationSelected);
	}
	
	public String getApplicationSelected(){
		return applicationSelected;
	}
	
	public void setApplicationSelected(final String applicationSelected){
		this.applicationSelected = applicationSelected;
	}

	public Student[] getAppliedStudents(){
		return appliedStudents;
	}
	
	public void setAppliedStudents(final Student[] appliedStudents){
		this.appliedStudents = appliedStudents;
	}
	
}
