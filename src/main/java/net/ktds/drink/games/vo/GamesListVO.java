package net.ktds.drink.games.vo;

import java.util.List;

import net.ktds.drink.support.pager.Pager;


public class GamesListVO {
	
	private List<GamesVO> games;
	private Pager pager;
	
	public List<GamesVO> getGames() {
		return games;
	}

	public void setGames(List<GamesVO> games) {
		this.games = games;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
}
