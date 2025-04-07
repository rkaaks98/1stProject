package farmstory.dto;

import farmstory.DataTransferObject;

public class ArticleDTO implements DataTransferObject {
	
	private int id;
	private String userId;
	private String title;
	private String author;
	private String content;
	private int fileid;
	private int commentNumber;
	private int viewNumber;
	private String registerDate;
	private int file;
	private String oName;
	private String sName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		if(id != null) {
			this.id = Integer.parseInt(id);
		}
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}
	public int getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}
	public int getViewNumber() {
		return viewNumber;
	}
	public void setViewNumber(int viewNumber) {
		this.viewNumber = viewNumber;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	@Override
	public String toString() {
		return "ArticleDTO [id=" + id + ", userId=" + userId + ", title=" + title + ", author=" + author + ", content="
				+ content + ", fileid=" + fileid + ", commentNumber=" + commentNumber + ", viewNumber=" + viewNumber
				+ ", registerDate=" + registerDate + ", file=" + file + ", oName=" + oName + ", sName=" + sName + "]";
	}
	
	private int getFile() {
		return file;

	}
	public void setFile(int file) {
		this.file = file;
		
	}
	

	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	

}
