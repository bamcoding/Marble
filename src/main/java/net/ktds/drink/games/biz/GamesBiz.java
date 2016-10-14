package net.ktds.drink.games.biz;

import java.util.List;


import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.CustomVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.user.vo.UserVO;

public interface GamesBiz {
	public List<CategoryVO> getCategory(CategoryVO categoryVO);
	public List<GamesVO> getGames(GamesVO gamesVO);
	public List<GamesVO> allGetGames(GamesVO gamesVO);
	public boolean addCustom(GamesVO gameVO, UserVO userInfo);
	public GamesVO getGameBy(String gameName, String gameInfo);
	public boolean addGame(String gameName, String gameInfo);
	public boolean isExsistGameName(String gameName);

}
