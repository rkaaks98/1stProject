package farmstory.dto;

import farmstory.DataTransferObject;

public class EventDTO implements DataTransferObject{

	private int id;
	private String eventTitle;
	private String eventDate;
	private String decription;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	@Override
	public String toString() {
		return "EventDTO [id=" + id + ", eventTitle=" + eventTitle + ", eventDate=" + eventDate + ", decription="
				+ decription + "]";
	}
	
	

}
