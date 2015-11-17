package ManagedBeans;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import Types.Student;
import Controls.ManagerDriver;

public class StudentPageBean {
	private String[] studentNameList;
	private Student[] studentList;
	private String studentSelected;
	private Student selectedStudentObject;
	private String studentOutput;
	
	private ManagerDriver managerDriver = new ManagerDriver();
	//TODO the first name in the DB is being added to the list twice.
	//If names are the same it will grab info of the person with the first username twice, instead of each once.
	//We need to only use primary keys to grab people from the table.
	//If we return a list of student objects, we can pick out the name for the list but always return the username to query the database.
	@PostConstruct
	public void init(){
		System.out.println("StudentPage Postconstruct init called.");
		
		getAllStudentsNames();
		System.out.println(studentNameList[0]);
		getAllStudents();
	}
	
	public String[] getAllStudentsNames(){
		studentNameList = managerDriver.getAllStudentsNames();
		return studentNameList;
	}
	public Student[] getAllStudents(){
		studentList = managerDriver.getAllStudents();
		return studentList;
	}
	
	
	public String[] getStudentNameList(){
		return studentNameList;
	}
	public void setStudentNameList(final String[] studentNameList){
		this.studentNameList = studentNameList;
	}
	public Student[] getStudentList(){
		return studentList;
	}
	public void setStudentList(final Student[] studentList){
		this.studentList = studentList;
	}
	public String getStudentSelected(){
		return studentSelected;
	}
	public void setStudentSelected(final String studentSelected){
		this.studentSelected = studentSelected;
		selectedStudentObject = managerDriver.getStudentInfo(studentSelected);
	}
	public Student getSelectedStudentObject(){
		return selectedStudentObject;
	}
	public void setSelectedStudentObject(final Student selectedStudentObject){
		this.selectedStudentObject = selectedStudentObject;
	}
}
