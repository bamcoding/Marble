package net.ktds.drink.games.vo;

public class GamesVO {
	private String gameId;
	private String gameName;
	private String gameInfo;
	private String categoryId;
	private String typeId;
	
	private CategoryVO categoryVO;
	private GameTypeVO gameTypeVO;
	
	public CategoryVO getCategoryVO() {
		return categoryVO;
	}

	public void setCategoryVO(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}

	public GameTypeVO getGameTypeVO() {
		return gameTypeVO;
	}

	public void setGameTypeVO(GameTypeVO gameTypeVO) {
		this.gameTypeVO = gameTypeVO;
	}

	public GamesVO() {
		categoryVO = new CategoryVO();
		gameTypeVO = new GameTypeVO();
	}
	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getGameInfo() {
		return gameInfo;
	}
	public void setGameInfo(String gameInfo) {
		this.gameInfo = gameInfo;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
}
