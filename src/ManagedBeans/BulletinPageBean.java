package ManagedBeans;

import Types.Bulletin;

import javax.annotation.PostConstruct;

import Controls.Driver;
import Controls.ManagerDriver;

public class BulletinPageBean {
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
	@PostConstruct
	public void init(){
		bulletinList = driver.getBulletins();
	}
	
	public void postBulletin(String title, String date, String message){
		this.title = title;
		this.date = date;
		this.message = message;
		driver.postBulletin(this.title, this.date, this.message);
	}
	
	public void postBulletin(){
		serverResponse = null;
		Bulletin bulletin = new Bulletin(title, date, message);
		serverResponse = driver.postBulletin(bulletin);
		bulletinList = driver.getBulletins();
	}
	public void removeBulletin() {
		md.removeBulletin(removedBulletinTitle);
		getBulletinTitles();
		bulletinList = driver.getBulletins();
	}
	public String[] getBulletinTitles() {
		bulletinTitles = new String[bulletinList.length];
		for (int i=0; i< bulletinList.length; i++) {
			bulletinTitles[i] = bulletinList[i].getTitle();
		}
		return bulletinTitles;
	}
	
	public Bulletin[] getBulletins(){
		bulletinList = driver.getBulletins();
		return bulletinList;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(final String title){
		this.title = title;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDate(final String date){
		this.date = date;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(final String message){
		this.message = message;
	}
	
	public Bulletin[] getBulletinList(){
		return bulletinList;
	}
	
	public void setBulletinList(final Bulletin[] bulletinList){
		this.bulletinList = bulletinList;
	}

	public String getServerResponse() {
		return serverResponse;
	}

	public void setServerResponse(String serverReponse) {
		this.serverResponse = serverReponse;
	}

	public Bulletin getBulletin() {
		return bulletin;
	}

	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}

	public String getRemovedBulletinTitle() {
		return removedBulletinTitle;
	}

	public void setRemovedBulletinTitle(String removedBulletinTitle) {
		this.removedBulletinTitle = removedBulletinTitle;
	}

	public void setBulletinTitles(String[] bulletinTitles) {
		this.bulletinTitles = bulletinTitles;
	}
}
