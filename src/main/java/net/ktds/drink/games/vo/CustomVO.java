package net.ktds.drink.games.vo;

import net.ktds.drink.user.vo.UserVO;

public class CustomVO {

	private String gameId;
	private String userId;
	
	private GamesVO gamesVO;
	private UserVO userVO;
	
	
	public GamesVO getGamesVO() {
		return gamesVO;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setGamesVO(GamesVO gamesVO) {
		this.gamesVO = gamesVO;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	
	public CustomVO() {
		gamesVO = new GamesVO();
		userVO = new UserVO();
	}

}
