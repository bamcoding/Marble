package net.ktds.drink.admin.dao;

import java.util.List;

import net.ktds.drink.admin.vo.GameSetVO;
import net.ktds.drink.admin.vo.HistoryVO;
import net.ktds.drink.games.vo.SearchGamesVO;

public interface HistoryDao {

	public int getConutOfHistory(SearchGamesVO searchGame);

	List<HistoryVO> getHistory(SearchGamesVO searchGame);

	public int deleteHistory(String playInfoId);

	public GameSetVO getGameSetAt(String playInfoId);

	public List<GameSetVO> getAllGameSet(String playInfoId);

}
