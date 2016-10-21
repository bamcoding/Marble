package net.ktds.drink.games.biz;

import java.util.List;

import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.CustomListVO;
import net.ktds.drink.games.vo.CustomVO;
import net.ktds.drink.games.vo.GamesListVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.games.vo.SearchGamesVO;
import net.ktds.drink.user.vo.UserVO;

public interface GamesBiz {
	public List<CategoryVO> getCategory(CategoryVO categoryVO);
	public List<CategoryVO> getAdminCategory(CategoryVO categoryVO);
	
	public GamesListVO getCategoryGames(SearchGamesVO searchGames, String categoryId);
	public GamesListVO getAllGames(SearchGamesVO searchGames);
	public CustomListVO getCustomGames(SearchGamesVO searchGames);
	
	public List<GamesVO> getGames(GamesVO gamesVO);
	public List<GamesVO> allGetGames();
	public GamesVO getGame(String gameId);
	public boolean addCustom(GamesVO gameVO, UserVO userInfo);
	public GamesVO getGameBy(String gameName);
	public GamesVO getGameDetailBy(String gameId);
	public CustomVO getCustomDetailBy(String gameId);
	
	
	public boolean addGame(GamesVO gamesVO);
	public boolean updateGame(GamesVO gamesVO);
	public boolean updateCustom(GamesVO gamesVO);
	public boolean deleteGames(String gameId);
	public boolean deleteCustom(String gameId);
	public boolean isExsistGameName(String gameName);
	public List<GamesVO> getGoldenCards();
}
