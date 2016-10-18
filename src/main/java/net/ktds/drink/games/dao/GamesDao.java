package net.ktds.drink.games.dao;

import java.util.List;

import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.user.vo.UserVO;

public interface GamesDao {

	public List<CategoryVO> getCategory(CategoryVO categoryVO);
	public List<GamesVO> getGames(GamesVO gamesVO);
	public List<GamesVO> allGetGames();
	public GamesVO getGame(String gameId);
	public int addGame(String gameName, String gameInfo);
	public GamesVO getGameBy(String gameName, String gameInfo);
	public int addCustom(GamesVO gamesVO, UserVO userInfo);
	public int countGameName(String gameName);
	public List<GamesVO> getGoldenCards();
}
