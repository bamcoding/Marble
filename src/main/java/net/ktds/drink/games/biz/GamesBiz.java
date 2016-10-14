package net.ktds.drink.games.biz;

import java.util.List;

import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.GamesVO;

public interface GamesBiz {
	List<CategoryVO> getCategory(CategoryVO categoryVO);
	List<GamesVO> getGames(GamesVO gamesVO);
	List<GamesVO> allGetGames(GamesVO gamesVO);
	

}
