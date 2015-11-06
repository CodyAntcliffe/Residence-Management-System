package Controls;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Types.*;

public class ManagerDriver extends Driver {

	//Attempts to connect to our database and returns the connection if successful, null if not
	public Connection connect(){
		return super.connect(dbUser, dbPass);
	}

	//Returns a list of all registered student's names
	public String[] getAllStudentsNames(){

		Connection C = connect();
		String names[] = new String[99];
		//Check if connection successful
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				//Check if the user exists
				String getNames  = "SELECT name FROM users";	
				RS = myStmt.executeQuery(getNames);

				int i = 0;
				//Add the user to the table otherwise
				while(RS.next()){
					String n = RS.getString("name");
					if(n != null){
						names[i] = n;
						i++;
					}
				}
				int count = 0;
				while(names[count]!=null)
					count++;
				String namesNotNull[] = new String[count];
				for(int j=0;j<count;j++)
					namesNotNull[j] = names[j];

				return namesNotNull;

			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	
	//Returns instance of Student by studentName
	public Student getStudentInfo(String studentName){
		
		Connection C = connect();
		studentName = "'"+studentName+"'";
		System.out.println(studentName);
		//Check if connection successful
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				//Check if the user exists
				
				String getNames  = "SELECT * FROM users where name = "+studentName;	
				RS = myStmt.executeQuery(getNames);
				String info[] = {"userName","passWord","accountType","name","phone","major","email","studentNumber","age","yearLevel","roomNum"};
//				
				while(RS.next()){
					Student S = new Student(RS.getString(info[0]),RS.getString(info[1]),RS.getString(info[2]),RS.getString(info[3]),RS.getString(info[4]),RS.getString(info[5]),RS.getString(info[6]),RS.getString(info[7]),RS.getString(info[8]));
					return S;
				}

					return null;
				
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;

	}
}
