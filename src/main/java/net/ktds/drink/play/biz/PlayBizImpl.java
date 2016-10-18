package net.ktds.drink.play.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Games;
import net.ktds.drink.constants.Session;
import net.ktds.drink.play.dao.PlayDao;
import net.ktds.drink.play.dao.PlayDaoImpl;
import net.ktds.drink.play.vo.HistoryVO;
import net.ktds.drink.play.vo.PlayVO;
import net.ktds.drink.user.vo.UserVO;

public class PlayBizImpl implements PlayBiz{
	
	private PlayDao playDao;
	
	public PlayBizImpl(){
		playDao = new PlayDaoImpl();
	}

	@Override
	public boolean registerHistory(List<PlayVO> plays) {
		int result = 0;
		result += playDao.addHistory(plays.get(0).getUserId());
		int playsSize = plays.size();
		for(int i=0; i<playsSize; i++){
			result += playDao.addPlays(plays.get(i));
		}
		return result == Games.CELL_SIZE+1;
	}

	@Override
	public boolean setLatestPlays(HttpServletRequest request) {
		List<HistoryVO> history = getHistory(request);
		if(history == null){
			return false;
		}
		
		HistoryVO latest = history.get(0);
		List<PlayVO> plays = getPlaysByPlayInfo(latest.getPlayInfo());
		
		HttpSession session = request.getSession();
		if(session.getAttribute(Session.GAME_SETTING) != null){
			session.removeAttribute(Session.GAME_SETTING);
		}
		session.setAttribute(Session.GAME_SETTING, plays);
		return session.getAttribute(Session.GAME_SETTING) != null;
	}

	@Override
	public List<HistoryVO> getHistory(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute(Session.USER_INFO);
		if(user == null){
			return null;
		}
		String userId = user.getUserId();
		List<HistoryVO> history = playDao.getHistoryByUserId(userId);
		return history;
	}

	@Override
	public List<PlayVO> getPlaysByPlayInfo(String playInfo) {
		return playDao.getPlayById(playInfo);
	}

}
