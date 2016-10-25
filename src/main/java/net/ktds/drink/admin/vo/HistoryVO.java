package net.ktds.drink.admin.vo;


import net.ktds.drink.user.vo.UserVO;


public class HistoryVO {
	private String playInfoId;
	private String userId;
	private String playTime;

	private UserVO userVO;
	
	public HistoryVO() {
		userVO = new UserVO();
		
		
	}
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	public String getPlayInfoId() {
		return playInfoId;
	}

	public void setPlayInfoId(String playInfoId) {
		this.playInfoId = playInfoId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}



}
