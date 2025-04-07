package farmstory.dto;

public class PageGroupDTO {
	private int start;
	private int end;
	private int current;

	public PageGroupDTO(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	public PageGroupDTO(int start, int end, int current) {
		this.start = start;
		this.end = end;
		this.setCurrent(current);
		
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}
}
