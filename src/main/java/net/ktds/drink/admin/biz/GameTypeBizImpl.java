package net.ktds.drink.admin.biz;

import java.util.List;

import net.ktds.drink.admin.dao.GameTypeDao;
import net.ktds.drink.admin.dao.GameTypeDaoImpl;
import net.ktds.drink.admin.vo.GameTypeListVO;
import net.ktds.drink.admin.vo.GameTypeVO;
import net.ktds.drink.games.vo.GamesListVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.games.vo.SearchGamesVO;
import net.ktds.drink.support.pager.Pager;
import net.ktds.drink.support.pager.PagerFactory;
import net.ktds.drink.user.dao.UserDao;
import net.ktds.drink.user.dao.UserDaoImpl;

public class GameTypeBizImpl implements GameTypeBiz {
	
	private GameTypeDao dao;
	
	public GameTypeBizImpl() {
		dao = new GameTypeDaoImpl();
	}
	@Override
	public GameTypeListVO getGameType(SearchGamesVO searchGame) {
		int totalCount = dao.getConutOfGameType(searchGame);
		
		//오라클 전용 페이지 만들어짐 
		//한페이지 3개 게시물, 3개그룹 보여주겠다
		Pager pager = PagerFactory.getPager(true);
		pager.setTotalArticleCount(totalCount);
		pager.setPageNumber(searchGame.getPageNumber());
		
		searchGame.setStartRowNumber(pager.getStartArticleNumber());
		searchGame.setEndRowNumber(pager.getEndArticleNumber());
		
		List<GameTypeVO> gameTypes = dao.getGameType(searchGame);
		
		GameTypeListVO gameTypeList = new GameTypeListVO();
		gameTypeList.setPager(pager);
		gameTypeList.setGameType(gameTypes);
		
		return gameTypeList;
	}
	@Override
	public boolean isExsistTypeName(String typeName) {
		return dao.countTypeName(typeName) > 0;
	}
	@Override
	public boolean addType(GameTypeVO type) {
		return dao.addType(type) > 0;
	}
	@Override
	public boolean deleteType(String typeId) {
		return dao.deleteType(typeId) > 0;
	}
	@Override
	public GameTypeVO getTypeAt(String typeId) {
		return dao.getTypeAt(typeId);
	}
	@Override
	public boolean updateType(GameTypeVO typeVO) {
		return dao.updateType(typeVO) > 0;
	}

}
