package net.ktds.drink.games.dao;

import java.util.List;

import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.GamesVO;

public interface GamesDao {

	public List<CategoryVO> getCategory(CategoryVO categoryVO);
	public List<GamesVO> getGames(GamesVO gamesVO);
	public List<GamesVO> allGetGames(GamesVO gamesVO);
}
