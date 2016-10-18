package net.ktds.drink.play.biz;

import javax.servlet.http.HttpServletRequest;

import net.ktds.drink.play.vo.PlayVO;

public interface PlayBiz {
	public boolean registerHistory(PlayVO play);
	
	public boolean setLatestPlays(HttpServletRequest request);
	
	public boolean getHistory(HttpServletRequest request);
	
	public boolean setPlaysByPlayInfo(String playInfo, HttpServletRequest request);
}
