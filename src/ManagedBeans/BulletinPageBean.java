package ManagedBeans;
/**
 * This class handles all the functionality found on the bulletin web page
 * It uses functions found in the Driver class and ManagerDriver class.
 */
import Types.Bulletin;

import javax.annotation.PostConstruct;

import Controls.Driver;
import Controls.ManagerDriver;

public class BulletinPageBean {
	
	/**
	 * Variables used by BulletinPageBean
	 */
	private String title;
	private String date;
	private String message;
	private Bulletin[] bulletinList = new Bulletin[3];
	private Bulletin bulletin;
	private String serverResponse;
	private Driver driver = new Driver();
	private ManagerDriver md = new ManagerDriver();
	private String removedBulletinTitle;
	String[] bulletinTitles;
	
	/**
	 * PostConstruct used so bulletinList is created before injection
	 */
	@PostConstruct
	public void init(){
		bulletinList = driver.getBulletins();
	}
	
	/**
	 * Posts a bulletin with a title, date and message
	 * this sets what was entered to the corresponding strings in the class
	 * @param title
	 * @param date
	 * @param message
	 */
	public void postBulletin(String title, String date, String message){
		this.title = title;
		this.date = date;
		this.message = message;
		driver.postBulletin(this.title, this.date, this.message);
	}
	
	/**
	 * Posts a bulletin by sending in a Bulletin object
	 * gets all bulletins and sets it to bulletinList
	 */
	public void postBulletin(){
		serverResponse = null;
		Bulletin bulletin = new Bulletin(title, date, message);
		serverResponse = driver.postBulletin(bulletin);
		bulletinList = driver.getBulletins();
	}
	
	/**
	 * Removes a bulletin
	 */
	public void removeBulletin() {
		md.removeBulletin(removedBulletinTitle);
		getBulletinTitles();
		bulletinList = driver.getBulletins();
	}
	/**
	 * Gets the titles of the bulletins
	 * @return String[]
	 */
	public String[] getBulletinTitles() {
		bulletinTitles = new String[bulletinList.length];
		for (int i=0; i< bulletinList.length; i++) {
			bulletinTitles[i] = bulletinList[i].getTitle();
		}
		return bulletinTitles;
	}
	
	/**
	 * Gets all bulletins
	 * @return Bulletin[]
	 */
	public Bulletin[] getBulletins(){
		bulletinList = driver.getBulletins();
		return bulletinList;
	}
	
	/**
	 * get method for title
	 * @return
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * set method for title
	 * @param title
	 */
	public void setTitle(final String title){
		this.title = title;
	}
	
	/**
	 * get method for date
	 * @return
	 */
	public String getDate(){
		return date;
	}
	
	/**
	 * set method for date
	 * @param date
	 */
	public void setDate(final String date){
		this.date = date;
	}
	
	/**
	 * get method for message
	 * @return
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * set method for message
	 * @param message
	 */
	public void setMessage(final String message){
		this.message = message;
	}
	
	/**
	 * get method for bulletinList
	 * @return
	 */
	public Bulletin[] getBulletinList(){
		return bulletinList;
	}
	
	/**
	 * set method for bulletinList
	 * @param bulletinList
	 */
	public void setBulletinList(final Bulletin[] bulletinList){
		this.bulletinList = bulletinList;
	}
	
	/**
	 * get method for serverResponse
	 * @return
	 */
	public String getServerResponse() {
		return serverResponse;
	}

	/**
	 * set method for serverResponse
	 * @param serverReponse
	 */
	public void setServerResponse(String serverReponse) {
		this.serverResponse = serverReponse;
	}

	/**
	 * get method for bulletin
	 * @return
	 */
	public Bulletin getBulletin() {
		return bulletin;
	}

	/**
	 * set method for bulletin
	 * @param bulletin
	 */
	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}

	/**
	 * get method for removedBulletinTitle
	 * @return
	 */
	public String getRemovedBulletinTitle() {
		return removedBulletinTitle;
	}

	/**
	 * set method for removedBulletinTitle
	 * @param removedBulletinTitle
	 */
	public void setRemovedBulletinTitle(String removedBulletinTitle) {
		this.removedBulletinTitle = removedBulletinTitle;
	}

	/**
	 * set method for bulletinTitles
	 * @param bulletinTitles
	 */
	public void setBulletinTitles(String[] bulletinTitles) {
		this.bulletinTitles = bulletinTitles;
	}
}
