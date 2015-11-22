package Types;

public class Bulletin {
	public String title;
	public String date;
	public String text;
	
	public Bulletin (String title, String date, String text) {
		this.title=title;
		this.date=date;
		this.text=text;
	}
	
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
	
	public void printBulletin(){
		
		System.out.println("Title: "+title);
		System.out.println("Date: "+date);
		System.out.println("Text: "+text);
	}
}
