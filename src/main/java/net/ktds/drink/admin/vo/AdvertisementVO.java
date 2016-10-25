package net.ktds.drink.admin.vo;

public class AdvertisementVO {

	String advertisementId;
	String contractDate;
	String expirationDate;
	String filePath;
	String fileName;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
