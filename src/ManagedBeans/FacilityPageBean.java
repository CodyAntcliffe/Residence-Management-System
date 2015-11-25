package ManagedBeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import Controls.ManagerDriver;
import Types.Facility;
import Types.Student;

public class FacilityPageBean {
	private String[] facilityType = new String[3];//used for drop down menu
	private String[] roomType = new String[3];//used for the room type drop down
	private String accessible;
	private String facilityName;//Stores the name of the new facility
	private String typeSelection;
	private String roomTypeSelection;
	private Facility[] facilityList;
	private String removeFacility;
	private String createResponse;
	private String removeResponse;
	private Student[] evictedStudents;
	
	ManagerDriver md = new ManagerDriver();
	
	@ManagedProperty(value="#{emailBean}")
	EmailBean emailBeanInstance = new EmailBean();
	
	@PostConstruct
	public void init() {
		facilityType[0]="Residence";
		facilityType[1]="Apartment";
		facilityType[2]="Townhouse";
		
		roomType[0] = "Basic Single";
		roomType[1] = "Single";
		roomType[2] = "Double";
		
		facilityList=md.getAllFacilities();
	}
	
	public void createFacility() {
		System.out.println(facilityName + typeSelection + roomTypeSelection + accessible);
		createResponse=null;
		if (typeSelection.equals("Residence")) {
			if (roomTypeSelection.equals("Basic Single")) {
				System.out.println(facilityName + typeSelection + roomTypeSelection + accessible);
				createResponse = md.createFacility(facilityName, "Residence", "5");
			}else if(roomTypeSelection.equals("Single")) {
				System.out.println(facilityName + typeSelection + roomTypeSelection + accessible);
				createResponse = md.createFacility(facilityName, "Residence", "6");
			}else if (roomTypeSelection.equals("Double")){
				createResponse = md.createFacility(facilityName, "Residence", "9");
			}
		}
		if (typeSelection.equals("Apartment")) {
			if (accessible.equals("Accessible")) {
				System.out.println(facilityName + typeSelection + roomTypeSelection + accessible);
				createResponse = md.createFacility(facilityName, "Apartment", "1");
			}else{
				System.out.println(facilityName + typeSelection + roomTypeSelection + accessible);
				createResponse = md.createFacility(facilityName, "Apartment", "2");
			}
		}
		if (typeSelection.equals("Townhouse")) {
			if (accessible.equals("Accessible")) {
				System.out.println(facilityName + typeSelection + roomTypeSelection + accessible);
				createResponse = md.createFacility(facilityName, "Townhouse", "3");
			}else{
				System.out.println(facilityName + typeSelection + roomTypeSelection + accessible);
				createResponse = md.createFacility(facilityName, "Townhouse", "4");
			}
		}
		if (createResponse == null) {
			createResponse = "Something went wrong, facility was not created.";
		}
		facilityList=md.getAllFacilities();
		System.out.println("Created?");
	}
	
	public void destroyFacility() {
		removeResponse=null;
		evictedStudents = md.getResidentsByFacility(removeFacility);
			for (int i=0; i< evictedStudents.length; i++) {
				emailBeanInstance.sendEmail(evictedStudents[i].email, evictedStudents[i].getFacility() + " is being shut down.", "Transitioning to a new room can be done from the Residence Management System. We are sorry for the inconvenience.");
			}
		removeResponse = md.removeFacility(removeFacility);
		if (removeResponse==null){
			removeResponse = "Something went wrong, facility was not removed.";
		}
		facilityList=md.getAllFacilities();
	}
	
	public String[] getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String[] facilityType) {
		this.facilityType = facilityType;
	}

	public String[] getRoomType() {
		return roomType;
	}

	public void setRoomType(String[] roomType) {
		this.roomType = roomType;
	}

	public String getAccessible() {
		return accessible;
	}

	public void setAccessible(String accessible) {
		this.accessible = accessible;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getTypeSelection() {
		return typeSelection;
	}

	public void setTypeSelection(String typeSelection) {
		this.typeSelection = typeSelection;
	}

	public String getRoomTypeSelection() {
		return roomTypeSelection;
	}

	public void setRoomTypeSelection(String roomTypeSelection) {
		this.roomTypeSelection = roomTypeSelection;
	}

	public Facility[] getFacilityList() {
		return facilityList;
	}

	public void setFacilityList(Facility[] facilityList) {
		this.facilityList = facilityList;
	}

	public String getRemoveFacility() {
		return removeFacility;
	}

	public void setRemoveFacility(String removeFacility) {
		this.removeFacility = removeFacility;
	}

	public String getCreateResponse() {
		return createResponse;
	}

	public void setCreateResponse(String createResponse) {
		this.createResponse = createResponse;
	}

	public String getRemoveResponse() {
		return removeResponse;
	}

	public void setRemoveResponse(String removeResponse) {
		this.removeResponse = removeResponse;
	}

	public Student[] getEvictedStudents() {
		return evictedStudents;
	}

	public void setEvictedStudents(Student[] evictedStudents) {
		this.evictedStudents = evictedStudents;
	}
}
