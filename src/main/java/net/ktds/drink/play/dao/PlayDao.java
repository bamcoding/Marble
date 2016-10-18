package net.ktds.drink.play.dao;

import java.util.List;

import net.ktds.drink.play.vo.HistoryVO;
import net.ktds.drink.play.vo.PlayVO;

public interface PlayDao {
	public List<HistoryVO> getHistoryByUserId(String userId);
	
	public List<PlayVO> getPlayById(String playInfo);
	
	public int addHistory(String userId);
	
	public int addPlays(PlayVO playVO);
}
