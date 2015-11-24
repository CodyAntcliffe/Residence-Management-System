package ManagedBeans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import Types.Student;
import Controls.Driver;
import Controls.ManagerDriver;

public class ApplicationsPageBean {
	private String applicationSelected;
	private Student[] appliedStudents;
	private String[] studentName;
	private Student studentSelected;
	
	private ManagerDriver managerDriver = new ManagerDriver();
	private Driver driver = new Driver();
	
	private final String approvedText = "Your request has been approved. You will receive the key to your room in 4-6 business days"; 
	private final String rejectedText = "Your request has been declined. You are free to apply for other rooms if you wish.";
	
	@ManagedProperty(value="#{emailBean}")
	EmailBean emailBeanInstance = new EmailBean();

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
		
		studentSelected = managerDriver.getStudentInfo(applicationSelected);
		emailBeanInstance.sendEmail(studentSelected.email, "Application for " +studentSelected.facility + " Room: " + studentSelected.roomNum, approvedText);
		
	}

	public void rejectApplication(){
		System.out.println("Rejected application of selected student");
		//remove student's selected room
		//we can use the request room method, as all it does is set the student's room as null
		managerDriver.setRoomNull(applicationSelected);
		
		studentSelected = managerDriver.getStudentInfo(applicationSelected);
		emailBeanInstance.sendEmail(studentSelected.email, "Application for " +studentSelected.facility + " Room: " + studentSelected.roomNum, rejectedText);
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
	public void setStudentName (final String[] studentName) {
		this.studentName = studentName;
	}
	
	
	public String[] getStudentName () {
		studentName = new String[appliedStudents.length];
		for (int i = 0; i< appliedStudents.length; i++) {
			studentName[i] = appliedStudents[i].name;
		}
		return studentName;
	}
}
