package net.ktds.drink.boards.vo;

import java.util.List;

import net.ktds.drink.support.pager.Pager;

public class BoardListVO {

	private List<BoardVO> boards;
	private Pager pager;
	
	public List<BoardVO> getBoardLists() {
		return boards;
	}
	public void setBoardLists(List<BoardVO> boards) {
		this.boards = boards;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
}
