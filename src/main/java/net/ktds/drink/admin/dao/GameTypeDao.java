package net.ktds.drink.admin.dao;

import java.util.List;

import net.ktds.drink.admin.vo.GameTypeVO;
import net.ktds.drink.games.vo.SearchGamesVO;

public interface GameTypeDao {
	public List<GameTypeVO> getGameType(SearchGamesVO searchGame);
	public int getConutOfGameType(SearchGamesVO searchGame);
	
	public int countTypeName(String typeName);
	public int addType(GameTypeVO type);
	public int deleteType(String typeId);
	public GameTypeVO getTypeAt(String typeId);
	public int updateType(GameTypeVO typeVO);
}
