package net.ktds.drink.admin.vo;

import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.user.vo.UserVO;

public class GameSetVO {
	private String playInfoId;
	private String userId;
	private String gameId;

	private UserVO userVO;
	private HistoryVO historyVO;
	private GamesVO gamesVO;
	
	public GameSetVO() {
		userVO = new UserVO();
		historyVO = new HistoryVO();
		gamesVO = new GamesVO();
		
		
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

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}


	public HistoryVO getHistoryVO() {
		return historyVO;
	}
	public void setHistoryVO(HistoryVO historyVO) {
		this.historyVO = historyVO;
	}
	public GamesVO getGamesVO() {
		return gamesVO;
	}

	public void setGamesVO(GamesVO gamesVO) {
		this.gamesVO = gamesVO;
	}
}
