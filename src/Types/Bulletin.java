package Types;

/**
 * Holds the info of a bulletin
 */

public class Bulletin {

	public String title;	//the title of the bulletin
	public String date;		//date of when the bulletin was posted
	public String text;		//the message of the bulletin
	
	/**
	 * Bulletin constructor
	 * @param title
	 * @param date
	 * @param text
	 */
	public Bulletin (String title, String date, String text) {
		this.title=title;
		this.date=date;
		this.text=text;
	}
	
	//get and set methods
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * prints a bulletin
	 */
	public void printBulletin(){
		
		System.out.println("Title: "+title);
		System.out.println("Date: "+date);
		System.out.println("Text: "+text);
	}
}
