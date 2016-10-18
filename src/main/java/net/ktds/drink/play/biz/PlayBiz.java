package net.ktds.drink.play.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.ktds.drink.play.vo.HistoryVO;
import net.ktds.drink.play.vo.PlayVO;

public interface PlayBiz {
	public boolean registerHistory(List<PlayVO> plays);
	
	public boolean setLatestPlays(HttpServletRequest request);
	
	public List<HistoryVO> getHistory(HttpServletRequest request);
	
	public List<PlayVO> getPlaysByPlayInfo(String playInfo);
}
