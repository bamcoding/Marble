package net.ktds.drink.games.dao;

import java.util.List;


import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.CustomVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.games.vo.SearchGamesVO;
import net.ktds.drink.user.vo.UserVO;

public interface GamesDao {
	
	public List<GamesVO> getAllGames(SearchGamesVO searchGames);
	public List<CustomVO> getCustomGames(SearchGamesVO searchGames);
	
	public List<CategoryVO> getCategory(CategoryVO categoryVO);
	public List<CategoryVO> getAdminCategory(CategoryVO categoryVO);
	public List<GamesVO> getGames(GamesVO gamesVO);
	public List<GamesVO> allGetGames(GamesVO gamesVO);
	public int addGame(GamesVO gamesVO);
	public GamesVO getGameBy(String gameName);
	public GamesVO getGameDetailBy(String gameId);
	public int addCustom(GamesVO gamesVO, UserVO userInfo);
	public int updateGame(GamesVO gamesVO);
	public int countGameName(String gameName);
	public int deleteGames(String gameId);
	
	public int getConutOfGames(SearchGamesVO searchGames);
	public int getConutOfCustomGames(SearchGamesVO searchGames);
}
