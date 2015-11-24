package ManagedBeans;

import Types.Bulletin;
import Controls.Driver;

public class BulletinPageBean {
	private String title;
	private String date;
	private String message;
	private Bulletin[] bulletinList;
	
	private Driver driver = new Driver();
	private Bulletin bulletin = new Bulletin(title, date, message);
	
	public void postBulletin(String title, String date, String message){
		this.title = title;
		this.date = date;
		this.message = message;
		driver.postBulletin(this.title, this.date, this.message);
	}
	
	public void postBulletin(){
		Bulletin bulletin = new Bulletin(title, date, message);
		driver.postBulletin(this.bulletin);
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
}
