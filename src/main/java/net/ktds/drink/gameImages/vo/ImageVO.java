package net.ktds.drink.gameImages.vo;

public class ImageVO {
	private String imageId;
	private String imageName;
	private String imageTitle;
	private String managerId;
	private String imageDescription;
	private String latestModifyDate;
	
	
	public String getLatestModifyDate() {
		return latestModifyDate;
	}
	public void setLatestModifyDate(String latestModifyDate) {
		this.latestModifyDate = latestModifyDate;
	}
	public String getImageTitle() {
		return imageTitle;
	}
	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getImageDescription() {
		return imageDescription;
	}
	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}
	
	
}
