package net.ktds.drink.games.vo;

import java.util.List;

import net.ktds.drink.support.pager.Pager;

public class CustomListVO {
	private List<CustomVO> custom;
	public List<CustomVO> getCustom() {
		return custom;
	}
	public void setCustom(List<CustomVO> custom) {
		this.custom = custom;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	private Pager pager;
}
