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

import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.play.vo.PlayVO;

public class SetMarbleBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SetMarbleBoardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		List<PlayVO> plays = new ArrayList<PlayVO>();
		PlayVO play = null;
		for(int i=0; i<23; i++){
			play = new PlayVO();
			play.setPlayInfoId("play"+ i%7 +"PlayInfoId");
			play.setUserId("play"+ i%7 +"UserId");
			play.setGameId("play"+ i%7 +"GameId");
			
			play.setGames(new GamesVO());			
			play.getGames().setGameId("play"+ i%7 +"GameId");
			play.getGames().setGameName("play"+ i%7 +"GameName");
			play.getGames().setGameInfo("play"+ i%7 +"GameInfo");
			play.getGames().setTypeId("play"+ i%7 + "TypeId");
			plays.add(play);
		}
		
		Random rnd = new Random();
		int random;
		int size = 24;
		for(int i=0; i<size-1; i++){
			random = rnd.nextInt(size-1);
			play = plays.get(i);
			plays.set(i, plays.get(random));
			plays.set(random, play);
		}
		
		play = new PlayVO();
		play.setPlayInfoId("playstart");
		play.setUserId("playstart");
		play.setGameId("playstart");
		
		play.setGames(new GamesVO());			
		play.getGames().setGameId("playstart");
		play.getGames().setGameName("playstart");
		play.getGames().setGameInfo("playstart");
		
		plays.add(0, play);
		
		String viewPath = "/WEB-INF/view/play/marble.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(viewPath);
		
		request.setAttribute("plays", plays);
		
		rd.forward(request, response);
	}

}
