package farmstory.dto;

import farmstory.DataTransferObject;

public class ProductImageDTO implements DataTransferObject {
	private int id;
	private int productid;
	private String thumbnailLocation;
	private String infoLocation;
	private String detailLocation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getThumbnailLocation() {
		return thumbnailLocation;
	}

	public void setThumbnailLocation(String thumbnailLocation) {
		this.thumbnailLocation = thumbnailLocation;
	}

	public String getInfoLocation() {
		return infoLocation;
	}

	public void setInfoLocation(String infoLocation) {
		this.infoLocation = infoLocation;
	}

	public String getDetailLocation() {
		return detailLocation;
	}

	public void setDetailLocation(String detailLocation) {
		this.detailLocation = detailLocation;
	}

	@Override
	public String toString() {
		return "ProductImageDTO [id=" + id + ", productid=" + productid + ", thumbnailLocation=" + thumbnailLocation
				+ ", infoLocation=" + infoLocation + ", detailLocation=" + detailLocation + "]";
	}

	

}
