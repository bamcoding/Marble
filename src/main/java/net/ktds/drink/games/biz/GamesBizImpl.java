package net.ktds.drink.games.biz;

import java.util.List;

import net.ktds.drink.games.dao.GamesDao;
import net.ktds.drink.games.dao.GamesDaoImpl;
import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.CustomVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.user.dao.UserDao;
import net.ktds.drink.user.dao.UserDaoImpl;
import net.ktds.drink.user.vo.UserVO;

public class GamesBizImpl implements GamesBiz {
	
	private GamesDao dao;
	private UserDao userDao;
	public GamesBizImpl() {
		dao = new GamesDaoImpl();
		userDao = new UserDaoImpl();
	}	
	
	
	@Override
	public List<CategoryVO> getCategory(CategoryVO categoryVO) {
		return dao.getCategory(categoryVO);
	}


	@Override
	public List<GamesVO> getGames(GamesVO gamesVO) {
		return dao.getGames(gamesVO);
	}


	@Override
	public List<GamesVO> allGetGames(GamesVO gamesVO) {
		return dao.allGetGames(gamesVO);
	}


	@Override
	public boolean isExsistGameName(String gameName) {
		return dao.countGameName(gameName) > 0;
	}


	@Override
	public boolean addCustom(GamesVO gameVO, UserVO userInfo) {
		return dao.addCustom(gameVO, userInfo)> 0;
	}


	@Override
	public boolean addGame(String gameName, String gameInfo) {
		return dao.addGame(gameName,gameInfo )> 0;
	}


	@Override
	public GamesVO getGameBy(String gameName, String gameInfo) {
		return dao.getGameBy(gameName, gameInfo);
	}



	

}
