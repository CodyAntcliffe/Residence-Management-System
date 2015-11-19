package ManagedBeans;

import Types.Student;
import Controls.ManagerDriver;

public class StudentPageBean {
	private Student[] studentList;
	private Student resultStudent;
	private String searchTerm="Search";
	
	private ManagerDriver managerDriver = new ManagerDriver();
	//TODO the first name in the DB is being added to the list twice.
	
	public String searchStudents() {
		resultStudent=null;
		studentList = managerDriver.getAllStudents();
		return "";
	}
	
	public String getSearchResult(String userName) {
		//call function to get student by their username
		for (int i=0; i< studentList.length; i++) {
			if (studentList[i].getUserName().equals(userName)){
				setResultStudent(studentList[i]);
			}
		}
		return "";
	}
	public Student[] getStudentList(){
		return studentList;
	}
	public void setStudentList(final Student[] studentList){
		this.studentList = studentList;
	}

	public Student getResultStudent() {
		return resultStudent;
	}

	public void setResultStudent(Student resultStudent) {
		this.resultStudent = resultStudent;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
}
