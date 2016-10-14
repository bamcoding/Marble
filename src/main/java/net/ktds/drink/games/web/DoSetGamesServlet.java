package net.ktds.drink.games.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.Param;


public class DoSetGamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private GamesBiz gamesBiz;

    public DoSetGamesServlet() {
        super();
        gamesBiz = new GamesBizImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String games = Param.getStringParam(request, "games");
		String[] gamesArr = games.split(",");
		
		List<GamesVO> setGames = new ArrayList<GamesVO>();
		for(int i=0; i<gamesArr.length; i++){
			
		}
		
		
	}

}
