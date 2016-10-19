package net.ktds.drink.admin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ktds.drink.games.biz.GamesBiz;
import net.ktds.drink.games.biz.GamesBizImpl;
import net.ktds.drink.games.vo.CustomVO;
import net.ktds.drink.games.vo.GamesVO;
import net.ktds.drink.support.Param;


public class DoUpdateCustomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GamesBiz biz;        
  
    public DoUpdateCustomServlet() {
        super();
        biz = new GamesBizImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameId = Param.getStringParam(request, "gameId");
		String gameName = Param.getStringParam(request, "gameName");
		String gameInfo = Param.getStringParam(request, "gameInfo");

		gameInfo = gameInfo.replace("\n", "<br/>").replaceAll("\r", " ");
		
		GamesVO gamesVO = new GamesVO();
		gamesVO.setGameId(gameId);
		gamesVO.setGameName(gameName);
		gamesVO.setGameInfo(gameInfo);  
		

		biz.updateCustom(gamesVO);
	}

}
