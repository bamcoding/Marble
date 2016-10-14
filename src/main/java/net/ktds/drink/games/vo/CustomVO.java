package net.ktds.drink.games.vo;

import net.ktds.drink.user.vo.UserVO;

public class CustomVO {

	
	private GamesVO gamesVO;
	public GamesVO getGamesVO() {
		return gamesVO;
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

	private UserVO userVO;
	
	public CustomVO() {
		gamesVO = new GamesVO();
		userVO = new UserVO();
	}

}
