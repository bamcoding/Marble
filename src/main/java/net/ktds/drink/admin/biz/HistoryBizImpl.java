package net.ktds.drink.admin.biz;

import java.util.List;

import net.ktds.drink.admin.dao.HistoryDao;
import net.ktds.drink.admin.dao.HistoryDaoImpl;
import net.ktds.drink.admin.vo.GameSetVO;
import net.ktds.drink.admin.vo.HistoryListVO;
import net.ktds.drink.admin.vo.HistoryVO;
import net.ktds.drink.games.vo.SearchGamesVO;
import net.ktds.drink.support.pager.Pager;
import net.ktds.drink.support.pager.PagerFactory;

public class HistoryBizImpl implements HistoryBiz {
	
	private HistoryDao dao;
	
	public HistoryBizImpl() {
		dao = new HistoryDaoImpl();
	}
	
	@Override
	public HistoryListVO getAllHistory(SearchGamesVO searchGame) {
		
		int totalCount = dao.getConutOfHistory(searchGame);
		
		//오라클 전용 페이지 만들어짐 
		//한페이지 3개 게시물, 3개그룹 보여주겠다
		Pager pager = PagerFactory.getPager(true);
		pager.setTotalArticleCount(totalCount);
		pager.setPageNumber(searchGame.getPageNumber());
		
		searchGame.setStartRowNumber(pager.getStartArticleNumber());
		searchGame.setEndRowNumber(pager.getEndArticleNumber());
		
		List<HistoryVO> history = dao.getHistory(searchGame);
		
		HistoryListVO HistoryList = new HistoryListVO();
		HistoryList.setPager(pager);
		HistoryList.setHistory(history);
		
		return HistoryList;
	}

	@Override
	public boolean deleteHistory(String playInfoId) {
		return dao.deleteHistory(playInfoId) > 0;
	}

	@Override
	public GameSetVO getGameSetAt(String playInfoId) {
		return dao.getGameSetAt(playInfoId);
	}

	@Override
	public List<GameSetVO> getAllGameSet(String playInfoId) {
		return dao.getAllGameSet(playInfoId);
	}

}
