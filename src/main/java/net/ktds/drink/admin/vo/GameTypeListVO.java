package net.ktds.drink.admin.vo;

import java.util.List;
import net.ktds.drink.support.pager.Pager;

public class GameTypeListVO {
	
	private List<GameTypeVO> gameType;
	private Pager pager;
	
	public List<GameTypeVO> getGameType() {
		return gameType;
	}
	public void setGameType(List<GameTypeVO> gameType) {
		this.gameType = gameType;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
}
