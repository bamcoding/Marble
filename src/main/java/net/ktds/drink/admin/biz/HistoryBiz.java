package net.ktds.drink.admin.biz;

import java.util.List;

import net.ktds.drink.admin.vo.GameSetVO;
import net.ktds.drink.admin.vo.HistoryListVO;
import net.ktds.drink.games.vo.SearchGamesVO;

public interface HistoryBiz {

	public HistoryListVO getAllHistory(SearchGamesVO searchGame);

	public boolean deleteHistory(String playInfoId);

	public GameSetVO getGameSetAt(String playInfoId);

	public List<GameSetVO> getAllGameSet(String playInfoId);

}
