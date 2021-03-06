package net.ktds.drink.play.web.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.ktds.drink.constants.Games;
import net.ktds.drink.constants.Session;
import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.play.biz.PlayBiz;
import net.ktds.drink.play.biz.PlayBizImpl;
import net.ktds.drink.play.vo.PlayVO;
import net.ktds.drink.support.Param;
import net.ktds.drink.user.vo.UserVO;

public class SetMarbleBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GamesBiz gamesBiz;
	private PlayBiz playBiz;
	
    public SetMarbleBoardServlet() {
        super();
        gamesBiz = new GamesBizImpl();
        playBiz = new PlayBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();

		String randomPlays = Param.getStringParam(request, "random");
		
		List<PlayVO> plays = null;
		
		String playInfo = Param.getStringParam(request, "playInfo");
		
		if(playInfo != null && playInfo != ""){
			 plays = playBiz.getPlaysByPlayInfo(playInfo);
		}else{
			plays = (List<PlayVO>) session.getAttribute(Session.GAME_SETTING);
		}
		
		
		if(randomPlays.equals("true")){
			plays = null;
		}
		
		UserVO user = (UserVO) session.getAttribute(Session.USER_INFO);
		
		
		Random rnd = new Random();

		PlayVO play = null;
		List<GamesVO> allGames = null;
		GamesVO game = null;
		if(plays == null) {
			plays = new ArrayList<PlayVO>();
			allGames = gamesBiz.allGetGames("all");
			int gamesSize = allGames.size();
			for(int i=0; i<Games.CELL_SIZE; i++){
				play = new PlayVO();
				game = allGames.get(rnd.nextInt(gamesSize));
				play.setGames(game);
				play.setGameId(game.getGameId());
				if(user!=null){
					play.setUserId(user.getUserId());
				}
				plays.add(play);
			}
		}
		
		if(user != null){
			playBiz.registerHistory(plays);			
		}
		
		int random;
		int size = plays.size();
		for(int i=0; i<size; i++){
			random = rnd.nextInt(size);
			play = plays.get(i);
			plays.set(i, plays.get(random));
			plays.set(random, play);
		}
		
		PlayVO start = new PlayVO();
		start.setGames(new GamesVO());
		start.getGames().setGameId("playstart");
		start.getGames().setGameName("START");
		start.getGames().setGameInfo("playstart");
		start.getGames().setTypeId("0");
		
		plays.add(0, play);
		
		String viewPath = "/WEB-INF/view/play/marble.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		
		request.setAttribute("plays", plays);
		request.setAttribute("start", start);
		rd.forward(request, response);
	}

}
