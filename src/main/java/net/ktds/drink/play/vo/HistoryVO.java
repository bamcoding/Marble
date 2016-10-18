package net.ktds.drink.play.vo;

public class HistoryVO {
	private String playInfo;
	private String playDate;
	private String userId;
	
	private PlayVO playVO;

	public String getPlayInfo() {
		return playInfo;
	}

	public void setPlayInfo(String playInfo) {
		this.playInfo = playInfo;
	}

	public String getPlayDate() {
		return playDate;
	}

	public void setPlayDate(String playDate) {
		this.playDate = playDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public PlayVO getPlayVO() {
		return playVO;
	}

	public void setPlayVO(PlayVO playVO) {
		this.playVO = playVO;
	}
	
	
}
