package net.ktds.drink.games.biz;

import java.util.List;

import net.ktds.drink.admin.vo.AdvertisementVO;
import net.ktds.drink.games.dao.GamesDao;
import net.ktds.drink.games.dao.GamesDaoImpl;
import net.ktds.drink.games.vo.CategoryVO;
import net.ktds.drink.games.vo.CustomListVO;
import net.ktds.drink.games.vo.CustomVO;
import net.ktds.drink.games.vo.GamesListVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.games.vo.SearchGamesVO;
import net.ktds.drink.support.pager.Pager;
import net.ktds.drink.support.pager.PagerFactory;
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
	public List<CategoryVO> getAdminCategory(CategoryVO categoryVO) {
		return dao.getAdminCategory(categoryVO);
	}
	

	@Override
	public List<GamesVO> getGames(GamesVO gamesVO) {
		return dao.getGames(gamesVO);
	}


	@Override
	public List<GamesVO> allGetGames(String userId) {
		return dao.allGetGames(userId);
	}



	@Override
	public GamesVO getGame(String gameId) {
		return dao.getGame(gameId);
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
	public boolean addGame(GamesVO gamesVO) {
		return dao.addGame(gamesVO )> 0;
	}


	@Override
	public GamesVO getGameBy(String gameName) {
		return dao.getGameBy(gameName);
	}


	@Override
	public GamesVO getGameDetailBy(String gameId) {
		return dao.getGameDetailBy(gameId);
	}
	@Override
	public CustomVO getCustomDetailBy(String gameId) {
		return dao.getCustomDetailBy(gameId);
	}

	@Override
	public boolean deleteGames(String gameId) {
		return dao.deleteGames(gameId)> 0;
	}


	@Override
	public boolean updateGame(GamesVO gamesVO) {
		return dao.updateGame(gamesVO)> 0;
	}
	
	
	@Override
	public boolean updateCustom(GamesVO gamesVO) {
		
		
		
		return dao.updateCustom(gamesVO)>0;
	}


	@Override
	public GamesListVO getAllGames(SearchGamesVO searchGames) {
		int totalCount = dao.getConutOfGames(searchGames);
		
		//오라클 전용 페이지 만들어짐 
		//한페이지 3개 게시물, 3개그룹 보여주겠다
		Pager pager = PagerFactory.getPager(true);
		pager.setTotalArticleCount(totalCount);
		pager.setPageNumber(searchGames.getPageNumber());
		
		searchGames.setStartRowNumber(pager.getStartArticleNumber());
		searchGames.setEndRowNumber(pager.getEndArticleNumber());
		
		List<GamesVO> games = dao.getAllGames(searchGames);
		
		GamesListVO gamesList = new GamesListVO();
		gamesList.setPager(pager);
		gamesList.setGames(games);
		
		
		return gamesList;
	}


	@Override
	public GamesListVO getCategoryGames(SearchGamesVO searchGames, String categoryId) {
		int totalCount = dao.getConutOfCategoryGames(searchGames, categoryId);
		
		//오라클 전용 페이지 만들어짐 
		//한페이지 3개 게시물, 3개그룹 보여주겠다
		Pager pager = PagerFactory.getPager(true);
		pager.setTotalArticleCount(totalCount);
		pager.setPageNumber(searchGames.getPageNumber());
		
		searchGames.setStartRowNumber(pager.getStartArticleNumber());
		searchGames.setEndRowNumber(pager.getEndArticleNumber());
		
		List<GamesVO> games = dao.getCategoryGames(searchGames, categoryId);
		
		GamesListVO gamesList = new GamesListVO();
		gamesList.setPager(pager);
		gamesList.setGames(games);
		
		return gamesList;
	}

	@Override
	public CustomListVO getCustomGames(SearchGamesVO searchGames) {
		
		int totalCount = dao.getConutOfCustomGames(searchGames);
		
		//오라클 전용 페이지 만들어짐 
		//한페이지 3개 게시물, 3개그룹 보여주겠다
		Pager pager = PagerFactory.getPager(true);
		pager.setTotalArticleCount(totalCount);
		pager.setPageNumber(searchGames.getPageNumber());
		
		searchGames.setStartRowNumber(pager.getStartArticleNumber());
		searchGames.setEndRowNumber(pager.getEndArticleNumber());
		
		List<CustomVO> custom = dao.getCustomGames(searchGames);
		
		CustomListVO customList = new CustomListVO();
		customList.setPager(pager);
		customList.setCustom(custom);
		
		
		return customList;
	}

	public List<GamesVO> getGoldenCards() {
		return dao.getGoldenCards();
	}


	@Override
	public boolean deleteCustom(String gameId) {
		return dao.deleteCustom(gameId)> 0;
	}


	@Override
	public List<GamesVO> allGetGames() {
		return dao.allGetGames();
	}


	@Override
	public String getDetailImageofGamesBy(String gameName) {
		GamesVO gamesVO = dao.getImageofGamesBy(gameName);
		return gamesVO.getDetailImage();
	
	}


	@Override
	public String getCellImageofGamesBy(String gameName) {
		GamesVO gamesVO = dao.getImageofGamesBy(gameName);
		return gamesVO.getCellImage();
	}









	




}
