package net.ktds.drink.play.vo;

import net.ktds.drink.games.vo.GamesVO;

public class PlayVO {
	private String playInfoId;
	private String userId;
	private String gameId;

	private GamesVO games;

	public GamesVO getGames() {
		return games;
	}

	public void setGames(GamesVO games) {
		this.games = games;
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

}
