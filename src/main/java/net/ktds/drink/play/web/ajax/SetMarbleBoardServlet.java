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

import net.ktds.drink.constants.Session;
import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.play.vo.PlayVO;

public class SetMarbleBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GamesBiz gamesBiz;
	
    public SetMarbleBoardServlet() {
        super();
        gamesBiz = new GamesBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		List<PlayVO> plays = (List<PlayVO>) session.getAttribute(Session.GAME_SETTING);
		
		Random rnd = new Random();

		PlayVO play = null;
		List<GamesVO> allGames = null;
		if(plays == null) {
			plays = new ArrayList<PlayVO>();
			allGames = gamesBiz.allGetGames();
			int gamesSize = allGames.size();
			for(int i=0; i<19; i++){
				play = new PlayVO();
				play.setGames(allGames.get(rnd.nextInt(gamesSize)));
				plays.add(play);
			}
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
