package net.ktds.drink.games.biz;

import java.util.List;

import net.ktds.drink.games.dao.GamesDao;
import net.ktds.drink.games.dao.GamesDaoImpl;
import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.GamesListVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.games.vo.SearchGamesVO;

public class GamesBizImpl implements GamesBiz {
	
	private GamesDao dao;
	
	public GamesBizImpl() {
		dao = new GamesDaoImpl();
	}	
	
	
	@Override
	public List<CategoryVO> getCategory(CategoryVO categoryVO) {
		return dao.getCategory(categoryVO);
	}


	@Override
	public List<GamesVO> getGames(GamesVO gamesVO) {
		return dao.getGames(gamesVO);
	}



	

}
