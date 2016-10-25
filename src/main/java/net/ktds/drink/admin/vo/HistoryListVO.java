package net.ktds.drink.admin.vo;

import java.util.List;

import net.ktds.drink.support.pager.Pager;

public class HistoryListVO {
	
	private List<HistoryVO> history;
	private Pager pager;
	
	public List<HistoryVO> getHistory() {
		return history;
	}
	public void setHistory(List<HistoryVO> history) {
		this.history = history;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
}
