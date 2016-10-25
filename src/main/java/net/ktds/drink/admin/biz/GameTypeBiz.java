package net.ktds.drink.admin.biz;

import net.ktds.drink.admin.vo.GameTypeListVO;
import net.ktds.drink.admin.vo.GameTypeVO;
import net.ktds.drink.games.vo.SearchGamesVO;


public interface GameTypeBiz {
	public GameTypeListVO getGameType(SearchGamesVO searchGame);
	
	public boolean isExsistTypeName(String typeName);

	public boolean addType(GameTypeVO type);

	public boolean deleteType(String typeId);

	public GameTypeVO getTypeAt(String typeId);

	public boolean updateType(GameTypeVO typeVO);
}
