package net.ktds.drink.games.biz;

import java.util.List;

import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.GamesVO;

public interface GamesBiz {
	public List<CategoryVO> getCategory(CategoryVO categoryVO);
	public List<GamesVO> getGames(GamesVO gamesVO);
	public List<GamesVO> allGetGames();
	public GamesVO getGame(String gameId);
}
